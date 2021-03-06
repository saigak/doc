package com.zocdoc.utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import javax.enterprise.inject.Produces;

/**
 * Created by root on 30.09.15.
 */
public class HibernateUtils {
    private static final SessionFactory ourSessionFactory;
    private static final ServiceRegistry serviceRegistry;

    static {
        try {
            Configuration configuration = new Configuration();

            configuration.configure("hibernate.cfg.xml");

            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            ourSessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            System.out.println(ex.toString());
            throw new ExceptionInInitializerError(ex);
        }
    }

    @Produces
    public static SessionFactory getSessionFactory() throws HibernateException {
        return ourSessionFactory;
    }
}
