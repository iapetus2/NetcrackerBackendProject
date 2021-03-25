package com.projectparty.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

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

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    @Nullable
    @Column
    private long cash;

    @Nullable
    @Transient
    private long frozenCash;

    @Transient
    private Map<Integer, Integer> frozenItems;

    @ElementCollection
    @MapKeyColumn(name = "tradingItemId")
    private Map<Integer, Integer> items;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    @OneToMany(mappedBy = "user")
    private List<Order> deals;

}