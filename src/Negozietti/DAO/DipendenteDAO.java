package Negozietti.DAO;

import Negozietti.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DipendenteDAO extends GenericDAO {

    public static int create(Dipendente d) throws SQLException {

        String sql = "INSERT INTO dipendenti VALUES (NULL,'" + d.getNome() + "','" + d.getCognome() + "','" + d.getCell() + "'," + d.getIdNegozio() + ");";
        int lastId = 0;
        GenericDAO.connect();

        Statement statement = GenericDAO.conn.createStatement();
        statement.executeUpdate(sql);
        ResultSet rs = statement.executeQuery("SELECT last_insert_rowid() as id;");
        lastId = rs.getInt("id");

        GenericDAO.conn.close();
        d.setId(lastId);

        return lastId;
    }

    public static Dipendente read(int id) throws SQLException{

        String sql = "SELECT * FROM dipendenti WHERE idDipendente = " + id + ";";
        GenericDAO.connect();
        Statement statement = GenericDAO.conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        rs.next();
        Dipendente d = new Dipendente(rs.getInt("idDipendente"),rs.getString("nome"), rs.getString("cognome"), rs.getString("cellulare"), rs.getInt("idNegozio"));
        GenericDAO.conn.close();
        return d;
    }

    public static ArrayList<Object> readAll() throws SQLException{

        String sql = "SELECT * FROM dipendenti;";
        GenericDAO.connect();
        Statement statement = GenericDAO.conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        ArrayList<Object> dipendenti = new ArrayList<Object>();

        while(rs.next())
            dipendenti.add(new Dipendente(rs.getInt("idDipendente"),rs.getString("nome"), rs.getString("cognome"), rs.getString("cellulare"),rs.getInt("idNegozio")));

        GenericDAO.conn.close();
        return dipendenti;
    }

    public static boolean update(Dipendente d) throws SQLException {
        String sql = "UPDATE dipendenti SET nome = '" + d.getNome() + "',cognome = '" + d.getCognome() + "',cellulare = '" + d.getCell() + "' WHERE idDipendente = " + d.getId() + ";";
        GenericDAO.connect();

        Statement statement = GenericDAO.conn.createStatement();
        int rc = statement.executeUpdate(sql);

        return (rc != 1);   //falso: executeUpdate a buon fine, vero: executeUpdate errore    }
    }
    public static boolean delete(int id) throws SQLException{

        String sql = "DELETE FROM dipendenti WHERE idDipendente = " + id + ";";
        GenericDAO.connect();

        Statement statement = GenericDAO.conn.createStatement();
        int rc = statement.executeUpdate(sql);    //ritorna numero di colonne modificate

        return (rc!=1);   //falso: executeUpdate a buon fine, vero: executeUpdate errore
    }

    public static ArrayList<Dipendente> readAllNegozi(int id) throws SQLException{  //ritorna tutti i dipendenti collegati al negozio 'id'

        String sql = "SELECT dipendenti.idDIpendente, dipendenti.nome, dipendenti.cognome, dipendenti.cellulare, dipendenti.idNegozio FROM dipendenti, negozi WHERE dipendenti.idNegozio = " + id + " AND negozi.idNegozio = dipendenti.idNegozio;";
        Statement statement = GenericDAO.conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        ArrayList<Dipendente> dipendenti = new ArrayList<Dipendente>();

        while(rs.next())
            dipendenti.add(new Dipendente(rs.getInt("idDipendente"),rs.getString("nome"), rs.getString("cognome"), rs.getString("cellulare"),rs.getInt("idNegozio")));

        return dipendenti;
    }
}
