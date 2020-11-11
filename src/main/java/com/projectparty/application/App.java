package com.projectparty.application;

import com.projectparty.entities.TradingItem;
import com.projectparty.entities.TradingItemType;
import com.projectparty.service.TradingItemService;
import com.projectparty.service.TradingItemServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        TradingItemService tradingItemService = new TradingItemServiceImpl();
        TradingItem tradingItem = new TradingItem(1, "shtuka", TradingItemType.METALS,
                1000, new Date());
        TradingItem tradingItem2 = new TradingItem(3, "kusochek", TradingItemType.METALS,
                2000, new Date());
        tradingItemService.save(tradingItem);
        tradingItemService.save(tradingItem2);
        System.out.println(tradingItem.getItemId());
        tradingItemService.delete(tradingItem);

    }

}