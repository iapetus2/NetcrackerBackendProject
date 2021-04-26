package com.projectparty.service;

import com.projectparty.dao.DealDao;
import com.projectparty.entities.Deal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@Transactional
public class DealServiceImpl implements DealService {

    private final DealDao itemsDao;

    private static final Logger logger
            = Logger.getLogger(DealService.class.getName());

    @Autowired
    public DealServiceImpl(DealDao itemsDao) {
        this.itemsDao = itemsDao;
    }

    @Override
    public void save(Deal deal) {
        deal.setDealDate(new Date());
        itemsDao.save(deal);
        logger.log(Level.INFO, "New deal has been saved to DB, deal_id = " + deal.getDealId());
    }

    @Override
    public List<Deal> readAll() {
        logger.log(Level.INFO, "Getting all deals from database");
        return itemsDao.readAll();
    }

    @Override
    public Deal read(int id) {
        logger.log(Level.INFO, "Getting deal from database with id = " + id);
        return itemsDao.read(id);
    }

    @Override
    public boolean update(Deal deal, int id) {
        logger.log(Level.INFO, "Updating properties of a deal with id = " + id);
        return itemsDao.update(deal, id);
    }

    @Override
    public boolean delete(int id) {
        logger.log(Level.INFO, "Deleting deal with id = " + id);
        return itemsDao.delete(id);
    }

    @Override
    public List<Deal> readAllItemsById(int id) {
        logger.log(Level.INFO, "Getting deals from database committed over the item with id = " + id);
        return itemsDao.readAllItemsById(id);
    }

}
