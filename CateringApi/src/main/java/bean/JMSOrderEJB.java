package bean;

import domain.OrderJMS;
import ejb.OrderMDBInterface;

import javax.ejb.Remote;
import javax.ejb.Singleton;
import java.io.Serializable;

@Singleton
@Remote(OrderMDBInterface.class)
public class JMSOrderEJB implements OrderMDBInterface, Serializable {

    private OrderJMS orderJMS;

    @Override
    public void AddOrderMessage(Object o) {
        orderJMS = (OrderJMS)o;
    }

    @Override
    public Object GetOrderMessage() {
        return orderJMS;
    }
}
