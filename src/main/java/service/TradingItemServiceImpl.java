package service;

import entities.TradingItem;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TradingItemServiceImpl implements TradingItemService{

    private static final AtomicInteger ITEM_ID_HOLDER = new AtomicInteger();

    @Override
    public void create(TradingItem tradingItem) {
        //TODO adding to db
    }

    @Override
    public List<TradingItem> readAll() {
        //TODO getting from db
        return null;
    }

    @Override
    public TradingItem read(AtomicInteger id) {
        return null;
    }

    @Override
    public boolean update(TradingItem tradingItem, AtomicInteger id) {
        return false;
    }

    @Override
    public boolean delete(AtomicInteger id) {
        return false;
    }
}
