package com.projectparty.controllers;

import com.projectparty.messages.DealMessage;
import com.projectparty.messages.OrderMessage;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class MessageController {

    private static final Logger logger = Logger.getLogger(MessageController.class.getName());
    private final SimpMessagingTemplate template;

    public MessageController(SimpMessagingTemplate template) {
        this.template = template;
    }


    @RequestMapping(path = "/deals", method = RequestMethod.POST)
    public void sendToGraph(@RequestBody DealMessage dealMessage) {
        template.convertAndSend("/topic/deals", dealMessage);
        logger.log(Level.INFO, "Deal message has been sent to deal graph");
    }

    public void sendToOrderBook(OrderMessage orderMessage) {
        template.convertAndSend("/topic/orders", orderMessage);
        logger.log(Level.INFO, "Order message has been sent to orderBook");
    }

}
