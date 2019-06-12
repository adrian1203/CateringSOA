package bean;

import domain.Order;
import domain.Position;
import ejb.OrderEJBInterface;
import org.omg.PortableServer.POA;
import service.OrderService;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.*;


@Stateless
@Remote(OrderEJBInterface.class)
public class OrderEJB implements OrderEJBInterface {


    OrderService orderService;

    public OrderEJB(){
        orderService = new OrderService();
    }

    @Override
    public void createOrder(Date deliverDate, String additionalInformation, Set<Object> positionList, Long userID) {

        Collection<Position> set = (Collection<Position>)(Collection<?>)positionList;
        Set<Position> positions = new HashSet<Position>(set);
        orderService.createOrder(deliverDate,additionalInformation,positions, userID );
    }

    @Override
    public void updateOrder(Object o) {
        orderService.updateOrder((Order) o);
    }

    @Override
    public void changeOrderStatus(String status, Long id) {
        orderService.changeOrderStatus(status,id);
    }

    @Override
    public List<Object> getAllOrders() {
       return new ArrayList<Object>(Arrays.asList(orderService.getAllOrders().toArray()));
    }

    @Override
    public List<Object> getOrderByStatus(String status) {
        return new ArrayList<Object>(Arrays.asList(orderService.getOrderByStatus(status).toArray()));

    }

    @Override
    public Object getOrderById(Long id) {
        return  orderService.getOrderById(id);
    }

    @Override
    public List<Object> getFilteredOrderForUser(Long userId, Date start, Date end) {
        return new ArrayList<Object>(Arrays.asList(orderService.getFilteredOrderForUser(userId, start, end).toArray()));
    }

    public List<Object> getOrdereForUser(Long userId){
        return new ArrayList<Object>(Arrays.asList(orderService.getAllOrderForUser(userId).toArray()));

    }

    @Override
    public Object generateBill(Long userId, Date start, Date end) {
        return orderService.generateBill(userId, start, end);
    }
}
