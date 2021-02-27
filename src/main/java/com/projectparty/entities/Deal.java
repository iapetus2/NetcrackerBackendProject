package com.projectparty.entities;

import lombok.*;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dealId")
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
    @JoinColumn(nullable = false)
    private User user;

    @Column(name = "amount")
    private int amount;

    public int getDealItemId() {
        return dealItemId;
    }

    public User getUser() {
        return user;
    }

    public int getAmount() {
        return amount;
    }

    public Date getDealDate() {
        return dealDate;
    }

    public int getDealId() {
        return dealId;
    }

    public long getDealPrice() {
        return dealPrice;
    }
}
