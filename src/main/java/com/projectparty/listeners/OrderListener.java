package com.projectparty.listeners;

import com.projectparty.controllers.MessageController;
import com.projectparty.entities.Order;
import com.projectparty.messages.OrderMessage;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.stereotype.Component;

@Component
public class OrderListener implements PostUpdateEventListener, PostInsertEventListener {

    private final MessageController messageController;

    public OrderListener(MessageController messageController) {
        this.messageController = messageController;
    }

    @Override
    public void onPostInsert(PostInsertEvent event) {
        Object entity = event.getEntity();

        if (entity instanceof Order) {
            onInsertOrUpdate((Order) entity);
        }
    }

    @Override
    public void onPostUpdate(PostUpdateEvent event) {
        Object entity = event.getEntity();

        if (entity instanceof Order) {
            onInsertOrUpdate((Order) entity);
        }
    }

    private void onInsertOrUpdate(Order order) {

        //Sending message to subscribers
        OrderMessage orderMessage = new OrderMessage(order);
        try {
            messageController.sendToOrderBook(orderMessage);
        } catch (Exception e) {
            throw new RuntimeException("Could not send message to subscribers", e);
        }
    }

    @Override
    public boolean requiresPostCommitHanding(EntityPersister persister) {
        return false;
    }

    @Override
    public boolean requiresPostCommitHandling(EntityPersister persister) {
        return false;
    }
}
