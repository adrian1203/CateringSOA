package bean;


import lombok.val;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.*;

@MessageDriven(name = "MyMDB",activationConfig = {
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/topic/TestTopic"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable"),
        @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "myTopicSubscription")
})
public class CateringMessageBean implements MessageListener {

    public void onMessage(Message message){
        try{
            if(message instanceof TextMessage){
                TextMessage textMessage = (TextMessage) message;
                System.out.println("WIADOMOSC JMS: " + textMessage.getText());
            }
        }catch (JMSException e){
            e.printStackTrace();
        }
    }
}
