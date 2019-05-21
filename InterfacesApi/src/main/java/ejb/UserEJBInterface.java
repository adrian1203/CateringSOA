package ejb;

public interface UserEJBInterface {

    public Boolean logIn(String login, String password);
    public Boolean logOut();
    public Boolean register(String login, String password,String firstName,String lastName,String email, String city,String street,String flatNumber, String userRole);
    public Boolean changePassword(String oldPassword, String newPassword);
    public Boolean changePasswordByAdmin(String newPassword, Long userId);
}
