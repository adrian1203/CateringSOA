package repository;

import domain.Position;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class PositionRepository {
    private EntityManagerFactory factory;
    private EntityManager em;

    public PositionRepository() {
        factory = Persistence.createEntityManagerFactory("CateringJPA");
        em = factory.createEntityManager();
    }

    public List<Position> findAllPosition() {
        Query query = em.createQuery("FROM Position ", Position.class);
        return query.getResultList();
    }

    public Position findPositionById(Long id) {
        return em.find(Position.class, id);
    }

    public void createPosition(Position position) {
        em.getTransaction().begin();
        em.persist(position);
        em.getTransaction().commit();
    }

    public void updatePosition(Position position) {
        em.getTransaction().begin();
        em.merge(position);
        em.getTransaction().commit();
    }

    public void deletePosition(Long id) {
        em.getTransaction().begin();
        em.remove(findPositionById(id));
        em.getTransaction().commit();
    }

    public void ReInitFactory(){
        factory = null;
        em=null;
        factory = Persistence.createEntityManagerFactory("CateringJPA");
        em = factory.createEntityManager();
    }

}
