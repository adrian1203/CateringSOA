package service;

import domain.CateringUser;
import domain.UserRole;
import repository.CateringUserRepository;

import javax.inject.Inject;
import java.util.List;

public class CateringUserService {

    private CateringUserRepository cateringUserRepository;

    private List<CateringUser> findAllUser() {
        return cateringUserRepository.findAllUser();
    }


    public CateringUserService( ) {
        this.cateringUserRepository = new CateringUserRepository();
    }

    public CateringUser logIn(String login, String password){

        CateringUser cateringUser=cateringUserRepository.findUserByLogin(login);
        if(cateringUser.getPassword().equals(password)){
            return cateringUser;
        }
        return null;
    }
    public Boolean createUser(String login, String password, String firstName, String lastName, String email, String city, String street, String flatNumber, String userRole) {
        CateringUser cateringUser = new CateringUser();
        cateringUser.setLogin(login);
        cateringUser.setPassword(password);
        cateringUser.setUserRole(UserRole.valueOf(userRole));
        cateringUser.setCity(city);
        cateringUser.setFirstName(firstName);
        cateringUser.setLastName(lastName);
        cateringUser.setEmail(email);
        cateringUser.setStreet(street);
        cateringUser.setFlatNumber(flatNumber);

        //todo trzeba pewnie później sprawdzić czy istniej takie haslo itd xD
        cateringUserRepository.createUser(cateringUser);
        return true;

    }
}
