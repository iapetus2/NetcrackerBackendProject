package com.projectparty.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "TradingItem")
@NoArgsConstructor
public class TradingItem {

    @Id
    @NonNull
    @Column(name = "tradingItemId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int itemId;

    @NonNull
    @Column(name = "name")
    private String itemName;

    @Column(name = "type")
    private TradingItemType itemCategory;

    @Column(name = "price")
    @NonNull
    private long itemPrice;

    @Column(name = "creationDate")
    @Temporal(TemporalType.DATE)
    private Date itemDate;

}
