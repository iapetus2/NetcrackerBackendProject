package com.projectparty.entities;

import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "User_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserData {

    @Id
    @Column(name = "user_id")
    private int userId;

    @Nullable
    @Column(name = "cash")
    private long cash;

    @OneToOne(mappedBy = "userData")
    @JoinColumn
    private User user;

    @ElementCollection
    @MapKeyColumn(name = "tradingItemId")
    private Map<Integer, Integer> items;

    @OneToMany()
    private List<Order> orders;

    @OneToMany()
    private List<Deal> deals;

}