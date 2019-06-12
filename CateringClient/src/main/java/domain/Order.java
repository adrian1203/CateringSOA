package domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
public class Order implements Serializable {

    private Long id;
    private Date orderDate;
    private Date deliverDate;
    private Float price;
    private String additionalInformation;
    private OrderStatus orderStatus;
    private Set<Position> positionSet = new HashSet<Position>();
    private CateringUser cateringUser;
}
