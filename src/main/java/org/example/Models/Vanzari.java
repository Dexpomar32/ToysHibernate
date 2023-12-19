package org.example.Models;

import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

import java.sql.Date;

@Entity
@Table(name = "Vanzari")
public class Vanzari {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Vanzare")
    private Integer id;
    @NaturalId
    @Column(name = "codVanzare")
    private String cod;
    @ManyToOne
    @JoinColumn(name = "ID_Jucarie")
    private Jucarii Jucarie;
    @Column(name = "Data_Vanzare")
    private Date dataVanzare;
    @Column(name = "Cantitate_Vanduta")
    private Integer cantitateaVanduta;

    public Vanzari() {}

    public Vanzari(String cod, Jucarii jucarie, Date dataVanzare, Integer cantitateaVanduta) {
        this.cod = cod;
        Jucarie = jucarie;
        this.dataVanzare = dataVanzare;
        this.cantitateaVanduta = cantitateaVanduta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Jucarii getJucarie() {
        return Jucarie;
    }

    public void setJucarie(Jucarii jucarie) {
        Jucarie = jucarie;
    }

    public Date getDataVanzare() {
        return dataVanzare;
    }

    public void setDataVanzare(Date dataVanzare) {
        this.dataVanzare = dataVanzare;
    }

    public Integer getCantitateaVanduta() {
        return cantitateaVanduta;
    }

    public void setCantitateaVanduta(Integer cantitateaVanduta) {
        this.cantitateaVanduta = cantitateaVanduta;
    }

    @Override
    public String toString() {
        return "\n\nVanzari\n{" +
                "\n\tcod='" + cod + '\'' +
                ", \n\tJucarie=" + Jucarie +
                ", \n\tdataVanzare=" + dataVanzare +
                ", \n\tcantitateaVanduta=" + cantitateaVanduta +
                "\n}";
    }
}
