package domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "catering_user")
public class CateringUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CUST_GEN")
    @Column(name = "catering_user_id", nullable = false)
    private Long id;

    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String login;
    @Column
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    @Column
    private String email;
    @Column
    private String city;
    @Column
    private String street;
    @Column
    private String flatNumber;


}
