package Negozietti;

import java.util.ArrayList;

public class Negozio {

    private int id = 0;
    private String nome = null;
    private String sede = null;
    private ArrayList<Frutto> frutti = new ArrayList<>();

    private  ArrayList<Dipendente> dipendenti = new ArrayList<>();
    public Negozio(){}

    public Negozio(String nome, String sede, ArrayList<Frutto> frutti, ArrayList<Dipendente> dipendenti) {
        this.nome = nome;
        this.sede = sede;
        this.frutti = frutti;
        this.dipendenti = dipendenti;
    }

    public Negozio(int id, String nome, String sede, ArrayList<Frutto> frutti, ArrayList<Dipendente> dipendenti) {
        this.id = id;
        this.nome = nome;
        this.sede = sede;
        this.frutti = frutti;
        this.dipendenti = dipendenti;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public ArrayList<Frutto> getFrutti() {
        return frutti;
    }

    public void setFrutti(ArrayList<Frutto> frutti) {
        this.frutti = frutti;
    }
    public void addFrutti(Frutto f){
        this.frutti.add(f);
    }

    public ArrayList<Dipendente> getDipendenti() {
        return dipendenti;
    }

    public void setDipendenti(ArrayList<Dipendente> dipendenti) {
        this.dipendenti = dipendenti;
    }

    @Override
    public String toString() {
        return "Negozio{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sede='" + sede + '\'' +
                ", frutti=" + frutti +
                ", dipendenti=" + dipendenti + "\n" +
                '}';
    }

    public String[] toRow() {
        String[] fields = new String[3];
        fields[0] = id + "";
        fields[1] = nome;
        fields[2] = sede;
        return fields;
    }
}
