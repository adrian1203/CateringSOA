package service;

import domain.CateringUser;
import domain.UserRole;
import repository.CateringUserRepository;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Logger;

public class CateringUserService {

    Logger logger = Logger.getLogger(CateringUserService.class.getName());


    private CateringUserRepository cateringUserRepository;

    private List<CateringUser> findAllUser() {
        return cateringUserRepository.findAllUser();
    }


    public CateringUserService( ) {
        this.cateringUserRepository = new CateringUserRepository();
    }

    public CateringUser logIn(String login, String password){

        CateringUser cateringUser=cateringUserRepository.findUserByLogin(login);
        if(cateringUser != null && cateringUser.getPassword().equals(hashPassword(password))){
            return cateringUser;
        }
        return null;
    }

    public Boolean createUser(String login, String password, String firstName, String lastName, String email, String city, String street, String flatNumber, String userRole) {
        if(checkIfExistPasswordOrLogin(hashPassword(password), login)){
            logger.info("istnieje hasło");
            return false;}
        CateringUser cateringUser = new CateringUser();
        cateringUser.setLogin(login);
        cateringUser.setPassword(hashPassword(password));
        cateringUser.setUserRole(UserRole.valueOf(userRole));
        cateringUser.setCity(city);
        cateringUser.setFirstName(firstName);
        cateringUser.setLastName(lastName);
        cateringUser.setEmail(email);
        cateringUser.setStreet(street);
        cateringUser.setFlatNumber(flatNumber);
        cateringUserRepository.createUser(cateringUser);
        return true;

    }

    public String hashPassword(String password)   {

        String passwordHash =null;
        MessageDigest md = null;
        try{
            md = MessageDigest.getInstance("MD5");
            byte[] passwordBytes = password.getBytes();
            byte[] hash = md.digest(passwordBytes);
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< hash.length ;i++)
            {
                sb.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
            }
            passwordHash = sb.toString();
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();

        }
       logger.info(passwordHash);
        System.out.println("skrót hasła: "+passwordHash);
        return passwordHash;
    }

    Boolean checkIfExistPasswordOrLogin(String password, String login){
      return  cateringUserRepository.findAllUser().stream().anyMatch(elem -> elem.getPassword().equals(password) || elem.getLogin().equals(login) );
    }


}
