package repository;

import domain.CateringUser;
import domain.Order;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

@RequestScoped
public class OrderRepository {

    private EntityManagerFactory factory;
    private EntityManager em;

    public OrderRepository() {
        factory = Persistence.createEntityManagerFactory("CateringJPA");
        em = factory.createEntityManager();
    }

    public List<CateringUser> findAllOrder() {
        Query query = em.createQuery("FROM Order ", Order.class);
        return query.getResultList();
    }

    public Order findOrderOrderById(Long id) {
        return em.find(Order.class, id);
    }

    public void createOrder(Order order) {
        em.getTransaction().begin();
        em.persist(order);
        em.getTransaction().commit();
    }

    public void updateOrder(Order cateringUser) {
        em.getTransaction().begin();
        em.merge(cateringUser);
        em.getTransaction().commit();
    }

    public void deleteOrder(Long id) {
        Order order = findOrderOrderById(id);
        em.getTransaction().begin();
        em.remove(order);
        em.getTransaction().commit();
    }
}
