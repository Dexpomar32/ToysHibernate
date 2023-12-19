package org.example.Models;

import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

@Entity()
@Table(name = "Categorii")
public class Categorii {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Categorie")
    private Integer id;
    @NaturalId
    @Column(name = "codCategorie")
    private String cod;
    @Column(name = "Nume_Categorie")
    private String numeCategorie;

    public Categorii() {}

    public Categorii(String cod, String numeCategorie) {
        this.cod = cod;
        this.numeCategorie = numeCategorie;
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

    public String getNumeCategorie() {
        return numeCategorie;
    }

    public void setNumeCategorie(String numeCategorie) {
        this.numeCategorie = numeCategorie;
    }

    @Override
    public String toString() {
        return "\n\nCategorii\n{" +
                "\n\tcod='" + cod + '\'' +
                ", \n\tnumeCategorie='" + numeCategorie + '\'' +
                "\n}";
    }
}