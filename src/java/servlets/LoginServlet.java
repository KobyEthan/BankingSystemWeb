package servlets;

import database.AccountDA;
import database.BankingSystemEM;
import database.CustomerDA;
import database.UserRoleDA;
import domain.Customer;
import domain.UserRole;
import exception.RecordNotFoundException;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final EntityManagerFactory emf;

public LoginServlet() {
    super();
    emf = BankingSystemEM.getEmFactory();
    if (emf == null) {
        System.out.println("emf is null");
    }
}
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        BankingSystemEM.initialize();
        
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        EntityManager em = null;
        try {
            UserRoleDA userRoleDA = new UserRoleDA();
            em = emf.createEntityManager();
            System.out.println("User ID: " + userId);
            System.out.println("Password: " + password);
            Customer customer = CustomerDA.findCustomerByUserIdandPssword(em, userId, password);
            String roleType = customer.getRoleType();
            UserRole userRole = userRoleDA.findUserRoleByRoleType(roleType);
            HttpSession session = request.getSession();
            
            session.setAttribute("customer", customer);
            session.setAttribute("customerId", customer.getCustomerId());
            
            session.setAttribute("roleType", roleType);
            System.out.println("Role Type: " + customer.getRoleType());
            
            session.setAttribute("allUsersPower", userRole.getAllUsersPower());
            session.setAttribute("viewAccounts", userRole.getViewAccounts());
            session.setAttribute("editAccounts", userRole.getEditAccounts());
            session.setAttribute("createAccounts", userRole.getCreateAccounts());
            session.setAttribute("viewTransactions", userRole.getViewTransactions());
            session.setAttribute("transferFunds", userRole.getTransferFunds());
            session.setAttribute("viewCustomers", userRole.getViewCustomers());
            session.setAttribute("editCustomers", userRole.getEditCustomers());
            session.setAttribute("createCustomers", userRole.getCreateCustomers());
            session.setAttribute("createUserRoles", userRole.getCreateUserRoles());
            
            
            response.sendRedirect(request.getContextPath() + "/main_menu.jsp");
        } catch (RecordNotFoundException ex) {
            ex.printStackTrace();
            request.setAttribute("errorMessage", "Invalid UserId or Password.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } 
        
    }
}