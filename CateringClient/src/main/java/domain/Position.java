package domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
public class Position implements Serializable {


    private Long id;
    private String name;
    private String description;
    private Float price;
    private Set<Order> orderSet = new HashSet<Order>();
    private Boolean toApproved;
    private Boolean dayPosition;
    private Category category;

}
