package com.projectparty.messages;

import com.projectparty.entities.Order;
import com.projectparty.entities.OrderType;
import lombok.Data;

import java.util.Objects;

@Data
public class OrderMessage {

    private final OrderType type;
    private int amount;
    private final long price;

    public OrderMessage(Order order) {
        this.type = order.getType();
        this.amount = order.getAmount();
        this.price = order.getPrice();
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

    @Override
    public int hashCode() {
        return Objects.hash(type, amount, price);
    }
}
