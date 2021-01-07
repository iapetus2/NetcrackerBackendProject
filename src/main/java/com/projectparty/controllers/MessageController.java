package com.projectparty.controllers;
import com.projectparty.graphs.Graph;
import com.projectparty.graphs.GraphMessage;
import org.hibernate.HibernateException;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    @EventListener
    @SendTo("/topic/messages")
    public void send(GraphMessage graphMessage) throws HibernateException {
        new Graph(graphMessage.getDeal());
    }
}
