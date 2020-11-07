package entities;

import lombok.*;
import java.util.Date;

@Data
@AllArgsConstructor
public class TradingItem {

    @NonNull
    private final int itemId;
    @NonNull
    private final String itemName;
    private entities.TradingItemType itemCategory;
    private long itemPrice;
    private Date itemDate;

}