
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"orderSet"})
public class Position implements Serializable {


    private Long id;
    private String name;
    private String description;
    private Float price;
    private Category category;
    private Boolean toApproved;
    private Boolean dayPosition;



}
