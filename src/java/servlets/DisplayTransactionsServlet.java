package servlets;

import database.TransactionDA;
import domain.Account;
import domain.Transaction;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "DisplayTransactionsServlet", urlPatterns = {"/displayTransactions"})
public class DisplayTransactionsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accountNumber = request.getParameter("accountNumber");
        ArrayList<Transaction> transactions = TransactionDA.findTransactionsByAccountNumber(accountNumber);
        request.setAttribute("transactions", transactions);
        request.getRequestDispatcher("transactions.jsp").forward(request, response);
    }
}