package org.example.Dao.DaoImpl;

import org.example.Dao.DaoModels.DaoPapusile;
import org.example.Models.Papusile;
import org.example.Util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class DaoPapusileImpl implements DaoPapusile {
    private final static String HQL_SELECT_ALL_FROM_PAPUSILE = "FROM Papusile";
    private final static String HQL_PAPUSI_ASC = "FROM Papusile p JOIN FETCH p.Jucarie j ORDER BY j.pret ASC";
    private final SessionFactory sessionFactory;

    public DaoPapusileImpl() {
        this.sessionFactory = HibernateUtil.getInstance().getSessionFactory();
    }

    @Override
    public List<Papusile> findAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Papusile> query = session.createQuery(HQL_SELECT_ALL_FROM_PAPUSILE, Papusile.class);
            return query.list();
        }
    }

    @Override
    public Papusile findById(String id) {
        try (Session session = sessionFactory.openSession()) {
            return session.bySimpleNaturalId(Papusile.class)
                    .load(id);
        }
    }

    @Override
    public void update(String id, Papusile entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Papusile papusile = session.bySimpleNaturalId(Papusile.class)
                    .load(id);
            entity.getJucarie().setId(papusile.getJucarie().getId());

            try {
                papusile.setJucarie(entity.getJucarie());
                papusile.setMaterial(entity.getMaterial());
                papusile.setInaltime(entity.getInaltime());
                session.merge(papusile);
                session.getTransaction().commit();
            } catch (HibernateException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void delete(String id) {
        try (Session session = sessionFactory.openSession()) {
            Papusile papusile = session.bySimpleNaturalId(Papusile.class)
                    .load(id);
            session.beginTransaction();
            session.evict(papusile);
            session.remove(papusile);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(Papusile entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void selectPapusiAsc() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Query<Papusile> query = session.createQuery(HQL_PAPUSI_ASC, Papusile.class);
        List<Papusile> papusileList = query.list();

        session.getTransaction().commit();
        System.out.println(papusileList);
    }
}