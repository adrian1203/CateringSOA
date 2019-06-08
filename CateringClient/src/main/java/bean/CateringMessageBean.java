package bean;


import JMS.OrderJMS;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.jms.*;

@ApplicationScoped
@ManagedBean(name = "MesBean")
@MessageDriven(name = "MyMDB",activationConfig = {
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/topic/OrderTopic"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable"),
        @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "myTopicSubscription")
})
public class CateringMessageBean implements MessageListener {

    private OrderJMS orderJMS;
    private int count=0;

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
        System.out.println("PRYZPISANIE " + order.getAdidtionalInformation());
        orderJMS = order;
        System.out.println("PO PRZYP: " + orderJMS.getAdidtionalInformation());
    }

    public void FilterOrderJMS(Long userId){
        count++;
        if(orderJMS != null){
            System.out.println("WIADOM" + orderJMS.getAdidtionalInformation());
//            if(orderJMS.getUserId() != userId) {
//                orderJMS = null;
//            }
        }
        System.out.println("FILTRACJA " + count);
    }

    public OrderJMS getOrderJMS() {
        return orderJMS;
    }

    public void setOrderJMS(OrderJMS orderJMS) {
        this.orderJMS = orderJMS;
    }

    public String GetOrderMessage(){
        System.out.println("MEsSS ");
        if(orderJMS != null){
            return String.valueOf(orderJMS.getUserId());
        }
        else
            return "";
    }
}
