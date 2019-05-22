package domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "parmenent_order_date")
public class PermanetOrderDate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CUST_GEN")
    @Column(name = "parmenent_order_date_id", nullable = false)
    private Long id;
    @Column
    private Date orderDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

}
