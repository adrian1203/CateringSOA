package domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PermanetOrderDate implements Serializable {

    public PermanetOrderDate(Date orderDate) {
        this.orderDate = orderDate;
        this.orderStatus = OrderStatus.ORDERED;
    }

    private Long id;
    private Date orderDate;
    private OrderStatus orderStatus;

}
