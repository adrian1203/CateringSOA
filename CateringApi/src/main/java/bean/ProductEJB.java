package bean;
import domain.Category;
import domain.Position;
import ejb.ProductEJBInterface;
import repository.CategoryRepository;
import repository.PositionRepository;
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


    private PositionRepository positionRepository;
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
        positionRepository.ReInitFactory();
    }

    public void deleteCategory(Long id) {
        categoryService.deleteCategory(id);
        categoryRepository.ReInitFactory();
    }

    public void updateCategory(Object o) {
        categoryService.updateCategory((Category) o);
        categoryRepository.ReInitFactory();
    }

    public void updatePosition(Object o) {
        positionService.updatePosition((Position) o);
        categoryRepository.ReInitFactory();
    }

    public List<Object> getTopPosition() {
        return null;
    }

    public Object getDayPosition() {
       return positionService.getDayPosition();
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
        categoryRepository.ReInitFactory();
    }


    public void setDayPossition(Long possitionId) {
        positionService.setDayPosition(possitionId);
    }


    public ProductEJB() {
        this.positionService = new PositionService();
        this.positionRepository = new PositionRepository();
    }
}
