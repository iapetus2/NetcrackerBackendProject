package com.projectparty.service;

import com.projectparty.dao.TradingItemDao;
import com.projectparty.entities.TradingItem;
import com.projectparty.entities.TradingItemType;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TradingItemService implements TradingItemServiceInterface {

    private TradingItemDao itemsDao = new TradingItemDao();

    @Override
    public void save(TradingItem tradingItem) {
        itemsDao.save(tradingItem);
    }

    @Override
    public List<TradingItem> readAll() {
        return itemsDao.readAll();
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
    public boolean delete(int id) {
        return itemsDao.delete(id);
    }





}
