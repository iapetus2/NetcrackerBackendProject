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
    @JsonIgnore
    @Column(name = "orderId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "tradingItemId")
    private TradingItem tradingItem;

    @NonNull
    @Column(name = "type")
    private OrderType type;

    @NonNull //todo remove
    @Column(name = "price")
    private long price;

    @NonNull
    @Temporal(TemporalType.DATE)
    @Column(name = "creationDate")
    private Date date;


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
}
