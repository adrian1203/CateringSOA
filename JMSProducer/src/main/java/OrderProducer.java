import JMS.OrderJMS;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.*;

@Stateless
@LocalBean
public class OrderProducer {

    @Resource(mappedName = "java:/topic/OrderTopic")
    Topic topic;

    @Inject
    @JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
    JMSContext jmsContext;

    public void sendOrder(OrderJMS order) throws JMSException {
        ObjectMessage objectMessage = jmsContext.createObjectMessage();
        objectMessage.setObject(order);
        jmsContext.createProducer().send(topic,objectMessage);
    }
}
