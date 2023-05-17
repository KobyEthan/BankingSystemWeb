package servlets;

import database.BankingSystemEM;
import database.CustomerDA;
import domain.Customer;
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

public class EditCustomersServlet extends HttpServlet {
    private EntityManager em;
    private CustomerDA customerDA;

    public EditCustomersServlet() {
        EntityManagerFactory emf = BankingSystemEM.getEmFactory();
        em = emf.createEntityManager();
        customerDA = new CustomerDA();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String customerId = request.getParameter("customerId");
        HttpSession session = request.getSession();
        session.setAttribute("customerToUpdateId", customerId);
        Customer customerToUpdate = customerDA.findCustomerById(customerId);

        session.setAttribute("customerToUpdate", customerToUpdate);
        response.sendRedirect("edit_customers.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String customerId = (String) request.getSession().getAttribute("customerToUpdateId");
        Customer customerToUpdate = customerDA.findCustomerById(customerId);

        if (customerToUpdate != null) {
            try {
                em.getTransaction().begin();
                customerToUpdate.setFirstName(request.getParameter("firstName"));
                customerToUpdate.setLastName(request.getParameter("lastName"));
                customerToUpdate.setPhoneNumber(request.getParameter("phoneNumber"));
                customerToUpdate.setUserId(request.getParameter("userId"));
                customerToUpdate.setPassword(request.getParameter("password"));
                customerToUpdate.setRoleType(request.getParameter("roleType"));

                customerDA.updateCustomer(customerToUpdate, em);
                em.flush();
                em.getTransaction().commit();

                request.getSession().setAttribute("message", "Customer edit successful");
                request.getRequestDispatcher("/edit_customers.jsp").forward(request, response);
            } catch (Exception e) {
                Logger.getLogger(EditCustomersServlet.class.getName()).log(Level.SEVERE, "Error updating customer", e);
                em.getTransaction().rollback();
                request.getSession().setAttribute("message", "Unable to edit customer. Please try again later.");
                response.sendRedirect(request.getContextPath() + "/manage_customers.jsp");
            }
        } else {
            request.getSession().setAttribute("message", "Unable to edit customer. Customer not found.");
            response.sendRedirect(request.getContextPath() + "/manage_customers.jsp");
        }
    }

    @Override
    public void destroy() {
        em.close();
    }
}