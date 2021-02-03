package com.projectparty.listeners;

import com.projectparty.controllers.MessageController;
import com.projectparty.entities.Deal;
import com.projectparty.graphs.GraphMessage;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.stereotype.Component;

@Component
public class ModifiedListener implements PostUpdateEventListener, PostInsertEventListener {

    private final MessageController messageController;

    public ModifiedListener(MessageController messageController) {
        this.messageController = messageController;
    }

    private void onSaveOrUpdate(Deal event) {
        GraphMessage graphMessage = new GraphMessage(event);

        try {
            messageController.send(graphMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPostInsert(PostInsertEvent postInsertEvent) {
        Object entity = postInsertEvent.getEntity();

        if (entity instanceof Deal) {
            onSaveOrUpdate((Deal) entity);
        }
    }

    @Override
    public void onPostUpdate(PostUpdateEvent postUpdateEvent) {
        Object entity = postUpdateEvent.getEntity();

        if (entity instanceof Deal) {
            onSaveOrUpdate((Deal) entity);
        }
    }

    @Override
    public boolean requiresPostCommitHanding(EntityPersister entityPersister) {
        return false;
    }

    @Override
    public boolean requiresPostCommitHandling(EntityPersister persister) {
        return false;
    }
}
