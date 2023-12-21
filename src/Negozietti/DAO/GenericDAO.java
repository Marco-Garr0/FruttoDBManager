package Negozietti.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class GenericDAO {

    protected static Connection conn = null;
    private static final String URL_DB_HEADER = "jdbc:sqlite:";
    private static String dbName = null;

    public static void setDbName(String dbName){

        GenericDAO.dbName = dbName;
    }
    public static void connect() throws SQLException{

        GenericDAO.conn = DriverManager.getConnection(URL_DB_HEADER + GenericDAO.dbName);
    }

    public static int create(Object o) throws SQLException{

        return -2;
    }

    public static Object read(int id) throws SQLException{

        return null;
    }

    public static ArrayList<Object> readAll() throws SQLException{

        return null;
    }

    public static boolean update(Object o) throws SQLException{
        return true;
    }

    public static boolean delete(int id) throws SQLException{

        return true;
    }
}
