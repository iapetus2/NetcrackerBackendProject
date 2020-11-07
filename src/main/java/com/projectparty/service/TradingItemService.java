package com.projectparty.service;

import com.projectparty.entities.TradingItem;
import java.util.List;

public interface TradingItemService {

    void create(TradingItem tradingItem);

    List<TradingItem> readAll();

    TradingItem read(int id);

    boolean update(TradingItem tradingItem, int id);

    boolean delete(int id);

}
