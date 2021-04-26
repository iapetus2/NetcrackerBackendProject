package com.projectparty.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "Deals")
@NoArgsConstructor
public class Deal {

    @Id
    @Column(name = "dealId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NonNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "dealDate")
    private Date date;

    @Column(name = "price")
    private long price;

    @Column(name = "tradingItemId")
    private int dealItemId;

    @NonNull
    @ManyToOne
    @JoinColumn
    private User user;

    @Column(name = "amount")
    private int amount;
}
