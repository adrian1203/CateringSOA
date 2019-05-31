package repository;

import domain.CateringUser;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class CateringUserRepository {

    private EntityManagerFactory factory;
    private EntityManager em;

    public CateringUserRepository() {
        factory = Persistence.createEntityManagerFactory("CateringJPA");
        em = factory.createEntityManager();
    }

    public List<CateringUser> findAllUser() {
        Query query = em.createQuery("FROM CateringUser ", CateringUser.class);
        return query.getResultList();
    }

    public CateringUser findUserById(Long id) {
        return em.find(CateringUser.class, id);
    }

    public void createUser(CateringUser cateringUser) {
        em.getTransaction().begin();
        em.persist(cateringUser);
        em.getTransaction().commit();
    }

    public void updateUser(CateringUser cateringUser) {
        em.getTransaction().begin();
        em.persist(cateringUser);
        em.getTransaction().commit();
    }

    public void deleteUser(Long id) {
        CateringUser cateringUser = findUserById(id);
        em.getTransaction().begin();
        em.remove(cateringUser);
        em.getTransaction().commit();
    }

    public CateringUser findUserByLogin(String login){
        Query query = em.createQuery("SELECT u From CateringUser u where u.login =:login", CateringUser.class).setParameter("login",login);
        if(query.getResultList().isEmpty())
            return null;
        else
            return (CateringUser)query.getSingleResult();
    }
}
