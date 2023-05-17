package servlets;
import database.AccountDA;
import domain.Account;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import database.BankingSystemEM;

public class DisplayAccountsServlet extends HttpServlet {

    private final EntityManagerFactory emf = BankingSystemEM.getEmFactory();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String customerId = (String) session.getAttribute("customerId");

        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            List<Account> accounts = AccountDA.findAccountsByCustomerId(customerId);
            for (Account account : accounts) {
            double balance = Account.getAccountBalance(account.getAccountNumber());
            account.setBalance(balance);
                    }
            request.setAttribute("accounts", accounts);
            System.out.println("accounts size:" + accounts.size());
            request.getRequestDispatcher("/accounts.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(DisplayAccountsServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}