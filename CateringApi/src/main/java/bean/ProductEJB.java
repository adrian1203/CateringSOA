package bean;
import domain.Category;
import domain.Position;
import ejb.ProductEJBInterface;
import repository.CategoryRepository;
import service.CategoryService;
import service.CateringUserService;
import service.PositionService;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Stateless
@Remote(ProductEJBInterface.class)
public class ProductEJB implements ProductEJBInterface {

    @EJB
    private CategoryService categoryService;

    @EJB
    private CategoryRepository categoryRepository;


    private PositionService positionService;
    Logger logger = Logger.getLogger(ProductEJB.class.getName());

    public List<Object> getAllCategory() {
        return new ArrayList<Object>(Arrays.asList(categoryService.getAllCategory().toArray()));
    }

    public List<Object> getAllPosition() {
        return new ArrayList<Object>(Arrays.asList(positionService.findAllApprovedPosition().toArray()));
    }

    public void deletePosition(Long id) {
        positionService.deletePosition(id);
    }

    public void deleteCategory(Long id) {
        categoryService.deleteCategory(id);
    }

    public void updateCategory(Object o) {
        categoryService.updateCategory((Category) o);
    }

    public void updatePosition(Object o) {
        positionService.updatePosition((Position) o);
    }

    public List<Object> getTopPosition() {
        return null;
    }

    public Object getDayPosition() {
        return null;
    }

    public void createCategory(String name, String description) {
        categoryService.createCategory(name, description);
    }

    public void createPosition(String name, String description, Float price, Long categoryId) {
        positionService.createPosition(name, description, price, categoryId, false);
        categoryRepository.ReInitFactory();
    }

    public Object getCategoryById(Long id){
        return categoryService.getCategoryById(id);
    }

    public Object getPositionById(Long id){
        return positionService.getPositionById(id);
    }

    public List<Object> getPossitionToApproved() {
        return new ArrayList<Object>(Arrays.asList(positionService.getPossitionToApproved().toArray()));
    }

    public void approvePossition(Long id){
        positionService.approvePossition(id);
    }


    public ProductEJB() {
        //this.categoryService = new CategoryService();
        this.positionService = new PositionService();
    }
}
