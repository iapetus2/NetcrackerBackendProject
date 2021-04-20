package com.projectparty.config;

import com.projectparty.listeners.DealListener;
import com.projectparty.listeners.OrderListener;
import org.hibernate.SessionFactory;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class HibernateEventWiring {
    private final DealListener dealListener;
    private final SessionFactory sessionFactory;
    private final OrderListener orderListener;

    @Autowired
    public HibernateEventWiring(DealListener dealListener, SessionFactory sessionFactory, OrderListener orderListener) {
        this.dealListener = dealListener;
        this.sessionFactory = sessionFactory;
        this.orderListener = orderListener;
    }

    @PostConstruct
    public void registerListeners() {
        EventListenerRegistry registry = ((SessionFactoryImpl) sessionFactory).getServiceRegistry()
                .getService(EventListenerRegistry.class);

        registry.getEventListenerGroup(EventType.POST_INSERT).prependListener(dealListener);
        registry.getEventListenerGroup(EventType.POST_UPDATE).prependListener(dealListener);
        registry.getEventListenerGroup(EventType.POST_INSERT).prependListener(orderListener);
    }
}
