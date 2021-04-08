package com.projectparty.service;

import com.projectparty.dao.DealDao;
import com.projectparty.entities.Deal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DealServiceImpl implements DealService {

    private final DealDao itemsDao;

    @Autowired
    public DealServiceImpl(DealDao itemsDao) {
        this.itemsDao = itemsDao;
    }

    @Override
    public void save(Deal deal) {
        deal.setDealDate(new Date());
        itemsDao.save(deal);
    }

    @Override
    public List<Deal> readAll() {
        return itemsDao.readAll();
    }

    @Override
    public Deal read(int id) {
        return itemsDao.read(id);
    }

    @Override
    public boolean update(Deal deal, int id) {
        return itemsDao.update(deal, id);
    }

    @Override
    public boolean delete(int id) {
        return itemsDao.delete(id);
    }

    @Override
    public List<Deal> readAllItemsById(int id) {
        return itemsDao.readAllItemsById(id);
    }

}
