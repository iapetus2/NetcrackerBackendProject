package com.projectparty.service;

import com.projectparty.dao.DealDao;
import com.projectparty.entities.Deal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DealServiceImpl implements DealService {

    private DealDao dealDao = new DealDao();

    @Override
    public void save(Deal deal) {
        dealDao.save(deal);
    }

    @Override
    public List<Deal> readAll() {
        return dealDao.readAll();
    }

    @Override
    public Deal read(int id) {
        return dealDao.read(id);
    }

    @Override
    public boolean update(Deal deal, int id) {
        return dealDao.update(deal, id);
    }

    @Override
    public boolean delete(int id) {
        return dealDao.delete(id);
    }





}
