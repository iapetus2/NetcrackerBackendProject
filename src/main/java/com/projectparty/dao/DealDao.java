package com.projectparty.dao;

import com.projectparty.entities.Deal;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Component
public class DealDao {
    private static final Logger logger = Logger.getLogger(DealDao.class.getName());

    private final SessionFactory sessionFactory;

    public DealDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Deal deal) {
        var session = sessionFactory
                .getCurrentSession();
        session.save(deal);
    }

    public List<Deal> readAll() {
            var session = sessionFactory
                    .getCurrentSession();
            return session.createQuery("FROM Deal", Deal.class)
                    .list();
    }

    public Deal read(int id) {
        var session = sessionFactory
                .getCurrentSession();
        return session.get(Deal.class, id);
    }

    public boolean update(Deal deal, int id) {
        try {
            var session = sessionFactory
                    .getCurrentSession();
            session.load(Deal.class, id);
            session.update(deal);
        } catch (Exception e) {
            throw new RuntimeException("Update failure");
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
            throw new RuntimeException("Delete failure");
        }

        return true;
    }
}
