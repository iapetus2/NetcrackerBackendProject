package com.projectparty.dao;

import com.projectparty.entities.Deal;
import com.projectparty.exceptions.BusinessException;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DealDao {

    private final SessionFactory sessionFactory;

    public DealDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Deal deal) {
        try {
            var session = sessionFactory
                    .getCurrentSession();
            session.save(deal);
        } catch (Exception e) {
            throw new BusinessException("Error while saving new deal to database ", e);
        }
    }

    public List<Deal> readAll() {
        try {
            var session = sessionFactory
                    .getCurrentSession();
            return session.createQuery("FROM Deal", Deal.class)
                    .list();
        } catch (Exception e) {
            throw new BusinessException("Error while reading all deals from database ", e);
        }
    }

    public List<Deal> readAllItemsById(int id) {
        try {
            var session = sessionFactory
                    .getCurrentSession();
            return session.createQuery("FROM Deal WHERE dealItemId = :itemId", Deal.class)
                    .setParameter("itemId", id)
                    .list();
        } catch (Exception e) {
            throw new BusinessException("Error while reading all deals from database, itemId = " + id, e);
        }

    }

    public Deal read(int id) {
        try {
            var session = sessionFactory
                    .getCurrentSession();
            return session.get(Deal.class, id);
        } catch (Exception e) {
            throw new BusinessException("Error while getting a deal from database ", e);
        }
    }

    public boolean update(Deal deal, int id) {
        try {
            var session = sessionFactory
                    .getCurrentSession();
            session.load(Deal.class, id);
            session.update(deal);
        } catch (Exception e) {
            throw new BusinessException("Error while updating a deal ", e);
        }

        return true;
    }

    public boolean delete(int id) {
        try {
            var session = sessionFactory
                    .getCurrentSession();
            Deal proxyDeal;
            proxyDeal = session.load(Deal.class, id);
            session.delete(proxyDeal);
        } catch (Exception e) {
            throw new BusinessException("Error while deleting a deal from database ", e);
        }

        return true;
    }
}
