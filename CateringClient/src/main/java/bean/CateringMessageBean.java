package bean;


import JMS.OrderJMS;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.jms.*;

@MessageDriven(name = "MyMDB",activationConfig = {
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/topic/OrderTopic"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable"),
        @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "myTopicSubscription")
})
public class CateringMessageBean implements MessageListener {

//    @EJB(lookup = "java:global/CateringClient_war_exploded/bean.LoginBean")
//    LoginBean loginBean;

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
        //loginBean.SayHello();
    }
}
