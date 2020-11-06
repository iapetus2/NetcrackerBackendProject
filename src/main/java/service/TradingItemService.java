package service;

import entities.TradingItem;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public interface TradingItemService {

    void create(TradingItem tradingItem);

    List<TradingItem> readAll();

    TradingItem read(AtomicInteger id);

    boolean update(TradingItem tradingItem, AtomicInteger id);

    boolean delete(AtomicInteger id);

}
