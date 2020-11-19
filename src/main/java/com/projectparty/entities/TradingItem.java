package com.projectparty.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "TradingItem")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TradingItem {
    @Id
    @Column(name = "id")
    private int itemId;

    @NonNull
    @Column(name = "name")
    private String itemName;

    @Column(name = "type")
    private TradingItemType itemCategory;

    @Column(name = "price")
    private long itemPrice;

    @Column(name = "creationDate")
    private Date itemDate;

}