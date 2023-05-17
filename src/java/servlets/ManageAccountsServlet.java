package servlets;

import database.AccountDA;
import database.BankingSystemEM;
import domain.Account;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ManageAccountsServlet extends HttpServlet {

    private EntityManagerFactory emf;

    public ManageAccountsServlet(){
         emf = BankingSystemEM.getEmFactory();
}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        
        List<Account> allAccounts = AccountDA.getAllAccounts(em);
        
        em.close();
        
        request.setAttribute("allAccounts", allAccounts);
        
        request.getRequestDispatcher("/manage_accounts.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            String accountNumber = request.getParameter("accountNumber");
            String customerId = request.getParameter("customerId");
            String accountType = request.getParameter("accountType");
            String dateString = request.getParameter("dateOpened");
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date dateOpened = null;
            try {
                dateOpened = new Date(format.parse(dateString).getTime());
            } catch (ParseException e) {
                  e.printStackTrace();
                }
            
            Account account = new Account();
            account.setAccountNumber(accountNumber);
            account.setCustomerId(customerId);
            account.setAccountType(accountType);
            account.setDateOpened(dateOpened);

            AccountDA.create(account);
            HttpSession session = request.getSession();
            session.setAttribute("message", "Account successfully created");
            List<Account> allAccounts = AccountDA.getAllAccounts(em);
            request.setAttribute("allAccounts", allAccounts);
            request.getRequestDispatcher("/manage_accounts.jsp").forward(request, response);
        
        
        } else if ("delete".equals(action)) {
            AccountDA accountDA = new AccountDA();
            String accountNumber = request.getParameter("accountNumber");
            Account account = accountDA.getAccountByNumber(accountNumber);
        if (account != null) {
                AccountDA.delete(accountNumber);
                request.setAttribute("message", "Account successfully deleted");
        } else {
            request.setAttribute("message", "Unable to delete Account");
        }
            List<Account> allAccounts = AccountDA.getAllAccounts(em);
            request.setAttribute("allAccounts", allAccounts);
            request.getRequestDispatcher("/manage_accounts.jsp").forward(request, response);   
    }
        em.close();
}
}