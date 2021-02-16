package com.projectparty.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Orders")
public class Order {

    @NonNull
    @Id
    @Column(name = "id")
    private int orderId;

    @NonNull
    @Column(name = "tradingItemId")
    private int tradingItemId;

    @NonNull
    @Column(name = "type")
    private OrderType orderType;

    @Column(name = "price")
    private long orderPrice;

    @NonNull
    @Column(name = "creationDate")
    private Date orderDate;

    @NonNull
    @Column(name = "userId")
    private int userId;

}
