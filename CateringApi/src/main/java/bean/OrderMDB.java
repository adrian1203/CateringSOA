package bean;

import domain.OrderJMS;
import ejb.OrderMDBInterface;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.io.Serializable;

@MessageDriven(name = "MyMDB",activationConfig = {
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/topic/OrderTopic"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable"),
        @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "myTopicSubscription")
})
public class OrderMDB implements MessageListener, Serializable {

    @EJB(lookup = "java:global/CateringApi-1.0-SNAPSHOT/JMSOrderEJB")
    private OrderMDBInterface orderMDBInterface;

    public void onMessage(Message message) {
        try{
            if (message instanceof ObjectMessage) {

                ObjectMessage objectMessage = (ObjectMessage) message;
                OrderJMS orderJMS  = (OrderJMS)objectMessage.getObject();
                orderMDBInterface.AddOrderMessage(orderJMS);
            }
        }catch (JMSException e){
            e.printStackTrace();
        }
    }

}
