package com.projectparty.entities;

import lombok.Data;
import lombok.NonNull;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class Order {

    @NonNull
    private AtomicInteger orderId;
    @NonNull
    private final TradingItem tradingItem;
    @NonNull
    private final OrderType orderType;
    private final long orderPrice;
    @NonNull
    private Date orderDate;

}
