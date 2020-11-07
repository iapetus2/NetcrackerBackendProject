package entities;

import lombok.Data;
import lombok.NonNull;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class Order {

    @NonNull
    private AtomicInteger orderId;
    @NonNull
    private final entities.TradingItem tradingItem;
    @NonNull
    private final entities.OrderType orderType;
    @NonNull
    private final long orderPrice;
    @NonNull
    private Date orderDate;

}
