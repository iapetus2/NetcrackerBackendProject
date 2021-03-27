package com.projectparty.controllers;

import com.projectparty.messages.GraphMessage;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageController {

    private final SimpMessagingTemplate template;

    public MessageController(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void send(GraphMessage graphMessage) {
        template.convertAndSend("/topic/messages", graphMessage);
    }

}
