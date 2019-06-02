package endpoint;


import domain.Category;
import domain.Order;
import domain.PermanetOrderDate;
import domain.Position;
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
        if (httpHeaders.getLanguage() != null) {
            return categoryService.translateCategory(httpHeaders.getLanguage().toString(), this.categoryService.getAllCategory());
        }
        return this.categoryService.getAllCategory();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Category getCategoryById(@PathParam("id") Long id, @Context HttpHeaders httpHeaders) {
        if (httpHeaders.getLanguage() != null) {
            return categoryService.translateCategory(httpHeaders.getLanguage().toString(), categoryService.getCategoryById(id));
        }
        return this.categoryService.getCategoryById(id);
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("dupa")
    public Category getCategoryById() {
//        Order order = new Order();
//        order.setPrice(12.69F);
//        order.setAdditionalInformation("Testujemmmyyyyy");
//        order.setOrderDate(new Date());
//        order.setCateringUser(cateringUserService.findUserById(303L));
        Set<Position> positionSet = new HashSet<>();
        positionSet.add( positionService.getPositionById(1L));
//        order.setPositionSet(positionSet);
//        Category category = new Category();
//        category.setDescription("hfghgfhgfh");
//        category.setName("yyyyyyy");
         //categoryService.createCategory("hgjgjhj","ytrytytryrt");

         orderService.createOrder(new Date(),"ghghg",positionSet, 303L);
         Set<PermanetOrderDate> permanetOrderDates = new HashSet<>();
         permanetOrderDates.add(new PermanetOrderDate());
         permanentOrderService.createOrder("tttt", positionSet,303L,permanetOrderDates);
//        Date date = new Date();
//        date.setYear(2000);
//        orderService.getFilteredOrderForUser(303L,date, new Date());
//        orderService.getAllOrderForUser(303L);
//
//        int a = orderService.getAllOrderForUser(303L).size();
//        int b = orderService.getFilteredOrderForUser(303L,date, new Date()).size();
//        int c = orderService.getAllOrders().size();
//        logger.info("PIIIIIIIIERWSZYYYYYYYYYYYYY");
//        logger.info(String.valueOf(a));
//        logger.info(String.valueOf(b));
//        logger.info(String.valueOf(c));
//        logger.info(String.valueOf(orderService.getAllOrderForUser(303L).size()));
//        logger.info(String.valueOf(orderService.getFilteredOrderForUser(303L,date, new Date()).size()));



        //positionService.getTopPosition();
        return null;
    }

    @EJB
    private CategoryService categoryService;
    private PositionService positionService;
    private CateringUserService cateringUserService;
    private PermanentOrderService permanentOrderService;
    private OrderService orderService;

    public ProductController() {

        positionService = new PositionService();
        cateringUserService=new CateringUserService();
        permanentOrderService = new PermanentOrderService();
        orderService = new OrderService();
    }


}
