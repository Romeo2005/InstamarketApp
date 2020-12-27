package org.romeo.instamarketApp.dao;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.romeo.instamarketApp.instagram_working.MainInstagramWorker;
import org.romeo.instamarketApp.models.Add;
import org.romeo.instamarketApp.models.InFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class AddDAO implements DAO<Add, String> {

    @Autowired
    MainInstagramWorker instagramWorker;

    private final SessionFactory factory =
            new Configuration()
                    .configure()
                    .buildSessionFactory();

    @Override
    public void create(Add add) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.save(add);

            session.getTransaction().commit();
        }
    }

    @Override
    public Add read(String s) {
        try (Session session = factory.openSession()) {
            Add result = session.get(Add.class, s);

            Hibernate.initialize(result.getUser());

            return result;
        }
    }

    @Override
    public void update(Add add) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.update(add);

            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Add add) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.delete(add);

            session.getTransaction().commit();
        }
    }

    public List<Add> getBestAdds(InFilter filter) {
        try (Session session = factory.openSession()) {

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Add> query = builder.createQuery(Add.class);
            Root<Add> root = query.from(Add.class);

            query.where(builder.and(
                    builder.or(
                            builder.equal(root.get("media_type"), "ALL"),
                            builder.equal(root.get("media_type"), filter.getMediaType())
                    ),

                    builder.or(
                            builder.equal(root.get("add_type"), "ALL"),
                            builder.equal(root.get("add_type"), filter.getAddType())
                    ),

                    builder.gt(root.get("price"), filter.getPrice())
            ));

            List<Add> result = session.createQuery(query).getResultList();
            result.removeIf(add -> filter.getUser().getFollowers() < add.getMinFollowers());

            return result;
        }
    }
}