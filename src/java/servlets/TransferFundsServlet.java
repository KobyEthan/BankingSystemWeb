package servlets;

import database.AccountDA;
import database.BankingSystemEM;
import database.TransactionDA;
import domain.Account;
import domain.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class TransferFundsServlet extends HttpServlet {
    private EntityManager em;
    private EntityManagerFactory emf;
    private AccountDA accountDA;
    private TransactionDA transactionDA;

    public TransferFundsServlet() {
        emf = BankingSystemEM.getEmFactory();
        em = emf.createEntityManager();
        accountDA = new AccountDA();
        transactionDA = new TransactionDA();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        HttpSession session = request.getSession();
        String customerId = (String) session.getAttribute("customerId");

        List<Account> accounts = accountDA.findAccountsByCustomerId(customerId);
        request.setAttribute("accounts", accounts);

        List<Transaction> allTransactions = transactionDA.getAllTransactions(em);
        request.getSession().setAttribute("allTransactions", allTransactions);

        request.getRequestDispatcher("/transfer_funds.jsp").forward(request, response);
    }

    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String fromAccountNumber = request.getParameter("fromAccount");
    String toAccountNumber = request.getParameter("toAccount");
    double transferAmount = Double.parseDouble(request.getParameter("transferAmount"));
    String transactionDescription = "Transfer of funds from account " + fromAccountNumber + " to account " + toAccountNumber;
    String transactionDate = request.getParameter("transactionDate");

    Account fromAccount = accountDA.getAccount(fromAccountNumber);
    Account toAccount = accountDA.getAccount(toAccountNumber);

    if (fromAccount.getBalance() < transferAmount) {
        request.getSession().setAttribute("message", "Insufficient funds");
        response.sendRedirect(request.getContextPath() + "/transferFunds");
        return;
    }

    String fromAccountType = fromAccount.getAccountType();
    String toAccountType = toAccount.getAccountType();

    String fromTransactionType = fromAccountType.equals(Account.ASSET) ? "debit" : "credit";
    String toTransactionType = toAccountType.equals(Account.ASSET) ? "credit" : "debit";

    Transaction fromTransaction = new Transaction(transferAmount, transactionDescription, transactionDate, fromAccountNumber, fromTransactionType);
    Transaction toTransaction = new Transaction(transferAmount, transactionDescription, transactionDate, toAccountNumber, toTransactionType);

    transactionDA.create(fromTransaction, em);
    transactionDA.create(toTransaction, em);

    request.getSession().setAttribute("message", "Transfer successful");
    response.sendRedirect(request.getContextPath() + "/transfer_funds.jsp");
}
}