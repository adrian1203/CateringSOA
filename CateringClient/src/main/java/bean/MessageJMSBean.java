package bean;

import domain.OrderJMS;
import ejb.OrderMDBInterface;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(name = "MessageJMSBean")
public class MessageJMSBean {


    private OrderJMS orderJMS;

    @EJB(lookup = "java:global/CateringApi-1.0-SNAPSHOT/JMSOrderEJB")
    private OrderMDBInterface orderMDBInterface;


    public String GetJMSMessage(){
        if(orderJMS != null){
            return "zamówienie numer " + orderJMS.getId() + " zmieniło status na " + orderJMS.getAdidtionalInformation();
        }
        else
        {
            return "brak powiadomień";
        }
    }

    public void FilterJMS(Long userID){
        if(orderMDBInterface.GetOrderMessage() != null)
        {
            OrderJMS or = (OrderJMS) orderMDBInterface.GetOrderMessage();
            if(or.getUserId().equals(userID))
                orderJMS = or;
        }
    }
}
