package database;

import domain.Account;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class AccountDA {
    private EntityManagerFactory emf;
    private EntityManager em;
    
        public AccountDA() {
        emf = Persistence.createEntityManagerFactory("BankingSystemPU");
        em = emf.createEntityManager();
    }

public static Account getAccountByNumber(String accountNumber) {
    EntityManager em = BankingSystemEM.getEmFactory().createEntityManager();
    try {
        return em.find(Account.class, accountNumber);
    } finally {
        em.close();
    }
}

public Account getAccount(String accountNumber) {
    try {
        Account account = em.find(Account.class, accountNumber);
        return account;
    } finally {
        em.close();
    }
}
    
public static List<Account> getAllAccounts(EntityManager em) {
        Query query = em.createQuery("SELECT a FROM Account a");
        return query.getResultList();
}

    public static List<Account> findAccountsByCustomerId(String customerId) {
        EntityManager em = BankingSystemEM.getEmFactory().createEntityManager();
        try {
            TypedQuery<Account> query = em.createQuery("SELECT a FROM Account a WHERE a.customerId = :customerId", Account.class);
            query.setParameter("customerId", customerId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

 public static void delete(String accountNumber) {
    if (accountNumber == null || accountNumber.isEmpty()) {
        throw new IllegalArgumentException("Account number cannot be null or empty");
    }

    EntityManager em = BankingSystemEM.getEmFactory().createEntityManager();
    EntityTransaction tx = em.getTransaction();
    try {
        tx.begin();
        Account account = em.find(Account.class, accountNumber);
        if (account != null) {
            em.remove(account);
        }
        tx.commit();
    } catch (RuntimeException e) {
        if (tx != null && tx.isActive()) {
            tx.rollback();
        }
        throw e;
    } finally {
        em.close();
    }
}

public void updateAccount(Account account, EntityManager em) {
    em.merge(account);
}

    public static void create(Account account) {
        EntityManager em = BankingSystemEM.getEmFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(account);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
}