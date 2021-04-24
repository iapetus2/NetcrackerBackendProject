package com.projectparty.messages;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projectparty.entities.Deal;

import java.util.Date;

//todo use lombok
public class DealMessage {

    private final long price;
    @JsonFormat(pattern = "MM/dd/yyyy HH:mm:ss",timezone = "Europe/Moscow")
    private final Date time;

    public DealMessage(Deal deal) {
        this.price = deal.getDealPrice();
        this.time =  new Date();
    }

    public long getPrice() {
        return price;
    }

    public Date getTime() {
        return time;
    }
}
