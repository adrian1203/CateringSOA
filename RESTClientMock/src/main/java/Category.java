import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties
public class Category implements Serializable {

    private Long id;
    private String name;
    private String description;
    private Set<Position> positionSet;
}
