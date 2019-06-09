package bean;


import domain.CateringUser;
import ejb.UserEJBInterface;
import service.CateringUserService;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Stateful
@Remote(UserEJBInterface.class)
public class UserEJB implements UserEJBInterface {

    private CateringUser logedUser;

    @EJB
    CateringApplicationManager cateringApplicationManager;

    @EJB
    private CateringUserService cateringUserService;

    public Boolean logIn(String login, String haslo) {
        //todo chyba trzeba zwracać stringa czy nieprawidowe dane, czy jest już zalogowany gdzieś
        List<CateringUser> loggedUsers = cateringApplicationManager.getLoggedUsers();
        CateringUser cateringUser = this.cateringUserService.logIn(login, haslo);
        if (cateringUser == null) {
            return false;
        }
        if(loggedUsers!=null){
            if (loggedUsers.contains(cateringUser)) {
                return false;
            }
        }
        loggedUsers.add(cateringUser);
        cateringApplicationManager.setLoggedUsers(loggedUsers);
        logedUser = cateringUser;
        return true;
    }

    public Boolean logOut() {
        List<CateringUser> loggedUsers = cateringApplicationManager.getLoggedUsers();
        loggedUsers.remove(logedUser);
        cateringApplicationManager.setLoggedUsers(loggedUsers);
        logedUser = null;
        return true;
    }

    public Boolean register(String login, String password, String firstName, String lastName, String email, String city, String street, String flatNumber, String userRole) {

        return cateringUserService.createUser(login, password, firstName, lastName, email, city, street, flatNumber, userRole);
    }

    public Boolean changePassword(String password) {
       return cateringUserService.changePassword(password, logedUser.getId());
    }

    public Boolean changePasswordByAdmin(String password, Long userId) {
        return cateringUserService.changePassword(password, userId);
    }

    public Object GetLoggedUser() {
        return logedUser;
    }

    @Override
    public List<Object> findAllUser() {
        return new ArrayList<Object>(Arrays.asList(cateringUserService.getAllCateringUsers().toArray()));

    }

    public UserEJB() {
       // this.cateringUserService = new CateringUserService();
    }


}
