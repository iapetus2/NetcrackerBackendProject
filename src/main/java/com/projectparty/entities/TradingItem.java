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
    @Column(name = "tradingItemId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NonNull
    @Column(name = "name")
    private String name;

    @NonNull
    @Column(name = "type")
    private TradingItemType type;

    @Column(name = "price")
    private long price;

    @Column(name = "creationDate")
    @Temporal(TemporalType.DATE)
    private Date date;

}
