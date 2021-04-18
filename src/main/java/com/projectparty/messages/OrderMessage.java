package com.projectparty.messages;

import com.projectparty.entities.Order;
import com.projectparty.entities.OrderType;

public class OrderMessage {
    private final OrderType type;
    private int amount;
    private final long price;

    public OrderMessage(Order order) {
        this.type = order.getOrderType();
        this.amount = order.getAmount();
        this.price = order.getOrderPrice();
    }

    public OrderType getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public long getPrice() {
        return price;
    }


    @Override
    public boolean equals(Object object) {
        boolean equation = false;

        if (object instanceof OrderMessage) {
            equation = this.price == ((OrderMessage) object).price &&
                    this.type == ((OrderMessage) object).type;
        }

        return equation;
    }

}
