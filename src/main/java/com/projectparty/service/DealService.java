package com.projectparty.service;

import com.projectparty.dao.DealDao;
import com.projectparty.entities.Deal;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DealService implements DealServiceInterface {

    private DealDao itemsDao = new DealDao();

    @Override
    public void save(Deal deal) {
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





}
