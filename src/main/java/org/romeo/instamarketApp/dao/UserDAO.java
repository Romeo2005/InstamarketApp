package org.romeo.instamarketApp.dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.romeo.instamarketApp.instagram_working.MainInstagramWorker;
import org.romeo.instamarketApp.models.Add;
import org.romeo.instamarketApp.models.InFilter;
import org.romeo.instamarketApp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO implements DAO<User, String>{
    @Autowired
    MainInstagramWorker instagramWorker;

    private final SessionFactory factory =
            new Configuration()
                    .configure()
                    .buildSessionFactory();
    @Override
    public void create(User user) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.save(user);

            session.getTransaction().commit();
        }
    }

    @Override
    public User read(String s) {
        try (Session session = factory.openSession()) {
            return session.get(User.class, s);
        }
    }

    @Override
    public void update(User user) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.update(user);

            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(User user) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.delete(user);

            session.getTransaction().commit();
        }
    }

    public List<User> getBestUsers(Add add) {
        try (Session session = factory.openSession()) {

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<InFilter> query = builder.createQuery(InFilter.class);
            Root<InFilter> root = query.from(InFilter.class);

            query.where(builder.and(
                    builder.or(
                            builder.equal(root.get("media_type"), "ALL"),
                            builder.equal(root.get("media_type"), add.getMediaType())
                    ),

                    builder.or(
                            builder.equal(root.get("add_type"), "ALL"),
                            builder.equal(root.get("add_type"), add.getAddType())
                    ),

                    builder.le(root.get("price"), add.getPrice())
            ));

            List<InFilter> inFilters = session.createQuery(query).getResultList();
            List<User> result = new ArrayList<>();

            for (InFilter i : inFilters)
                result.add(i.getUser());

            return instagramWorker.getUsersSuitableByFollowers(result, add.getMinFollowers());
        }
    }
}
