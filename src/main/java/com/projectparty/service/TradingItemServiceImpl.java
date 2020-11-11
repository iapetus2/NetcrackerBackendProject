package com.projectparty.service;

import com.projectparty.dao.TradingItemDao;
import com.projectparty.entities.TradingItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradingItemServiceImpl implements TradingItemService {

    private TradingItemDao itemsDao = new TradingItemDao();

    @Override
    public void save(TradingItem tradingItem) {
        itemsDao.save(tradingItem);
    }

    @Override
    public List<TradingItem> readAll() {
        //TODO getting from db
        return null;
    }

    @Override
    public TradingItem read(int id) {
        return itemsDao.read(id);
    }

    @Override
    public boolean update(TradingItem tradingItem, int id) {
        return itemsDao.update(tradingItem, id);
    }

    @Override
    public boolean delete(TradingItem tradingItem) {
        return itemsDao.delete(tradingItem);
    }
}
