package Negozietti.DAO;

import Negozietti.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class NegozioDAO extends GenericDAO{

    public static int create(Negozio n) throws SQLException{

        String sql = "INSERT INTO negozi VALUES (NULL,'" + n.getNome() + "','" + n.getSede() + "');";
        int lastId = 0;
        GenericDAO.connect();

        PreparedStatement ps = GenericDAO.conn.prepareStatement(sql);
        ps.executeUpdate();
        ps = GenericDAO.conn.prepareStatement("SELECT last_insert_rowid() as id;");
        ResultSet rs = ps.executeQuery();
        lastId = rs.getInt("id");

        GenericDAO.conn.close();
        n.setId(lastId);

        return lastId;
    }

    public static Negozio read(int id) throws SQLException{

        String sql = "SELECT * FROM negozi WHERE idNegozio = " + id + ";";
        GenericDAO.connect();
        Statement statement = GenericDAO.conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        ArrayList<Frutto> frutti = FruttoDAO.readAllNegozio(id);
        ArrayList<Dipendente> dipendenti = DipendenteDAO.readAllNegozi(id);
        if(rs != null){
            rs.next();
            Negozio n = new Negozio(rs.getInt("idNegozio"),rs.getString("nome"), rs.getString("sede"), FruttoDAO.readAllNegozio(id),DipendenteDAO.readAllNegozi(id));
            GenericDAO.conn.close();
            return n;
        }

        GenericDAO.conn.close();
        return null;
    }

    public static ArrayList<Object> readAll() throws SQLException{

        String sql = "SELECT * FROM negozi;";
        GenericDAO.connect();
        Statement statement = GenericDAO.conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        ArrayList<Object> negozi = new ArrayList<Object>();

        if(rs != null){

            while(rs.next())
                negozi.add(new Negozio(rs.getInt("idNegozio"),rs.getString("nome"), rs.getString("sede"), FruttoDAO.readAllNegozio(rs.getInt("idNegozio")),DipendenteDAO.readAllNegozi(rs.getInt("idNegozio"))));

            GenericDAO.conn.close();
            return negozi;
        }
        GenericDAO.conn.close();
        return null;
    }

    public static boolean update(Negozio n) throws SQLException{

        String sql = "UPDATE negozi SET nome = '" + n.getNome() +"',sede = '" + n.getSede()  + "' WHERE idNegozio = " + n.getId() + ";";
        GenericDAO.connect();

        Statement statement = GenericDAO.conn.createStatement();
        int rc = statement.executeUpdate(sql);
        GenericDAO.conn.close();
        return (rc!=1);   //falso: executeUpdate a buon fine, vero: executeUpdate errore
    }

    public static boolean delete(int id) throws SQLException{

        String sql = "DELETE FROM negozi WHERE idNegozio = " + id + ";";
        GenericDAO.connect();

        Statement statement = GenericDAO.conn.createStatement();
        int rc = statement.executeUpdate(sql);    //ritorna numero di colonne modificate
        GenericDAO.conn.close();
        return (rc!=1);   //falso: executeUpdate a buon fine, vero: executeUpdate errore
    }

    public static ArrayList<String> readAllIdNegozio() throws SQLException{

        String sql = "SELECT idNegozio FROM negozi;";
        GenericDAO.connect();
        Statement statement = GenericDAO.conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        ArrayList<String> negozi = new ArrayList<>();

        if(rs != null){

            while(rs.next())
                negozi.add(rs.getString("idNegozio"));

            GenericDAO.conn.close();
            return negozi;
        }
        GenericDAO.conn.close();
        return null;
    }

    public static void deleteAll() throws SQLException {

        String sql = "DELETE FROM negozi;";
        GenericDAO.connect();
        Statement statement = GenericDAO.conn.createStatement();
        statement.executeUpdate(sql);
        GenericDAO.conn.close();
    }
}

