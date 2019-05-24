package repository;

import domain.CateringUser;
import domain.PermanentOrder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class PermanentOrderRepository {

    private EntityManagerFactory factory;
    private EntityManager em;

    public PermanentOrderRepository() {
        factory = Persistence.createEntityManagerFactory("CateringJPA");
        em = factory.createEntityManager();
    }

    public List<PermanentOrder> findAllPermanentOrder() {
        Query query = em.createQuery("FROM PermanentOrder ", PermanentOrder.class);
        return query.getResultList();
    }

    public PermanentOrder findPermanentOrderById(Long id) {
        return em.find(PermanentOrder.class, id);
    }

    public void createPermanentOrder(PermanentOrder permanentOrder) {
        em.getTransaction().begin();
        em.persist(permanentOrder);
        em.getTransaction().commit();
    }

    public void updatePermanentOrder(PermanentOrder permanentOrder) {
        em.getTransaction().begin();
        em.merge(permanentOrder);
        em.getTransaction().commit();
    }

    public void deletePermanentOrder(Long id) {
        PermanentOrder permanentOrder = findPermanentOrderById(id);
        em.getTransaction().begin();
        em.remove(permanentOrder);
        em.getTransaction().commit();
    }
}
