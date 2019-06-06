package bean;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
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
    FacesContext fc = FacesContext.getCurrentInstance();

    private CateringUser loggedUser;

    @EJB(lookup = "java:global/CateringApi-1.0-SNAPSHOT/UserEJB")
    private UserEJBInterface userEJBInterface;


    public LoginBean(){
        userRole="customer";
    }

    public String CheckAuthorization(boolean enableRequiredRole, String requiredRole){
        if(loggedUser != null)
        {
            if(enableRequiredRole)
            {
                //Check if role allowed redirect
                switch (UserRole.valueOf(requiredRole))
                {
                    case CUSTOMER:
                            return null;
                    case ADMIN:
                        if(loggedUser.getUserRole() == UserRole.ADMIN)
                            return null;
                    case WORKER:
                        if(loggedUser.getUserRole() == UserRole.WORKER || loggedUser.getUserRole() == UserRole.ADMIN || loggedUser.getUserRole() == UserRole.MANAGER)
                            return null;
                    case MANAGER:
                        if(loggedUser.getUserRole() == UserRole.MANAGER || loggedUser.getUserRole() == UserRole.ADMIN)
                            return null;
                    case SUPPLIER:
                        if(loggedUser.getUserRole() == UserRole.SUPPLIER || loggedUser.getUserRole() == UserRole.ADMIN || loggedUser.getUserRole() == UserRole.MANAGER)
                            return null;
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

    public boolean CheckRoleAuthority(String requiredRole){
        if(CheckAuthorization(true,requiredRole) == null)
            return true;
        else
            return false;
    }


    public String ProcessLogin(){
        boolean success = userEJBInterface.logIn(login,password);
        if(success)
        {
            loggedUser = (CateringUser) userEJBInterface.GetLoggedUser();
            return "/catering_products.xhtml?faces-redirect=true";
        }
        else
        {
            FacesContext.getCurrentInstance().addMessage("form:buttonLogin", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Dane do logowania nieprawidłowe", "Błąd logowania. Sprawdź dane!"));
            return null;
        }
    }


    public String ProcessRegistration(){
        boolean success = userEJBInterface.register(login,password,firstName,lastName,email,city,street,flatNumber,userRole);
        if(success)
        {
            return "/login.xhtml?faces-redirect=true";
        }
        else
        {
            FacesContext.getCurrentInstance().addMessage("form:buttonRegister", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nieprawidłowe dane przy rejestracji", "Sprawdź dane!"));
            return null;
        }
    }

    public String Logout(){
        userEJBInterface.logOut();
        loggedUser=null;
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

    public CateringUser getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(CateringUser loggedUser) {
        this.loggedUser = loggedUser;
    }
}
