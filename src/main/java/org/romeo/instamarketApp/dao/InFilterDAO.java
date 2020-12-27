package org.romeo.instamarketApp.dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.romeo.instamarketApp.models.InFilter;
import org.springframework.stereotype.Component;

@Component
public class InFilterDAO implements DAO<InFilter, String>{

    private final SessionFactory factory =
            new Configuration()
                    .configure()
                    .buildSessionFactory();

    @Override
    public void create(InFilter inFilter) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.save(inFilter);

            session.getTransaction().commit();
        }
    }

    @Override
    public InFilter read(String s) {
        try (Session session = factory.openSession()) {
            InFilter result = session.get(InFilter.class, s);

            Hibernate.initialize(result.getUser());

            return result;
        }
    }

    @Override
    public void update(InFilter inFilter) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.update(inFilter);

            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(InFilter inFilter) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.delete(inFilter);

            session.getTransaction().commit();
        }
    }
}
