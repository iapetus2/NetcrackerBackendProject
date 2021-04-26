package com.projectparty.dao;

import com.projectparty.entities.TradingItem;
import com.projectparty.exceptions.BusinessException;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class TradingItemDao {

    private final SessionFactory sessionFactory;

    public TradingItemDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(TradingItem TradingItem) {
        try {
            var session = sessionFactory
                    .getCurrentSession();
            session.save(TradingItem);
        } catch (Exception e) {
            throw new BusinessException("Error while saving new item to database ", e);
        }
    }


    public List<TradingItem> readAll() {
        try {
            var session = sessionFactory
                    .getCurrentSession();
            return session.createQuery("FROM TradingItem", TradingItem.class)
                    .list();
        } catch (Exception e) {
            throw new BusinessException("Error while reading all items from database ", e);
        }
    }

    public TradingItem read(int id) {
        try {
            var session = sessionFactory
                    .getCurrentSession();
            return session.get(TradingItem.class, id);
        } catch (Exception e) {
            throw new BusinessException("Error while getting an item from database ", e);
        }
    }

    public boolean update(TradingItem tradingItem, int id) {
        try {
            var session = sessionFactory
                    .getCurrentSession();
            session.load(TradingItem.class, id);
            session.update(tradingItem);
        } catch (Exception e) {
            throw new BusinessException("Error while updating an item's properties ", e);
        }

        return true;
    }

    public boolean delete(int id) {
        try {
            var session = sessionFactory
                    .getCurrentSession();
            TradingItem proxyTradingItem;
            proxyTradingItem = session.load(TradingItem.class, id);
            session.delete(proxyTradingItem);
        } catch (Exception e) {
            throw new BusinessException("Error while deleting tradingItem from database ", e);
        }
        return true;
    }
}