package com.projectparty.messages;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projectparty.entities.Deal;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

@Data
public class DealMessage {

    private final long price;

    @NonNull
    @JsonFormat(pattern = "MM/dd/yyyy HH:mm:ss", timezone = "Europe/Moscow")
    private final Date time;


    public DealMessage(Deal deal) {
        this.price = deal.getPrice();
        this.time =  new Date();
    }
}
