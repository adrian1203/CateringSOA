package bean;

import domain.CateringUser;
import ejb.UserEJBInterface;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SessionScoped
@ManagedBean(name = "UserPanelBean")
public class UserPanelBean implements Serializable {

    @EJB(lookup = "java:global/CateringApi-1.0-SNAPSHOT/UserEJB")
    private UserEJBInterface userEJBInterface;


    private String changePassword;
    private Long changePasswordUser;
    private String changeSelectedPassword;

    public UserPanelBean(){

    }


    public void ChangePassword(){
        if(changePassword.length() > 5)
        userEJBInterface.changePassword(changePassword,changePassword); //TODO method api not work
    }

    public void ChangePasswordByAdmin(){
        if(changePasswordUser != null && changePasswordUser != -1L && changePassword.length() > 5){
            userEJBInterface.changePasswordByAdmin(changePassword,changePasswordUser); //TODO method api not work
        }
    }

    public List<CateringUser> GetUsersByAdmin(){
        return new ArrayList<CateringUser>();
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
}
