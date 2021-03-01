package com.projectparty.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Orders")
public class Order {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer orderId;

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
