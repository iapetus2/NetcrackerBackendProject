package com.projectparty.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "Deals")
@NoArgsConstructor
public class Deal {

    @Id
    @NonNull
    @Column(name = "dealId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int dealId;

    @NonNull
    @Temporal(TemporalType.DATE)
    @Column(name = "dealDate")
    private Date dealDate;

    @Column(name = "price")
    private long dealPrice;

    @NonNull
    @Column(name = "tradingItemId")
    private int dealItemId;

    @NonNull
    @ManyToOne
    @JoinColumn
    private User user;

    @Column(name = "amount")
    private int amount;
}
