package Negozietti;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Dipendente")
public class Dipendente {

    private int id = 0;
    private String nome = null;
    private String cognome = null;
    private String cell = null;

    private int idNegozio = 0;
    public Dipendente(){};

    public Dipendente(String nome, String cognome, String cell, int idNegozio) {
        this.nome = nome;
        this.cognome = cognome;
        this.cell = cell;
        this.idNegozio = idNegozio;
    }

    public Dipendente(int id, String nome, String cognome, String cell, int idNegozio) {

        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.cell = cell;
        this.idNegozio = idNegozio;
    }

    public int getId() {
        return id;
    }

    @XmlAttribute
    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    @XmlElement
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    @XmlElement
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCell() {
        return cell;
    }

    @XmlAttribute
    public void setCell(String cell) {
        this.cell = cell;
    }

    public int getIdNegozio() {
        return idNegozio;
    }
    @XmlAttribute
    public void setIdNegozio(int idNegozio) {
        this.idNegozio = idNegozio;
    }

    @Override
    public String toString() {
        return "Dipendente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", cell='" + cell + '\'' +
                ", idNegozio=" + idNegozio + "\n" +
                '}';
    }

    public String[] toRow() {

        String[] rows = new String[5];
        rows[0] = id + "";
        rows[1] = nome;
        rows[2] = cognome;
        rows[3] = cell;
        rows[4] = idNegozio + "";

        return rows;
    }

    public String toLine() {

        return id + ";" + nome + ";" + cognome + ";" + cell + ";" + idNegozio;
    }
}
