package endpoint;


import com.sun.org.apache.xpath.internal.operations.Or;
import domain.*;
import repository.CategoryRepository;
import service.*;

import javax.ejb.EJB;
import javax.jws.soap.SOAPBinding;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    @Path("dupa")
    public Category getCategoryById() throws ParseException {
        cateringUserService.createUser("yyyyy", "uyuyuyt","yyyyy", "uyuyuyt","yyyyy", "uyuyuyt", "yyyyy", "uyuyuyt", "ADMIN");
//        Order order = new Order();
//        order.setPrice(12.69F);
//        order.setAdditionalInformation("Testujemmmyyyyy");
//        order.setOrderDate(new Date());
//        order.setCateringUser(cateringUserService.findUserById(303L));
           //Set<Position> positionSet = new HashSet<>();
//        positionSet.add( positionService.getPositionById(1L));
////        order.setPositionSet(positionSet);
////        Category category = new Category();
////        category.setDescription("hfghgfhgfh");
////        category.setName("yyyyyyy");
//         //categoryService.createCategory("hgjgjhj","ytrytytryrt");
//
          //positionSet.add(positionService.getPositionById(52L));
          //  positionSet.add(positionService.getPositionById(1L));

        Date date = new Date();
         date.setYear(2000);
       Bill bill= orderService.generateBill(1L,new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), new Date());



       logger.info(bill.getPrice().toString());

        //orderService.createOrder(new Date(),"ghghg",positionSet, 1L);
//         Set<PermanetOrderDate> permanetOrderDates = new HashSet<>();
//         permanetOrderDates.add(new PermanetOrderDate());
//         permanentOrderService.createOrder("tttt", positionSet,303L,permanetOrderDates);

        // positionService.setDayPosition(1L);

         //positionService.getDayPosition();
         //getDayPosition();
         //logger.info(positionService.getDayPosition().getName());
//        Date date = new Date();
//        date.setYear(2000);
          int size = orderService.getFilteredOrderForUser(1L,new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), new Date()).size();

          logger.info(String.valueOf(size));
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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("order")
    public List<Order> getOrdee() {
//        re
        return orderService.getAllOrders();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("per")
    public List<PermanentOrder> getper() {
//        re
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
