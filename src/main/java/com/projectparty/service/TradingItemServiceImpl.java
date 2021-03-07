package com.projectparty.service;

import com.projectparty.dao.TradingItemDao;
import com.projectparty.entities.TradingItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TradingItemServiceImpl implements TradingItemService {

    private final TradingItemDao itemsDao;

    @Autowired
    public TradingItemServiceImpl(TradingItemDao itemsDao) {
        this.itemsDao = itemsDao;
    }

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
