package ejb;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface PermanentOrderIEJBnterface {

    public List<Object> getAllOrders();
    public Object getOrderById(Long id);
    public void updateOrder(Object order);
    public void createOrder(String additionalInformation, Set<Object> positionList, Long userID, Set<Object> permanetOrderDateList);
    public void updateStatusPermementOrder(Date deliveryDate, Long orderId);
    public List<Object> getFilteredOrderForUser(Long userId, Date start, Date end );

}
