package domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "permanent_order")
public class PermanentOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CUST_GEN")
    @Column(name = "permanent_order_id", nullable = false)
    private Long id;

    @Column
    private String additionalInformation;
    @Column
    private Date orderDate;

    @OneToMany
    private Set<PermanetOrderDate> deliverDateSet = new HashSet<PermanetOrderDate>();

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "pasitionOrder",
            joinColumns = { @JoinColumn(name = "position_id") },
            inverseJoinColumns = { @JoinColumn(name = "permanent_order_id")}
    )
    private Set<Position> positionSet = new HashSet<Position>();

    @ManyToOne
    private CateringUser cateringUser;
}
