import JMS.OrderJMS;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.jms.JMSException;

@SessionScoped
@ManagedBean(name = "ProducerBean")
public class ProducerBean {

    @EJB
     OrderProducer orderProducer;

    public String SendMessage() throws JMSException {
        OrderJMS orderJMS = new OrderJMS();
        orderJMS.setId(1L);
        orderJMS.setUserId(1L);
        orderJMS.setAdidtionalInformation("Testt");
        orderProducer.sendOrder(orderJMS);
        return null;
    }

}
