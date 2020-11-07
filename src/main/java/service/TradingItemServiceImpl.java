package service;

import entities.TradingItem;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TradingItemServiceImpl implements service.TradingItemService {

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
    public TradingItem read(int id) {
        return null;
    }

    @Override
    public boolean update(TradingItem tradingItem, int id) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
