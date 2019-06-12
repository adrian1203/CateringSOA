
import domain.OrderJMS;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.jms.JMSException;

@SessionScoped
@ManagedBean(name = "ProducerBean")
public class ProducerBean {

    private Long userID;
    private Long orderID;
    private String additionalInfo;


    @EJB
     OrderProducer orderProducer;

    public String SendMessage() throws JMSException {
        OrderJMS orderJMS = new OrderJMS();
        orderJMS.setId(orderID);
        orderJMS.setUserId(userID);
        orderJMS.setAdidtionalInformation(additionalInfo);
        orderProducer.sendOrder(orderJMS);
        return null;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
