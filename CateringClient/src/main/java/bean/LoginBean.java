package bean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

import domain.CateringUser;
import domain.UserRole;
import ejb.UserEJBInterface;

@SessionScoped
@ManagedBean(name = "LoginBean")
public class LoginBean implements Serializable {

    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String city;
    private String street;
    private String flatNumber;
    private String userRole;

    private CateringUser loggedUser;

    @EJB(lookup = "java:global/CateringApi-1.0-SNAPSHOT/UserEJB")
    private UserEJBInterface userEJBInterface;


    public LoginBean(){
        userRole="customer";
    }

    public String CheckAuthorization(boolean enableRequiredRole, UserRole requiredRole){
        if(loggedUser != null)
        {
            if(enableRequiredRole && requiredRole != null)
            {
                //Check if role allowed redirect
                switch (requiredRole)
                {
                    case CUSTOMER:
                        if(loggedUser.getUserRole().equals("CUSTOMER"))
                            return null;
                            break;
                    case ADMIN:
                        if(loggedUser.getUserRole().equals("ADMIN"))
                            return null;
                        break;
                    case WORKER:
                        if(loggedUser.getUserRole().equals("WORKER"))
                            return null;
                        break;
                    case MANAGER:
                        if(loggedUser.getUserRole().equals("MANAGER"))
                            return null;
                        break;
                    case SUPPLIER:
                        if(loggedUser.getUserRole().equals("SUPPLIER"))
                            return null;
                        break;
                }
                //If not success redirect to main page
                return "/catering_products.xhtml?faces-redirect=true";
            }
            else
                return null;
        }
        else
        {
            //If not loged redirect to login page
            return "/login.xhtml?faces-redirect=true";
        }
    }


    public String ProcessLogin(){
        userEJBInterface.logIn(login,password);
        loggedUser = (CateringUser) userEJBInterface.GetLoggedUser();
        return "/catering_products.xhtml?faces-redirect=true";
    }


    public String ProcessRegistration(){
        userEJBInterface.register(login,password,firstName,lastName,email,city,street,flatNumber,userRole);
        return "/login.xhtml?faces-redirect=true";
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
