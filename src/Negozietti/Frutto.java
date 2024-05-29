package Negozietti;

import Negozietti.DAO.*;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.sql.*;
import java.util.ArrayList;
@XmlRootElement(name = "Frutto")
public class Frutto {

    private int id = 0;
    private String nome = null;
    private int costo = 0;
    private Stagionalita stagione = Stagionalita.DEFAULT;

    public Frutto(){}

    public Frutto(String nome, Stagionalita stagione, int costo){

        this.nome = nome;
        this.stagione = stagione;
        this.costo = costo;
    }

    public Frutto(int id, String nome, Stagionalita stagione, int costo){

        this.nome = nome;
        this.stagione = stagione;
        this.costo = costo;
        this.id = id;
    }

    public Frutto(String line){    //costruttore con linea csv

        String[] fields = line.split("[;,]"); //regex per indicare che il separatore puà essere ';' o ','
        this.nome = fields[0];
        this.stagione = Stagionalita.valueOf(fields[1]);
        this.costo = Integer.parseInt(fields[2]);   //metodo statico per parsare da String a int
        this.id = Integer.parseInt(fields[3]);
    }

    public String getNome(){

        return nome;
    }

    public Stagionalita getStagionalita(){

        return stagione;
    }

    public int getCosto(){

        return costo;
    }

    public int getId(){

        return id;
    }
    @XmlElement
    public void setNome(String nome){

        this.nome = nome;
    }
    @XmlElement
    public void setStagionalita(Stagionalita stagione){

        this.stagione = stagione;
    }
    @XmlAttribute
    public void setCosto(int costo){

        this.costo = costo;
    }

    public void setId(int id){

        this.id = id;
    }

    @Override
    public String toString() {
        return "Frutto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", costo=" + costo +
                ", stagione=" + stagione + "\n" +
                '}';
    }

    public String toLine(){ //stringa formattata per csv

        return nome + ";" + stagione + ";" + costo + ";" + id;
    }

    public String[] toRow(){  //dato un oggetto ritorna un array di stringhe con gli attributi

        String[] ret = new String[4];
        ret[0] = this.id + "";
        ret[1] = this.nome;
        ret[2] = this.stagione.toString();
        ret[3] = this.costo + "";

        return ret;
    }
}
