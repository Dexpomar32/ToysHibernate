package org.example.Dao.DaoImpl;

import org.example.Dao.DaoModels.DaoVanzari;
import org.example.Models.Vanzari;
import org.example.Util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class DaoVanzariImpl implements DaoVanzari {
    private final static String HQL_SELECT_ALL_FROM_VANZARI = "FROM Papusile";
    private final static String HQL_SELECT_VANZARI_ZI = "SELECT SUM(v.cantitateaVanduta) FROM Vanzari v WHERE v.dataVanzare = :data";
    private final SessionFactory sessionFactory;

    public DaoVanzariImpl() {
        this.sessionFactory = HibernateUtil.getInstance().getSessionFactory();
    }

    @Override
    public List<Vanzari> findAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Vanzari> query = session.createQuery(HQL_SELECT_ALL_FROM_VANZARI, Vanzari.class);
            return query.list();
        }
    }

    @Override
    public Vanzari findById(String id) {
        try (Session session = sessionFactory.openSession()) {
            return session.bySimpleNaturalId(Vanzari.class)
                    .load(id);
        }
    }

    @Override
    public void update(String id, Vanzari entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Vanzari vanzari = session.bySimpleNaturalId(Vanzari.class)
                    .load(id);
            entity.getJucarie().setId(vanzari.getJucarie().getId());

            try {
                vanzari.setJucarie(entity.getJucarie());
                vanzari.setDataVanzare(entity.getDataVanzare());
                vanzari.setCantitateaVanduta(entity.getCantitateaVanduta());
                session.merge(vanzari);
                session.getTransaction().commit();
            } catch (HibernateException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void delete(String id) {
        try (Session session = sessionFactory.openSession()) {
            Vanzari vanzari = session.bySimpleNaturalId(Vanzari.class)
                    .load(id);
            session.beginTransaction();
            session.evict(vanzari);
            session.remove(vanzari);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(Vanzari entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    public void selectProduseVanduteInZi(String data) {
        try (Session session = sessionFactory.openSession()) {
            Query<Long> query = session.createQuery(HQL_SELECT_VANZARI_ZI, Long.class);
            Date sqlDate = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(data).getTime());
            Long count = query.setParameter("data", sqlDate).getSingleResult();

            System.out.println("Numarul total de jucarii vandute in data " + data + " este: " + count);
        } catch (HibernateException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
