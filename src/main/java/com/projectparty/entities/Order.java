package com.projectparty.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Orders")
public class Order {

    @Id
    @NonNull
    @JsonIgnore
    @Column(name = "orderId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;

    @ManyToOne
    @JoinColumn(name = "tradingItemId")
    private TradingItem tradingItem;

    @NonNull
    @Column(name = "type")
    private OrderType orderType;

    @NonNull
    @Column(name = "price")
    private long orderPrice;

    @NonNull
    @Temporal(TemporalType.DATE)
    @Column(name = "creationDate")
    private Date orderDate;


    @ManyToOne
    @JoinColumn
    private User user;

    @NonNull
    @Column(name = "amount")
    private int amount;

    @Override
    public String toString() {
        return "";
    }

    @PrePersist
   public void onCreate() {
        if (orderPrice <= 0 || amount <= 0) {
            throw new RuntimeException("Both price and amount must be positive");
       }

        if (orderType == OrderType.SELL && user.getItems().get(tradingItem.getItemId()) < amount) {
            throw new RuntimeException("Client doesn't have enough items to trade");
        }

     if (user.getCash() < orderPrice * amount) {
            throw new RuntimeException("Insufficient funds");
        }
    }
}
