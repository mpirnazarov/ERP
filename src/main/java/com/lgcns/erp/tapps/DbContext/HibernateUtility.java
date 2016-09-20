package com.lgcns.erp.tapps.DbContext;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Rafatdin on 20.09.2016.
 */
public class HibernateUtility {

    public static SessionFactory factory;
    //private static final ThreadLocal<Session> threadLocal;
    private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();

    private HibernateUtility() {
    }
//maling the Hibernate SessionFactory object as singleton

    public static synchronized SessionFactory getSessionFactory() {

        if (factory == null) {
            factory = new Configuration().configure().buildSessionFactory();
        }
        return factory;
    }

/*    public static Session getSession() {
        Session session = threadLocal.get();
        if(session == null){
            session = factory.openSession();
            threadLocal.set(session);
        }
        return session;
    }

    public static void closeSession() {
        Session session = threadLocal.get();
        if (session != null) {
            session.close();
            threadLocal.set(null);
        }
    }*/

}
