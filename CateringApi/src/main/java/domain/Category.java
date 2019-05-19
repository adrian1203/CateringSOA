package domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CUST_GEN")
    @Column(name = "category_id", nullable = false)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @OneToMany
    private Set<Position> positionSet;
}
