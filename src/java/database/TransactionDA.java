package database;

import domain.Transaction;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class TransactionDA {

    public static void add(Transaction t) {
        EntityManager em = BankingSystemEM.getEmFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    public static ArrayList<Transaction> getAllTransactions(EntityManager em) {
    try {
        TypedQuery<Transaction> query = em.createQuery("SELECT t FROM Transaction t", Transaction.class);
        return new ArrayList<>(query.getResultList());
    } finally {
        em.close();
    }
}
    public void create(Transaction transaction, EntityManager em) {
        EntityTransaction entityTransaction = em.getTransaction();
        try {
            entityTransaction.begin();
            em.persist(transaction);
            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
            throw e;
        }
    }
    

    public static Transaction find(String transactionID) {
        EntityManager em = BankingSystemEM.getEmFactory().createEntityManager();
        try {
            return em.find(Transaction.class, transactionID);
        } finally {
            em.close();
        }
    }

    public static ArrayList<Transaction> findTransactionsByAccountNumber(String accountNumber) {
        EntityManager em = BankingSystemEM.getEmFactory().createEntityManager();
        try {
            TypedQuery<Transaction> query = em.createQuery("SELECT t FROM Transaction t WHERE t.accountNumber = :accountNumber", Transaction.class);
            query.setParameter("accountNumber", accountNumber);
            return new ArrayList<>(query.getResultList());
        } finally {
            em.close();
        }
    }
    

    public static void update(Transaction t) {
        EntityManager em = BankingSystemEM.getEmFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(t);
            em.getTransaction().commit();
        } catch (EntityNotFoundException e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public static void delete(String transactionID) {
        EntityManager em = BankingSystemEM.getEmFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            Transaction t = em.find(Transaction.class, transactionID);
            if (t != null) {
                em.remove(t);
                em.getTransaction().commit();
            }
        } catch (EntityNotFoundException e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}