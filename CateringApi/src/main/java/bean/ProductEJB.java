package bean;


import clojure.lang.Obj;
import domain.Position;
import service.CategoryService;
import service.PositionService;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Stateless
public class ProductEJB {


    private CategoryService categoryService;
    private PositionService positionService;

    public List<Object> getAllCategory(){
        return new ArrayList<Object>(Arrays.asList(categoryService.getAllCategory().toArray()));
    }

    public ProductEJB() {
        this.categoryService = new CategoryService();
        this.positionService = new PositionService();
    }
}
