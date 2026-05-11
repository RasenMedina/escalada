package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe per gestionar la connexió amb la base de dades
 */
public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/escalada";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    /**
     * Obté una connexió amb la base de dades
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
