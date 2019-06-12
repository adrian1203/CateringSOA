package domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
public class PermanentOrder implements Serializable {

    private Long id;
    private String additionalInformation;
    private Date orderDate;
    private Set<PermanetOrderDate> deliverDateSet = new HashSet<PermanetOrderDate>();
    private Set<Position> permamentPositionSet = new HashSet<Position>();
    private CateringUser cateringUser;
}
