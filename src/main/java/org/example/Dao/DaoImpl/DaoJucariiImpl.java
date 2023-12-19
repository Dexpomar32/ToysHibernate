package org.example.Dao.DaoImpl;

import jakarta.persistence.StoredProcedureQuery;
import org.example.Dao.DaoModels.DaoJucarii;
import org.example.Models.Jucarii;
import org.example.Util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import java.math.BigDecimal;
import java.util.List;

@SuppressWarnings("unchecked")
public class DaoJucariiImpl implements DaoJucarii {
    private final static String HQL_SELECT_ALL_FROM_JUCARII = "FROM Jucarii";
    private static final String HQL_MOVE_TOYS_WITH_ZERO_QUANTITY = "EliminaSiMutaJucariiZeroCantitate";
    private static final String HQL_SELECT_EXPENSIVE_AND_CHEAPEST_TOY = "FROM Jucarii j " +
            "WHERE j.pret = (SELECT MAX(p.pret) FROM Jucarii p) " +
            "OR j.pret = (SELECT MIN(p.pret) FROM Jucarii p)";
    private static final String HQL_SELECT_TOYS_AVG_DUPA_TARA = "SELECT AVG(j.pret) FROM Jucarii j WHERE j.taraProductie = :tara";
    private static final String HQL_INSERT_JUCARIE_MONDOVA = "CopiazaJucariiMoldova";
    private static final String HQL_SELECT_TOYS_WITH_FILTERS = "FROM Jucarii j " +
            "WHERE j.pret <= :maxPret AND j.varstaMinima >= :minVarsta AND j.varstaMinima <= :maxVarsta";
    private static final String HQL_SELECT_CELE_MAI_POPULARE_PRODUSE = "ObtinereCeleMaiPopulareProduse";
    private final SessionFactory sessionFactory;
    private static final Integer MAX_SIZE = 2;

    public DaoJucariiImpl() {
        this.sessionFactory = HibernateUtil.getInstance().getSessionFactory();
    }

    @Override
    public List<Jucarii> findAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Jucarii> query = session.createQuery(HQL_SELECT_ALL_FROM_JUCARII, Jucarii.class);
            return query.list();
        }
    }

    @Override
    public Jucarii findById(String id) {
        try (Session session = sessionFactory.openSession()) {
            return session.bySimpleNaturalId(Jucarii.class)
                    .load(id);
        }
    }

    @Override
    public void update(String id, Jucarii entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Jucarii jucarii = session.bySimpleNaturalId(Jucarii.class)
                    .load(id);

            try {
                jucarii.setNumeJucarie(entity.getNumeJucarie());
                jucarii.setPret(entity.getPret());
                jucarii.setCantitate(entity.getCantitate());
                jucarii.setTaraProductie(entity.getTaraProductie());
                jucarii.setVarstaMinima(entity.getVarstaMinima());
                session.merge(jucarii);
                session.getTransaction().commit();
            } catch (HibernateException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void delete(String id) {
        try (Session session = sessionFactory.openSession()) {
            Jucarii jucarii = session.bySimpleNaturalId(Jucarii.class)
                    .load(id);
            session.beginTransaction();
            session.evict(jucarii);
            session.remove(jucarii);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(Jucarii entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void excludeJucariiCuCantitateZero() {
        try (Session session = sessionFactory.openSession()) {
            StoredProcedureQuery query = session.createNamedStoredProcedureQuery(HQL_MOVE_TOYS_WITH_ZERO_QUANTITY);
            query.execute();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void afisareJucariiScumpeSiIeftine() {
        List<Jucarii> toys;

        try (Session session = sessionFactory.openSession()) {
            Query<Jucarii> query = session.createQuery(HQL_SELECT_EXPENSIVE_AND_CHEAPEST_TOY, Jucarii.class);
            toys = query.list();

            if (toys.size() >= MAX_SIZE) {
                System.out.println("Cea mai ieftina: " + toys.get(0));
                System.out.println("Cea mai scumpa: " + toys.get(1));
            }
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void selectToysAVGTara(String entity) {
        try (Session session = sessionFactory.openSession()) {
            Query<Double> query = session.createQuery(HQL_SELECT_TOYS_AVG_DUPA_TARA, Double.class);
            query.setParameter("tara", entity);
            Double avgPret = query.uniqueResult();

            if (avgPret != null) {
                System.out.println("Pretul mediu al jucariilor produse in " + entity + " este: " + avgPret);
            } else {
                System.out.println("Nu exista jucarii produse in " + entity);
            }
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertJucarieMoldova() {
        try (Session session = sessionFactory.openSession()) {
            StoredProcedureQuery query = session.createNamedStoredProcedureQuery(HQL_INSERT_JUCARIE_MONDOVA);
            query.execute();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void selectToysWithFilters(double x, int n1, int n2) {
        try (Session session = sessionFactory.openSession()) {
            Query<Jucarii> query = session.createQuery(HQL_SELECT_TOYS_WITH_FILTERS, Jucarii.class);
            query.setParameter("maxPret", x);
            query.setParameter("minVarsta", n1);
            query.setParameter("maxVarsta", n2);
            List<Jucarii> toys = query.list();

            System.out.println(toys);
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void selectCeleMaiPopulareProduse() {
        List<Object[]> toys;
        try (Session session = sessionFactory.openSession()) {
            StoredProcedureQuery query = session.createStoredProcedureQuery(HQL_SELECT_CELE_MAI_POPULARE_PRODUSE);
            toys = query.getResultList();

            for (Object[] jucarii : toys) {
                String name = (String) jucarii[0];
                BigDecimal quantity = (BigDecimal) jucarii[1];

                System.out.println("Nume Jucarie: " + name +
                        ", Cantitate Totala: " + quantity);
            }
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }
}
