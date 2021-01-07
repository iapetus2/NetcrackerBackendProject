package com.projectparty.graphs;

import com.projectparty.entities.Deal;

public class Graph {
    String nameOfTheGraph;
    Deal deal;
    long price;

    public Graph() {
    }

    public Graph(Deal deal) {
        this.deal = deal;
        this.price = deal.getDealPrice();
    }
}
