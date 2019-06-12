package domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "position")
public class Position implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CUST_GEN")
    @Column(name = "position_id", nullable = false)
    private Long id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private Float price;
    @Column(columnDefinition="BOOLEAN DEFAULT false")
    private Boolean toApproved;
    @Column(columnDefinition="BOOLEAN DEFAULT false")
    private Boolean dayPosition;


    @JsonIgnoreProperties({"positionSet"})
    @ManyToMany(mappedBy = "positionSet")
    private Set<Order> orderSet = new HashSet<Order>();

    @JsonIgnoreProperties({"positionSet"})
    @ManyToMany(mappedBy = "permamentPositionSet")
    private Set<PermanentOrder> permamentOrderSet = new HashSet<PermanentOrder>();

    @ManyToOne
    private Category category;

}
