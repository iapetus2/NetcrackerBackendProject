package com.projectparty.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @NonNull
    @Column(name = "userId")
    private int userId;

    @NonNull
    @Column(name = "name")
    private String userName;

    @Column(name = "surname")
    private String userSurname;

    @Column(name = "cash")
    private long cash;

    @Transient
    private long frozenCash;

    @Transient
    private Map<Integer, Integer> frozenItems;

    @ElementCollection
    @MapKeyColumn(name = "tradingItemId")
    private Map<Integer, Integer> items;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Order> orders;

    @OneToMany(mappedBy = "user")
    private List<Deal> deals;

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public long getCash() {
        return cash;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public List<Deal> getDeals() {
        return deals;
    }

    public Map<Integer, Integer> getItems() {
        return items;
    }

    /*public void setUserId(int userId) {
        this.userId = userId;
    }*/

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCash(long cash) {
        this.cash = cash;
    }

    public void setItems(Map<Integer, Integer> items) {
        this.items = items;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void setDeals(List<Deal> deals) {
        this.deals = deals;
    }

    @Override
    public String toString() {
        return "";
    }
}
