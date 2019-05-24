package bean;

import domain.PermanentOrder;
import domain.PermanetOrderDate;
import domain.Position;
import ejb.PermanentOrderIEJBnterface;
import service.PermanentOrderService;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.*;


@Stateless
@Remote(PermanentOrderIEJBnterface.class)
public class PermanentOrderIEJB implements PermanentOrderIEJBnterface {

    PermanentOrderService permanentOrderService;

    public PermanentOrderIEJB() {
        permanentOrderService = new PermanentOrderService();
    }

    @Override
    public List<Object> getAllOrders() {
        return new ArrayList<Object>(Arrays.asList(permanentOrderService.getAllOrders().toArray()));
    }

    @Override
    public Object getOrderById(Long id) {
        return permanentOrderService.getOrderById(id);
    }

    @Override
    public void updateOrder(Object o) {
        permanentOrderService.updateOrder((PermanentOrder) o);
    }

    @Override
    public void createOrder(String additionalInformation, Set<Object> positionList, Long userID, Set<Object> permanetOrderDateList) {

        Collection<Position> set = (Collection<Position>) (Collection<?>) positionList;
        Set<Position> positions = new HashSet<Position>(set);
        Collection<PermanetOrderDate> set2 = (Collection<PermanetOrderDate>) (Collection<?>) permanetOrderDateList;
        Set<PermanetOrderDate> permanetOrderDate = new HashSet<PermanetOrderDate>(set2);
        permanentOrderService.createOrder(additionalInformation, positions, userID, permanetOrderDate);

    }

    @Override
    public void updateStatusPermementOrder(Date date, Long aLong) {

    }

    @Override
    public List<Object> getFilteredOrderForUser(Long userId, Date start, Date end) {
        return new ArrayList<Object>(Arrays.asList(permanentOrderService.getFilteredOrderForUser(userId, start, end).toArray()));

    }
}
