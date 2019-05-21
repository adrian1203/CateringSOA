package bean;
import domain.Category;
import domain.Position;
import ejb.ProductEJBInterface;
import service.CategoryService;
import service.PositionService;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Stateless
@Remote(ProductEJBInterface.class)
public class ProductEJB implements ProductEJBInterface {


    private CategoryService categoryService;
    private PositionService positionService;

    public List<Object> getAllCategory() {
        return new ArrayList<Object>(Arrays.asList(categoryService.getAllCategory().toArray()));
    }

    public List<Object> getAllPosition() {
        return new ArrayList<Object>(Arrays.asList(positionService.findAllPosition().toArray()));
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
        positionService.createPosition(name, description, price, categoryId);
    }

    public ProductEJB() {
        this.categoryService = new CategoryService();
        this.positionService = new PositionService();
    }
}
