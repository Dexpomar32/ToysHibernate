package org.example.Models;

import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

@Entity()
@Table(name = "Jucarii")
@NamedStoredProcedureQuery(
        name = "EliminaSiMutaJucariiZeroCantitate",
        procedureName = "EliminaSiMutaJucariiZeroCantitate",
        resultClasses = Jucarii.class
)
@NamedStoredProcedureQuery(
        name = "CopiazaJucariiMoldova",
        procedureName = "CopiazaJucariiMoldova",
        resultClasses = Jucarii.class
)
@NamedStoredProcedureQuery(
        name = "ObtinereCeleMaiPopulareProduse",
        procedureName = "ObtinereCeleMaiPopulareProduse",
        resultClasses = Jucarii.class
)
public class Jucarii {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Jucarie")
    private Integer id;
    @NaturalId
    @Column(name = "codJucarii")
    private String cod;
    @Column(name = "Nume_Jucarie")
    private String numeJucarie;
    @Column(name = "Pret")
    private Double pret;
    @Column(name = "Cantitate")
    private Integer cantitate;
    @Column(name = "Tara_Productie")
    private String taraProductie;
    @Column(name = "Varsta_Minima")
    private Integer varstaMinima;

    public Jucarii() {}

    public Jucarii(String cod, String numeJucarie, Double pret, Integer cantitate, String taraProductie, Integer varstaMinima) {
        this.cod = cod;
        this.numeJucarie = numeJucarie;
        this.pret = pret;
        this.cantitate = cantitate;
        this.taraProductie = taraProductie;
        this.varstaMinima = varstaMinima;
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

    public String getNumeJucarie() {
        return numeJucarie;
    }

    public void setNumeJucarie(String numeJucarie) {
        this.numeJucarie = numeJucarie;
    }

    public Double getPret() {
        return pret;
    }

    public void setPret(Double pret) {
        this.pret = pret;
    }

    public Integer getCantitate() {
        return cantitate;
    }

    public void setCantitate(Integer cantitate) {
        this.cantitate = cantitate;
    }

    public String getTaraProductie() {
        return taraProductie;
    }

    public void setTaraProductie(String taraProductie) {
        this.taraProductie = taraProductie;
    }

    public Integer getVarstaMinima() {
        return varstaMinima;
    }

    public void setVarstaMinima(Integer varstaMinima) {
        this.varstaMinima = varstaMinima;
    }

    @Override
    public String toString() {
        return "\n\nJucarii\n{" +
                "\n\tcodJucarie='" + cod + '\'' +
                ", \n\tnumeJucarie='" + numeJucarie + '\'' +
                ", \n\tpret=" + pret +
                ", \n\tcantitate=" + cantitate +
                ", \n\ttaraProductie='" + taraProductie + '\'' +
                ", \n\tvarstaMinima=" + varstaMinima +
                "\n}";
    }
}
