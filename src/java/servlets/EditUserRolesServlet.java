package servlets;

import database.BankingSystemEM;
import database.UserRoleDA;
import domain.UserRole;
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
import javax.servlet.http.HttpSession;

public class EditUserRolesServlet extends HttpServlet {
    private EntityManager em;
    private UserRoleDA userRoleDA;

    public EditUserRolesServlet() {
        EntityManagerFactory emf = BankingSystemEM.getEmFactory();
        em = emf.createEntityManager();
        userRoleDA = new UserRoleDA();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String roleType = request.getParameter("roleType");
        System.out.println(roleType);
        HttpSession session = request.getSession();
        UserRole userRoleToUpdate = userRoleDA.findUserRoleByRoleType(roleType);
        
        session.setAttribute("userRoleToUpdate", userRoleToUpdate);
        response.sendRedirect("edit_user_roles.jsp");
    }

  @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String roleType = (String) request.getSession().getAttribute("userRoleToUpdateType");
    UserRole userRoleToUpdate = userRoleDA.findUserRoleByRoleType(roleType);

    if (userRoleToUpdate != null) {
        try {
            em.getTransaction().begin();
            userRoleToUpdate.setAllUsersPower(Boolean.parseBoolean(request.getParameter("allUsersPower")));
            userRoleToUpdate.setCreateAccounts(Boolean.parseBoolean(request.getParameter("createAccounts")));
            userRoleToUpdate.setCreateCustomers(Boolean.parseBoolean(request.getParameter("createCustomers")));
            userRoleToUpdate.setCreateUserRoles(Boolean.parseBoolean(request.getParameter("createUserRoles")));
            userRoleToUpdate.setEditAccounts(Boolean.parseBoolean(request.getParameter("editAccounts")));
            userRoleToUpdate.setEditCustomers(Boolean.parseBoolean(request.getParameter("editCustomers")));
            userRoleToUpdate.setTransferFunds(Boolean.parseBoolean(request.getParameter("transferFunds")));
            userRoleToUpdate.setViewAccounts(Boolean.parseBoolean(request.getParameter("viewAccounts")));
            userRoleToUpdate.setViewCustomers(Boolean.parseBoolean(request.getParameter("viewCustomers")));
            userRoleToUpdate.setViewTransactions(Boolean.parseBoolean(request.getParameter("viewTransactions")));

            userRoleDA.updateUserRole(userRoleToUpdate, em);
            em.flush();
            em.getTransaction().commit();

            List<UserRole> allRoles = userRoleDA.getAllRoles(em);
            request.setAttribute("allRoles", allRoles);
            request.getSession().setAttribute("message", "User role edit successful");
            request.getRequestDispatcher("/edit_user_roles.jsp").forward(request, response);
        } catch (Exception e) {
            Logger.getLogger(EditUserRolesServlet.class.getName()).log(Level.SEVERE, "Error updating user role", e);
            em.getTransaction().rollback();
            request.getSession().setAttribute("message", "Unable to edit user role. Please try again later.");
            response.sendRedirect(request.getContextPath() + "/manage_user_roles.jsp");
        }
    } else {
        request.getSession().setAttribute("message", "Unable to edit user role. User role not found.");
        response.sendRedirect(request.getContextPath() + "/manage_user_roles.jsp");
    }
}

    @Override
    public void destroy() {
        em.close();
    }
}