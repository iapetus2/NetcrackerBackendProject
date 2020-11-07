package com.projectparty.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@AllArgsConstructor
public class User {

    @NonNull
    private AtomicInteger userId;
    private String userName;
    private long cash;
    private List<Order> orders;
    private List<Deal> deals;
}
