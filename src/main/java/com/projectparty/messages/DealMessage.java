package com.projectparty.messages;

import com.projectparty.entities.Deal;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DealMessage {

   private Deal deal;
   private long price;
   private String timeStamp;

    public DealMessage(Deal deal) {
        this.deal = deal;
        this.price = deal.getDealPrice();
        this.timeStamp = new SimpleDateFormat("yyyyMMdd HH:mm:ss")
                .format(Calendar.getInstance().getTime());
    }

    public DealMessage(long price) {
        this.price = price;
        this.timeStamp = new SimpleDateFormat("yyyyMMdd HH:mm:ss")
                .format(Calendar.getInstance().getTime());
    }

    public DealMessage() {
    }

    public Deal getDeal() {
        return deal;
    }

    public long getPrice() {
        return price;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    @Override
    public String toString() {
        return "GraphMessage{" +
                "price=" + price +
                ", timeStamp='" + timeStamp + '\'' +
                '}';
    }
}