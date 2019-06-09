package domain;

        import lombok.Getter;
        import lombok.NoArgsConstructor;
        import lombok.Setter;

        import java.io.Serializable;
        import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class OrderJMS implements Serializable {

    Long id;
    Long userId;
    String adidtionalInformation;
    Date orderedDate;
}
