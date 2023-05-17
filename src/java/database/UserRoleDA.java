package database;

import domain.UserRole;
import exception.RecordNotFoundException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class UserRoleDA {
    
    private EntityManagerFactory emf;
    private EntityManager em;
    
    public UserRoleDA() {
        emf = Persistence.createEntityManagerFactory("BankingSystemPU");
        em = emf.createEntityManager();
    }

    public static List<UserRole> getAllRoles(EntityManager em) {
        TypedQuery<UserRole> query = em.createQuery("SELECT ur FROM UserRole ur", UserRole.class);
        List<UserRole> roles = query.getResultList();
        return roles;
    }    
    
    
public UserRole findUserRoleByRoleType(String roleType) {
    TypedQuery<UserRole> query = em.createQuery("SELECT ur FROM UserRole ur WHERE ur.roleType = :roleType", UserRole.class);
    query.setParameter("roleType", roleType);
    List<UserRole> userRoles = query.getResultList();
    if (userRoles.isEmpty()) {
        return null;
    } else {
        return userRoles.get(0);
    }
}
public void deleteUserRoleByRoleType(String roleType) throws RecordNotFoundException {
    EntityManager em = emf.createEntityManager();
    try {
        em.getTransaction().begin();
        UserRole userRole = em.find(UserRole.class, roleType);
        if (userRole == null) {
            throw new RecordNotFoundException("Role does not exist");
        }
        em.remove(em.merge(userRole));
        em.getTransaction().commit();
    } finally {
        em.close();
    }
}
    
    public void addUserRole(UserRole userRole) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    em.persist(userRole);
    em.getTransaction().commit();
    em.close();
}
public void updateUserRole(UserRole userRole, EntityManager em) throws RecordNotFoundException {
    em.merge(userRole);
}
        public void close() {
        em.close();
        emf.close();
    }
    
}