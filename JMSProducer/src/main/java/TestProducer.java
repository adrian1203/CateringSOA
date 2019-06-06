import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Topic;

@Stateless
@LocalBean
public class TestProducer {

    @Resource(mappedName = "java:/topic/TestTopic")
    Topic topic;

    @Inject
    @JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
    JMSContext jmsContext;

    public void sendTopic(String message) {
        jmsContext.createProducer().send(topic,message);
    }

}
