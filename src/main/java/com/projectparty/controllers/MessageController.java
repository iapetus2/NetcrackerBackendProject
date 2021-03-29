package com.projectparty.controllers;

import com.projectparty.messages.DealMessage;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
public class MessageController {

    private final SimpMessagingTemplate template;

    public MessageController(SimpMessagingTemplate template) {
        this.template = template;
    }


    @RequestMapping(path="/deals", method = RequestMethod.POST)
    public void sendToGraph(@RequestBody DealMessage dealMessage) {
        template.convertAndSend("/topic/deals", dealMessage);
        System.out.println("Sent");
    }

}
