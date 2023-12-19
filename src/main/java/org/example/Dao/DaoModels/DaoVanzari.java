package org.example.Dao.DaoModels;

import org.example.Dao.DaoBase;
import org.example.Models.Vanzari;

public interface DaoVanzari extends DaoBase<String, Vanzari> {
    void selectProduseVanduteInZi(String data);
}
