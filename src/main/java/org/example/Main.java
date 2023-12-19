package org.example;

import org.example.Dao.DaoImpl.DaoJucariiImpl;
import org.example.Dao.DaoImpl.DaoPapusileImpl;
import org.example.Dao.DaoImpl.DaoVanzariImpl;
import org.example.Dao.DaoModels.DaoJucarii;
import org.example.Dao.DaoModels.DaoPapusile;
import org.example.Dao.DaoModels.DaoVanzari;
import org.example.Dao.Exceptions.DaoException;
import org.example.Models.Jucarii;
import org.example.Util.HibernateUtil;

import java.util.logging.Level;

public class Main {
    public static void main(String[] args) throws DaoException {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        DaoJucarii daoJucarii = new DaoJucariiImpl();
        DaoPapusile daoPapusile = new DaoPapusileImpl();
        DaoVanzari daoVanzari = new DaoVanzariImpl();
        Jucarii jucarii = new Jucarii("codTest01", "testname", 45.52, 0, "Moldova", 10);
        Jucarii jucariiUpdate = new Jucarii("codTest01", "update", 12.12, 20, "China", 15);

//        System.out.println(daoJucarii.findAll());
//        System.out.println(daoJucarii.findById("COD001"));
//        daoJucarii.insert(jucariiUpdate);
//        daoJucarii.update("codTest01", jucarii);
//        daoJucarii.delete("codTest01");
//        daoJucarii.excludeJucariiCuCantitateZero();
//        daoJucarii.afisareJucariiScumpeSiIeftine();
//        daoJucarii.selectToysAVGTara("China");
//        daoJucarii.insertJucarieMoldova();
//        daoJucarii.selectToysWithFilters(50.0, 4, 10);
//        daoJucarii.selectCeleMaiPopulareProduse();
//        daoPapusile.selectPapusiAsc();
//        daoVanzari.selectProduseVanduteInZi("2023-10-01");

        HibernateUtil.getInstance().getSessionFactory().close();
    }
}