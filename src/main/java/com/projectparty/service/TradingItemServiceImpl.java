package com.projectparty.service;

import com.projectparty.dao.TradingItemDao;
import com.projectparty.entities.TradingItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@Transactional
public class TradingItemServiceImpl implements TradingItemService {

    private final TradingItemDao itemsDao;
    private static final Logger logger
            = Logger.getLogger(TradingItemService.class.getName());

    @Autowired
    public TradingItemServiceImpl(TradingItemDao itemsDao) {
        this.itemsDao = itemsDao;
    }

    @Override
    public void save(TradingItem tradingItem) {
        itemsDao.save(tradingItem);
        logger.log(Level.INFO, "New item has been saved to DB, item: " + tradingItem.getItemName());
    }

    @Override
    public List<TradingItem> readAll() {
        logger.log(Level.INFO, "Getting all items from database");
        return itemsDao.readAll();
    }

    @Override
    public TradingItem read(int id) {
        logger.log(Level.INFO, "Getting item from database with id = " + id);
        return itemsDao.read(id);
    }

    @Override
    public boolean update(TradingItem tradingItem, int id) {
        logger.log(Level.INFO, "Updating properties of a item with id=" + id);
        return itemsDao.update(tradingItem, id);
    }

    @Override
    public boolean delete(int id) {
        logger.log(Level.INFO, "Deleting item with id = " + id);
        return itemsDao.delete(id);
    }

}
