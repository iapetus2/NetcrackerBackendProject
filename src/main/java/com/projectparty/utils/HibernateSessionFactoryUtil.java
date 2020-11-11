package com.projectparty.utils;
import com.projectparty.entities.Deal;
import com.projectparty.entities.Order;
import com.projectparty.entities.TradingItem;
import com.projectparty.entities.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure() ;
                configuration.addAnnotatedClass(TradingItem.class);
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Deal.class);
                configuration.addAnnotatedClass(Order.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties());
                sessionFactory = configuration
                        .buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("SessionFactory Exception" + e);
            }
        }
        return sessionFactory;
    }
}