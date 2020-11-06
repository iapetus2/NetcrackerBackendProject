package entities;

import lombok.*;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@AllArgsConstructor
public class TradingItem {

    @NonNull
    private final AtomicInteger itemId;
    @NonNull
    private final String itemName;
    private TradingItemType itemCategory;
    private Long itemPrice;
    private Date itemDate;

}