package com.projectparty.entities;

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
    @NonNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orderId")
    private Integer orderId;

    @ManyToOne
    @JoinColumn(name = "tradingItemId")
    private TradingItem tradingItem;

    @NonNull
    @Column(name = "type")
    private OrderType orderType;

    @Column(name = "price")
    private long orderPrice;

    @NonNull
    @Temporal(TemporalType.DATE)
    @Column(name = "creationDate")
    private Date orderDate;

    @NonNull
    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @NonNull
    @Column(name = "amount")
    private int amount;

    public TradingItem getTradingItem() {
        return tradingItem;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public long getOrderPrice() {
        return orderPrice;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public User getUser() {
        return user;
    }

    public int getAmount() {
        return amount;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public void setTradingItem(TradingItem tradingItem) {
        this.tradingItem = tradingItem;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public void setOrderPrice(long orderPrice) {
        this.orderPrice = orderPrice;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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
