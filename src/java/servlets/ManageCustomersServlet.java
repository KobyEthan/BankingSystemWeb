package servlets;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.BankingSystemEM;
import database.CustomerDA;
import domain.Customer;

public class ManageCustomersServlet extends HttpServlet {
    private EntityManagerFactory emf;

    public ManageCustomersServlet() {
        emf = BankingSystemEM.getEmFactory();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();

        List<Customer> customers = CustomerDA.getAllCustomers(em);

        em.close();

        request.setAttribute("customers", customers);

        request.getRequestDispatcher("/manage_customers.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();

        String action = request.getParameter("action");

        if ("create".equals(action)) {
            CustomerDA customerDA = new CustomerDA();
            Customer customer = new Customer();
            
            customer.setFirstName(request.getParameter("firstName"));
            customer.setLastName(request.getParameter("lastName"));
            customer.setPhoneNumber(request.getParameter("phoneNumber"));
            customer.setUserId(request.getParameter("userId"));
            customer.setPassword(request.getParameter("password"));
            customer.setRoleType(request.getParameter("roleType"));


            customerDA.createCustomer(customer);

            customerDA.close();
            
            List<Customer> customers = customerDA.getAllCustomers(em);
            request.setAttribute("customers", customers);
            request.getRequestDispatcher("/manage_customers.jsp?message=success").forward(request, response);


        } else if ("delete".equals(action)) {
         CustomerDA customerDA = new CustomerDA();
        String customerId = request.getParameter("customerId");
        Customer customer = customerDA.findCustomerById(customerId);

        if (customer != null) {
            customerDA.deleteCustomer(customer);
            request.setAttribute("message", "Customer deleted successfully.");
        } else {
            request.setAttribute("message", "Unable to delete customer. Customer not found.");
        }

        List<Customer> customers = customerDA.getAllCustomers(em);
        request.setAttribute("customers", customers);
        request.getRequestDispatcher("/manage_customers.jsp").forward(request, response);
    }

        em.close();
      }
    }