package org.example.Models;

import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "Papusile")
public class Papusile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Papusa")
    private Integer id;
    @NaturalId
    @Column(name = "codPapusa")
    private String cod;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_Jucarie")
    private Jucarii Jucarie;
    @Column(name = "Material")
    private String material;
    @Column(name = "Inaltime")
    private Double inaltime;

    public Papusile() {}

    public Papusile(String cod, Jucarii jucarie, String material, Double inaltime) {
        this.cod = cod;
        Jucarie = jucarie;
        this.material = material;
        this.inaltime = inaltime;
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

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Double getInaltime() {
        return inaltime;
    }

    public void setInaltime(Double inaltime) {
        this.inaltime = inaltime;
    }

    @Override
    public String toString() {
        return "\n\nPapusile\n{" +
                "\n\tcod='" + cod + '\'' +
                ", \n\tJucarie=" + Jucarie +
                ", \n\tmaterial='" + material + '\'' +
                ", \n\tinaltime=" + inaltime +
                "\n}";
    }
}
