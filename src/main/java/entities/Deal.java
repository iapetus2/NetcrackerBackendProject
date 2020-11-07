package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;

@Data
@AllArgsConstructor
public class Deal {

    private int dealId;
    private Date dealDate;
    private long dealPrice;

}
