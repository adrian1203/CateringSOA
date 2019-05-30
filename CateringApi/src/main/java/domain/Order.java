package domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "catering_order")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CUST_GEN")
    @Column(name = "order_id", nullable = false)
    private Long id;
    @Column
    private Date orderDate;
    @Column
    private Date deliverDate;
    @Column
    private Float price;
    @Column
    private String additionalInformation;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus=OrderStatus.ORDERED;

    @JsonIgnoreProperties({"orderSet", "permamentOrderSet"})
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "positionOrder",
            joinColumns = { @JoinColumn(name = "position_id") },
            inverseJoinColumns = { @JoinColumn(name = "order_id")}
    )
    private Set<Position> positionSet = new HashSet<Position>();

    @ManyToOne
    private CateringUser cateringUser;
}
