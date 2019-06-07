package domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Bill {

    Date genertedDate;
    List<String> orderedPosition;
    Date startDate;
    Date endDate;
    Float price;
    String additionalInfomration;
}
