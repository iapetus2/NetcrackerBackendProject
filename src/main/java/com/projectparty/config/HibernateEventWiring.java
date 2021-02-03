package com.projectparty.config;

import com.projectparty.listeners.ModifiedListener;
import com.projectparty.utils.HibernateSessionFactoryUtil;
import org.hibernate.SessionFactory;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class HibernateEventWiring {
    private final ModifiedListener modifiedListener;

    public HibernateEventWiring(ModifiedListener modifiedListener) {
        this.modifiedListener = modifiedListener;
    }

    @PostConstruct
    public void registerListeners() {
        SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
        EventListenerRegistry registry = ((SessionFactoryImpl) sessionFactory).getServiceRegistry()
                .getService(EventListenerRegistry.class);

        registry.getEventListenerGroup(EventType.POST_INSERT).prependListener(modifiedListener);
        registry.getEventListenerGroup(EventType.POST_UPDATE).prependListener(modifiedListener);
    }
}
