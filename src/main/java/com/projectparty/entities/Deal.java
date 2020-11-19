package com.projectparty.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "Deals")
@AllArgsConstructor
@NoArgsConstructor
public class Deal {

    @Id
    @NonNull
    @Column(name = "id")
    private int dealId;

    @NonNull
    @Column(name = "dealDate")
    private Date dealDate;

    @Column(name = "price")
    private long dealPrice;

    @NonNull
    @ManyToOne
    @Column(name = "tradingItemName")
    private TradingItem dealItem;

}
