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

    public static void main(String[] args) {

        try{
            String connectionStr = "jdbc:sqlite:./frutto.db";

            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(connectionStr);
            if(conn != null)
                System.out.println("yuppy, connessione verificata");

            Statement st = conn.createStatement();

            //print del singolo Frutto da db
            String sql = "SELECT id, nome, Stagionalita, costo FROM frutto WHERE id=2;";
            ResultSet rs = st.executeQuery(sql);
            rs.next();

            Frutto frutto = new Frutto(rs.getString("nome"), Stagionalita.valueOf(rs.getString("stagionalita").toUpperCase()), rs.getInt("costo"));
            System.out.println("\nfrutto singolo\n");                                                     //rs ha coppie chiave-valore
            System.out.println(frutto);
            //
            //print dell'intera tabella salvata in un ArrayList
            ArrayList<Frutto> frutti = new ArrayList<Frutto>();
            String sqlAll = "SELECT id, nome, Stagionalita, costo FROM frutto;";
            rs = st.executeQuery(sqlAll);

            System.out.println("\ncollezione da db\n");
            while(rs.next())
                frutti.add(new Frutto(rs.getString("nome"), Stagionalita.valueOf(rs.getString("stagionalita").toUpperCase()), rs.getInt("costo")));

            System.out.println(frutti);
            conn.close();
            //
            /*
            //uso di FruttoDAO

            //uso di FruttoDAO.create()

            Frutto fruttoToCreate = new Frutto("papaya", Stagionalita.DEFAULT, 7);
            //FruttoDAO.create(fruttoToCreate);

            //

            //uso di FruttoDAO.read()
            Frutto fruttoFromRead = FruttoDAO.read(2);
            System.out.println("\nfrutto da FruttoDAO.read()\n");
            System.out.println(fruttoFromRead);
            //

            //uso di FruttoDAO.delete()

            int id = 3;
            //boolean deleteRet = FruttoDAO.delete(id);      //uso del metodo delete
            //if(deleteRet)
                System.out.println("errore durante l'eliminazione dell'id: " + id);

            //

            //uso di FruttoDAO.readAll()
            ArrayList<Object> genericCollection = FruttoDAO.readAll();
            System.out.println("\nprint della collezione letta tramite FruttoDAO.readAll()\n");
            System.out.println(genericCollection);
            //

            //uso di FruttoDAO.update()
            Frutto fruttoToUpdate = new Frutto("mela", Stagionalita.AUTUNNALE, 3, 6);
            boolean updateRet = FruttoDAO.update(fruttoToUpdate);
            if(updateRet)
                System.out.println("errore in FruttoDAO.update()");
            //
            */
            //uso di DipendentiDAO
            //uso di DipendentiDAO.create()
            //DipendenteDAO.create(new Dipendente("Fabio", "Puzzo", "34659787364", 3));
            //
            //uso di DipendentiDAO.read()
            Dipendente dipendenteFromRead = DipendenteDAO.read(1);
            System.out.println("\nDipendente da DipendenteDAO.read()\n");
            System.out.println(dipendenteFromRead.toString());
            //uso di DipendentiDAO.readAll()
            ArrayList<Object> dipendentiFromReadAll = DipendenteDAO.readAll();
            System.out.println("\nArrayList di Dipendente da DipendenteDAO.readAll()\n");
            System.out.println(dipendentiFromReadAll.toString());
            //
            //uso di DipendentiDAO.update()
            boolean updateReturn = DipendenteDAO.update(new Dipendente(2, "Matteo", "martini", "39872530", 1));
            if(updateReturn)
                System.out.println("errrore in DipendentiDAO.update()");
            //
            //uso di DipendentiDAO.delete()
            //DipendenteDAO.delete(4);
            //

            //uso di NegoziDAO
            //uso di NegoziDAO.create()
            //NegozioDAO.create(new Negozio("Ceva Frutta", "inferno", null, null));
            //
            //uso di NegoziDAO.read()
            Negozio n = NegozioDAO.read(3);
            System.out.println("\nNegozio da NegoziDAO.read()\n");
            System.out.println(n.toString());
            //
            //uso di NegoziDAO.readAll()
            ArrayList<Object> negozi = NegozioDAO.readAll();
            System.out.println("\nNegozi da NegoziDAO.readAll()\n");
            System.out.println(negozi);
            //uso di NegozioDAO.update()
            boolean returnUPdateNeg = NegozioDAO.update(new Negozio(3, "murialdo macelleria", "murialdo",null,null));
            if(returnUPdateNeg)
                System.out.println("errrore in DipendentiDAO.update()");
            //
            //uso di NeogziDAO.delete()
            //NegozioDAO.delete(4);
        } catch(SQLException | ClassNotFoundException ex){
            ex.printStackTrace();
        }
    }
}
