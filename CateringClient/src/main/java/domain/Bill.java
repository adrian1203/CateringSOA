package domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Bill implements Serializable {

    Date genertedDate;
    List<String> orderedPosition;
    Date startDate;
    Date endDate;
    Float price;
    String additionalInfomration;
}