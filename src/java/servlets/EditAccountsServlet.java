package servlets;

import database.AccountDA;
import database.BankingSystemEM;
import domain.Account;
import java.io.IOException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditAccountsServlet extends HttpServlet {
    private EntityManager em;
    private AccountDA accountDA;

    public EditAccountsServlet() {
        EntityManagerFactory emf = BankingSystemEM.getEmFactory();
        em = emf.createEntityManager();
        accountDA = new AccountDA();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accountNumber = request.getParameter("accountNumber");
        HttpSession session = request.getSession();
        session.setAttribute("accountToUpdate", accountNumber);
        Account accountToUpdate = accountDA.getAccountByNumber(accountNumber);

        session.setAttribute("accountToUpdate", accountToUpdate);
        response.sendRedirect("edit_accounts.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accountNumber = (String) request.getSession().getAttribute("accountToUpdate");
        Account accountToUpdate = accountDA.getAccountByNumber(accountNumber);

        if (accountToUpdate != null) {
            try {
                em.getTransaction().begin();
                accountToUpdate.setAccountName(request.getParameter("accountName"));
                accountToUpdate.setAccountType(request.getParameter("accountType"));

                accountDA.updateAccount(accountToUpdate, em);
                em.flush();
                em.getTransaction().commit();

                request.getSession().setAttribute("message", "Account edit successful");
                request.getRequestDispatcher("/edit_accounts.jsp").forward(request, response);
            } catch (Exception e) {
                Logger.getLogger(EditAccountsServlet.class.getName()).log(Level.SEVERE, "Error updating account", e);
                em.getTransaction().rollback();
                request.getSession().setAttribute("message", "Unable to edit account. Please try again later.");
                response.sendRedirect(request.getContextPath() + "/manage_accounts.jsp");
            }
        } else {
            request.getSession().setAttribute("message", "Unable to edit account. Account not found.");
            response.sendRedirect(request.getContextPath() + "/manage_accounts.jsp");
        }
    }

    @Override
    public void destroy() {
        em.close();
    }
}