import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean(name = "ProducerBean")
public class ProducerBean {

    @EJB
    TestProducer testProducer;

    public String SendMessage(){
        testProducer.sendTopic("HELLO KUBA");
        return null;
    }

}
