package bean;

import domain.Bill;
import domain.CateringUser;
import domain.PermanentOrder;
import domain.PermanetOrderDate;
import ejb.OrderEJBInterface;
import ejb.PermanentOrderIEJBnterface;
import ejb.UserEJBInterface;
import org.omg.CORBA.PUBLIC_MEMBER;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@SessionScoped
@ManagedBean(name = "UserPanelBean")
public class UserPanelBean implements Serializable {

    @EJB(lookup = "java:global/CateringApi-1.0-SNAPSHOT/UserEJB")
    private UserEJBInterface userEJBInterface;

    @EJB(lookup = "java:global/CateringApi-1.0-SNAPSHOT/OrderEJB")
    private OrderEJBInterface orderEJBInterface;

    @EJB(lookup = "java:global/CateringApi-1.0-SNAPSHOT/PermanentOrderIEJB")
    private PermanentOrderIEJBnterface permanentOrderIEJBnterface;


    private String changePassword;
    private Long changePasswordUser;
    private String changeSelectedPassword;

    private Date billDateFrom;
    private Date billDateTo;
    private Bill bill;

    public UserPanelBean(){
    }


    public String ChangePassword(Long UserId){
        if(changePassword != null)
        {
            userEJBInterface.changePasswordByAdmin(changePassword,UserId);
            userEJBInterface.logOut();
            return null;
        }
        else
            return null;

    }

    public String ChangePasswordByAdmin(){
        if(changePasswordUser != null && changePasswordUser != -1L){
            userEJBInterface.changePasswordByAdmin(changeSelectedPassword,changePasswordUser);
            return null;
        }
        return null;
    }

    public List<Object> GetUsersByAdmin(){
        return userEJBInterface.findAllUser();
    }


    public String GenerateBill(Long idUser){
        bill = (Bill)orderEJBInterface.generateBill(idUser,billDateFrom,billDateTo);
        if(bill != null)
            return "/bill.xhtml";
        else
            return "";
    }

    public String PrintBill(){
        return "";
    }

    public String GetBillPoisiton(){
        String positions="";
        for(String o : bill.getOrderedPosition()){
            positions += "*"+o+"\n";
        }
        return positions;
    }

    public String GetDateFromSet(Set<PermanetOrderDate> dates){
        String out="";
        for(PermanetOrderDate d : dates){
            out+= d.getOrderDate().toString() + " * ";
        }
        return out;
    }

    ///GET&SET

    public String getChangePassword() {
        return changePassword;
    }

    public void setChangePassword(String changePassword) {
        this.changePassword = changePassword;
    }

    public UserEJBInterface getUserEJBInterface() {
        return userEJBInterface;
    }

    public void setUserEJBInterface(UserEJBInterface userEJBInterface) {
        this.userEJBInterface = userEJBInterface;
    }

    public Long getChangePasswordUser() {
        return changePasswordUser;
    }

    public void setChangePasswordUser(Long changePasswordUser) {
        this.changePasswordUser = changePasswordUser;
    }

    public String getChangeSelectedPassword() {
        return changeSelectedPassword;
    }

    public void setChangeSelectedPassword(String changeSelectedPassword) {
        this.changeSelectedPassword = changeSelectedPassword;
    }

    public OrderEJBInterface getOrderEJBInterface() {
        return orderEJBInterface;
    }

    public void setOrderEJBInterface(OrderEJBInterface orderEJBInterface) {
        this.orderEJBInterface = orderEJBInterface;
    }

    public PermanentOrderIEJBnterface getPermanentOrderIEJBnterface() {
        return permanentOrderIEJBnterface;
    }

    public void setPermanentOrderIEJBnterface(PermanentOrderIEJBnterface permanentOrderIEJBnterface) {
        this.permanentOrderIEJBnterface = permanentOrderIEJBnterface;
    }

    public Date getBillDateFrom() {
        return billDateFrom;
    }

    public void setBillDateFrom(Date billDateFrom) {
        this.billDateFrom = billDateFrom;
    }

    public Date getBillDateTo() {
        return billDateTo;
    }

    public void setBillDateTo(Date billDateTo) {
        this.billDateTo = billDateTo;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }
}
