package database;

import domain.Customer;
import exception.RecordNotFoundException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class CustomerDA {

    private EntityManagerFactory emf;
    private EntityManager em;

    public CustomerDA() {
        emf = Persistence.createEntityManagerFactory("BankingSystemPU");
        em = emf.createEntityManager();
    }

    // Create a new customer
    public void createCustomer(Customer customer) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(customer);
        transaction.commit();
}

    // Update an existing customer
    public void updateCustomer(Customer customer, EntityManager em) {
        em.merge(customer);
    }

    // Delete a customer
    public void deleteCustomer(Customer customer) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(customer);
        transaction.commit();
    }

    // Find a customer by ID
    public Customer findCustomerById(String customerId) {
        return em.find(Customer.class, customerId);
    }

    // Find all customers
    public static List<Customer> getAllCustomers(EntityManager em) {
        Query query = em.createQuery("SELECT c FROM Customer c");
        return query.getResultList();
    }

    // Find a customer by username and password
public static Customer findCustomerByUserIdandPssword(EntityManager em, String userId, String password) throws RecordNotFoundException {
    TypedQuery<Customer> query = em.createQuery(
        "SELECT c FROM Customer c WHERE c.userId = :userId AND c.password = :password", Customer.class);
    query.setParameter("userId", userId);
    query.setParameter("password", password);
    try {
        return query.getSingleResult();
    } catch (NoResultException ex) {
        throw new RecordNotFoundException("No customer found for the given userId and password.");
    } finally{
        em.close();
    }
}

    // Close the EntityManager and EntityManagerFactory
    public void close() {
        em.close();
        emf.close();
    }
}
