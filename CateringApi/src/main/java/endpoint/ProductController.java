package endpoint;


import domain.*;
import repository.CategoryRepository;
import service.*;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.util.*;
import java.util.logging.Logger;

@Path("categories")
public class ProductController {


    Logger logger = Logger.getLogger(ProductController.class.getName());

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> getAllCategory(@Context HttpHeaders httpHeaders) {
        List<Category> categories= new LinkedList<>();
        if (httpHeaders.getLanguage() != null) {
            categories= categoryService.translateCategory(httpHeaders.getLanguage().toString(), this.categoryService.getAllCategory());
        }
        categories = this.categoryService.getAllCategory();
        categoryRepository.ReInitFactory();
        return categories;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Category getCategoryById(@PathParam("id") Long id, @Context HttpHeaders httpHeaders) {
        Category category;
        if (httpHeaders.getLanguage() != null) {
            category =categoryService.translateCategory(httpHeaders.getLanguage().toString(), categoryService.getCategoryById(id));
        }
        categoryRepository.ReInitFactory();
        category =this.categoryService.getCategoryById(id);
        return category;
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("order")
    public List<Order> getOrdee() {
        return orderService.getAllOrders();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("per")
    public List<PermanentOrder> getper() {
        return permanentOrderService.getAllOrders();
    }

    @EJB
    private CategoryService categoryService;
    private PositionService positionService;
    private CateringUserService cateringUserService;
    private PermanentOrderService permanentOrderService;
    private OrderService orderService;


    @EJB
    private CategoryRepository categoryRepository;

    public ProductController() {

        positionService = new PositionService();
        cateringUserService=new CateringUserService();
        permanentOrderService = new PermanentOrderService();
        orderService = new OrderService();
    }


    public Object getDayPosition() {
        return positionService.getDayPosition();
    }


}
