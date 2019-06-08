package bean;


import JMS.OrderJMS;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.jms.*;


@MessageDriven(name = "MyMDB",activationConfig = {
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/topic/OrderTopic"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable"),
        @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "myTopicSubscription")
})
public class CateringMessageBean implements MessageListener {

    public void onMessage(Message message) {
        try{
            if (message instanceof ObjectMessage) {

                ObjectMessage objectMessage = (ObjectMessage) message;
                OrderJMS orderJMS  = (OrderJMS)objectMessage.getObject();
                processOrder(orderJMS);
            }
        }catch (JMSException e){
            e.printStackTrace();
        }
    }

    public void processOrder(OrderJMS order){
        System.out.println("ORDER JMS: " + order.getAdidtionalInformation());
    }

}
