package org.example.Dao.DaoImpl;

import org.example.Dao.DaoModels.DaoCategorii;
import org.example.Models.Categorii;
import org.example.Util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class DaoCategoriiImpl implements DaoCategorii {
    private final static String HQL_SELECT_ALL_FROM_CATEGORII = "FROM Categorii";
    private final SessionFactory sessionFactory;

    public DaoCategoriiImpl() {
        this.sessionFactory = HibernateUtil.getInstance().getSessionFactory();
    }

    @Override
    public List<Categorii> findAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Categorii> query = session.createQuery(HQL_SELECT_ALL_FROM_CATEGORII, Categorii.class);
            return query.list();
        }
    }

    @Override
    public Categorii findById(String id) {
        try (Session session = sessionFactory.openSession()) {
            return session.bySimpleNaturalId(Categorii.class)
                    .load(id);
        }
    }

    @Override
    public void update(String id, Categorii entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Categorii categorii = session.bySimpleNaturalId(Categorii.class)
                    .load(id);

            try {
                categorii.setNumeCategorie(entity.getNumeCategorie());
                session.merge(categorii);
                session.getTransaction().commit();
            } catch (HibernateException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void delete(String id) {
        try (Session session = sessionFactory.openSession()) {
            Categorii categorii = session.bySimpleNaturalId(Categorii.class)
                    .load(id);
            session.beginTransaction();
            session.evict(categorii);
            session.remove(categorii);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(Categorii entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }
}