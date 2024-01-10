package Negozietti.DAO;
;
import Negozietti.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FruttoDAO extends GenericDAO {

    public static int create(Frutto f) throws SQLException{

        String sql = "INSERT INTO frutto VALUES(NULL,'" + f.getNome() + "','" + f.getStagionalita().toString() + "'," + f.getCosto() + ");";

        GenericDAO.connect();

        Statement statement = GenericDAO.conn.createStatement();
        statement.executeUpdate(sql);
        ResultSet rs = statement.executeQuery("SELECT last_insert_rowid() as id;");
        int lastId = rs.getInt("id");

        GenericDAO.conn.close();
        f.setId(lastId);

        return lastId;
    }

    public static Frutto read(int id) throws SQLException{

        String sql = "SELECT * FROM frutto WHERE id = " + id + ";";
        GenericDAO.connect();
        Statement statement = GenericDAO.conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        rs.next();
        Frutto f =  new Frutto(rs.getInt("id"), rs.getString("nome"), Stagionalita.valueOf(rs.getString("stagionalita").toUpperCase()), rs.getInt("costo"));
        GenericDAO.conn.close();
        return f;
    }

    public static ArrayList<Object> readAll() throws SQLException{

        String sql = "SELECT * FROM frutto;";
        GenericDAO.connect();
        Statement statement = GenericDAO.conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        ArrayList<Object> frutti = new ArrayList<Object>();

        while(rs.next())
            frutti.add(new Frutto(rs.getInt("id"), rs.getString("nome"), Stagionalita.valueOf(rs.getString("stagionalita").toUpperCase()), rs.getInt("costo")));

        GenericDAO.conn.close();
        return frutti;

    }

    public static boolean update(Frutto f) throws SQLException{

        String sql = "UPDATE frutto SET nome = '" + f.getNome() + "',stagionalita = '" + f.getStagionalita().toString() + "',costo = " + f.getCosto() + " WHERE id = " + f.getId() + ";";
        GenericDAO.connect();

        Statement statement = GenericDAO.conn.createStatement();
        int rc = statement.executeUpdate(sql);

        return (rc!=1);   //falso: executeUpdate a buon fine, vero: executeUpdate errore
    }

    public static boolean delete(int id) throws SQLException{

        String sql = "DELETE FROM frutto WHERE id = " + id + ";";
        GenericDAO.connect();

        Statement statement = GenericDAO.conn.createStatement();
        int rc = statement.executeUpdate(sql);    //ritorna numero di colonne modificate

        return (rc!=1);   //falso: executeUpdate a buon fine, vero: executeUpdate errore
    }

    public static ArrayList<Frutto> readAllNegozio(int id) throws SQLException{ //legge tutti i frutti collegati al negozio con id

        String sql = "SELECT frutto.id, frutto.nome, frutto.stagionalita, frutto.costo FROM frutto, fruttiNegozi WHERE idNegozio = " + id + " AND frutto.id = fruttiNegozi.idFrutto;";
        ArrayList<Frutto> frutti = new ArrayList<>();
        Statement statement = GenericDAO.conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        while(rs.next())
            frutti.add(new Frutto(rs.getInt("id"), rs.getString("nome"), Stagionalita.valueOf(rs.getString("stagionalita").toUpperCase()), rs.getInt("costo")));

        return frutti;
    }

    public static void deleteAll() throws SQLException {

        String sql = "DELETE FROM frutto;";
        GenericDAO.connect();
        Statement statement = GenericDAO.conn.createStatement();
        statement.executeUpdate(sql);
        GenericDAO.conn.close();
    }
}
