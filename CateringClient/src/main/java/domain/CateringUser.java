package domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
public class CateringUser implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String userRole;
    private String email;
    private String city;
    private String street;
    private String flatNumber;


}
