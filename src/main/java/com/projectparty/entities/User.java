package com.projectparty.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
@Table(name = "Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @NonNull
    @Column(name = "id")
    @Id
    private AtomicInteger userId;
    @NonNull
    private String userName;
    private long cash;
    @OneToMany
    private List<Order> orders;
    @OneToMany
    private List<Deal> deals;
}
