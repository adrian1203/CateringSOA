package domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "catering_user")
public class CateringUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CUST_GEN")
    @Column(name = "id", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CateringUser() {
    }

}
