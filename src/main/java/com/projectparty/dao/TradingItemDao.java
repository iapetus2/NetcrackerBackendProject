package com.projectparty.dao;

import com.projectparty.entities.TradingItem;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@Component
//todo transactional
public class TradingItemDao {
        Logger logger = Logger.getLogger(TradingItemDao.class.getName());//todo access modifier

        private final SessionFactory sessionFactory;

        public TradingItemDao(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
        }

        public void save(TradingItem TradingItem) {
            try{
                var session = sessionFactory
                        .getCurrentSession();
                session.save(TradingItem);
            }catch (Exception e){
                logger.log(Level.SEVERE, "Exception: ", e); //todo
            }
        }


        public List<TradingItem> readAll() {
            try{
                var session = sessionFactory
                        .getCurrentSession();
                return session.createQuery("FROM TradingItem", TradingItem.class)
                        .list();
            }
            catch (Exception e){
                logger.severe("Error: " + e.getMessage());
                throw new RuntimeException("Can not read database"); //todo
            }
        }

        public TradingItem read(int id) {
            try {
                var session = sessionFactory
                        .getCurrentSession();
                return  session.get(TradingItem.class, id);
            }
            catch (Exception e){
                logger.severe("Error: " + e.getMessage());
                throw new RuntimeException("Can not read from database");
            }
        }

        public boolean update(TradingItem tradingItem, int id) {
            try  {
                var session = sessionFactory
                        .getCurrentSession();
                session.load(TradingItem.class, id);
                session.update(tradingItem);
            } catch (Exception e){
                logger.severe("Error: " + e.getMessage());
                throw new RuntimeException("Update failure");
            }

            return true;
        }

        public boolean delete(int id) {
            try{
                var session = sessionFactory
                        .getCurrentSession();
                TradingItem proxyTradingItem;
                proxyTradingItem = session.load(TradingItem.class, id);
                session.delete(proxyTradingItem);
            } catch (Exception e) {
                logger.severe("Error: " + e.getMessage());
                throw new RuntimeException("Delete failure");
            }
            return true;
        }
}