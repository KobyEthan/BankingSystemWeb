package servlets;

import database.BankingSystemEM;
import database.UserRoleDA;
import domain.UserRole;
import exception.RecordNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManageUserRolesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EntityManagerFactory emf;

    public ManageUserRolesServlet() {
        emf = BankingSystemEM.getEmFactory();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();

        List<UserRole> allRoles = UserRoleDA.getAllRoles(em);

        em.close();

        request.setAttribute("allRoles", allRoles);

        request.getRequestDispatcher("/manage_user_roles.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            UserRoleDA userRoleDA = new UserRoleDA();
            UserRole newRole = new UserRole();
            
            newRole.setRoleType(request.getParameter("roleType"));
            newRole.setAllUsersPower("on".equals(request.getParameter("allUsersPower")));
            newRole.setViewAccounts("on".equals(request.getParameter("viewAccounts")));
            newRole.setEditAccounts("on".equals(request.getParameter("editAccounts")));
            newRole.setCreateAccounts("on".equals(request.getParameter("createAccounts")));
            newRole.setViewTransactions("on".equals(request.getParameter("viewTransactions")));
            newRole.setTransferFunds("on".equals(request.getParameter("transferFunds")));
            newRole.setViewCustomers("on".equals(request.getParameter("viewCustomers")));
            newRole.setEditCustomers("on".equals(request.getParameter("editCustomers")));
            newRole.setCreateCustomers("on".equals(request.getParameter("createCustomers")));
            newRole.setCreateUserRoles("on".equals(request.getParameter("createUserRoles")));

            userRoleDA.addUserRole(newRole);

            userRoleDA.close();

            List<UserRole> allRoles = UserRoleDA.getAllRoles(em);
            request.setAttribute("allRoles", allRoles);
            request.getRequestDispatcher("/manage_user_roles.jsp?message=success").forward(request, response);

        } else if ("delete".equals(action)) {
            UserRoleDA userRoleDA = new UserRoleDA();
            String roleType = request.getParameter("roleType");
            try {
                userRoleDA.deleteUserRoleByRoleType(roleType);
                request.setAttribute("message", "User role deleted successfully.");
            } catch (RecordNotFoundException e) {
                request.setAttribute("message", "Unable to delete user role. User role not found.");
            }

            userRoleDA.close();

            List<UserRole> allRoles = UserRoleDA.getAllRoles(em);
            request.setAttribute("allRoles", allRoles);
            request.getRequestDispatcher("/manage_user_roles.jsp").forward(request, response);
        }
        em.close();
    }
}