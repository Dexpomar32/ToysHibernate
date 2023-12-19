package org.example.Util;

import org.example.Config.HibernateConfig;
import org.hibernate.SessionFactory;

public class HibernateUtil {
    private static final HibernateUtil instance = new HibernateUtil();
    private final SessionFactory sessionFactory;

    public static HibernateUtil getInstance() {
        return instance;
    }

    private HibernateUtil() {
        sessionFactory = new HibernateConfig().buildSessionFactory();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
