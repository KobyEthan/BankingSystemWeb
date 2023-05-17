package database;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class BankingSystemEM {

    private static final String BANKINGSYSTEMPU = "BankingSystemPU";
    private static EntityManagerFactory emf;

    public static EntityManagerFactory getEmFactory() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory(BANKINGSYSTEMPU);
        }
        return emf;
    }

    public static void closeEmFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

    public static void initialize() {
        getEmFactory();
    }
}