package repository;

import domain.Category;

import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

@Singleton
public class CategoryRepository {

    private EntityManagerFactory factory;
    private EntityManager em;

    public CategoryRepository() {
        factory = Persistence.createEntityManagerFactory("CateringJPA");
        em = factory.createEntityManager();
    }

    public List<Category> findAllCategory() {
        Query query = em.createQuery("FROM Category ", Category.class);
        return query.getResultList();
    }

    public Category findCategoryById(Long id) {
        return em.find(Category.class, id);
    }

    public void createCategory(Category category) {
        em.getTransaction().begin();
        em.persist(category);
        em.getTransaction().commit();
    }

    public void updateCategory(Category category) {
        em.getTransaction().begin();
        em.merge(category);
        em.getTransaction().commit();
    }

    public void deleteCategory(Long id){
        Category category = findCategoryById(id);
        try{
            em.getTransaction().begin();
            em.remove(category);
            em.getTransaction().commit();
        }catch (Exception e) {}
    }

    public void ReInitFactory(){
        factory = null;
        em=null;
        factory = Persistence.createEntityManagerFactory("CateringJPA");
        em = factory.createEntityManager();
    }
}
