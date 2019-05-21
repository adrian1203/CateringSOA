package domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "position")
public class Position {

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

    @ManyToMany(mappedBy = "positionSet")
    private Set<Order> orderSet = new HashSet<Order>();

}