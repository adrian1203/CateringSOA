package ejb;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface OrderEJBInterface {

    public void createOrder(Date deliverDate, String additionalInformation, Set<Object> positionList, Long userID);
    public void updateOrder(Object order);
    public void changeOrderStatus(String status, Long orderId);
    public List<Object> getAllOrders();
    public List<Object> getOrderByStatus(String status);
    public Object getOrderById(Long id);
    public List<Object> getFilteredOrderForUser(Long userId, Date start, Date end );
    public List<Object> getOrdereForUser(Long userId);
    public Object generateBill(Long userId, Date start, Date end);


}
