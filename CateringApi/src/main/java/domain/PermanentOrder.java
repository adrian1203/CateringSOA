package domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "permanent_order")
public class PermanentOrder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CUST_GEN")
    @Column(name = "permanent_order_id", nullable = false)
    private Long id;

    @Column
    private String additionalInformation;
    @Column
    private Date orderDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<PermanetOrderDate> deliverDateSet = new HashSet<PermanetOrderDate>();

    @JsonIgnoreProperties({"orderSet", "permamentOrderSet"})
    @ManyToMany(cascade = {CascadeType.ALL},  fetch= FetchType.EAGER)
    @JoinTable(

            name = "pasitionOrderPermament",
            joinColumns = { @JoinColumn(name = "permanent_order_id") },
            inverseJoinColumns = { @JoinColumn(name = "position_id")}
    )
    private Set<Position> permamentPositionSet = new HashSet<Position>();

    @ManyToOne
    private CateringUser cateringUser;
}
