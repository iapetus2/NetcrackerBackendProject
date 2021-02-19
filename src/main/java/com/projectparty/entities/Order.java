package com.projectparty.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

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
    @Column(name = "orderAmount")
    private int orderAmount;

    @NonNull
    @Column(name = "userId")
    private int userId;

}
