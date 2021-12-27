package by.tms.project.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * The Class MySQLConnection.
 *
 * @author ShchbetovaEK
 */
public class MySQLConnection {
    private static final Logger logger = LogManager.getLogger();
    private static final String DATA_BASE_URL = "jdbc:mysql://localhost/clinic?serverTimezone=Europe/Moscow";
    private static final String DATA_BASE_USER = "root";
    private static final String DATA_BASE_PASSWORD = "root";

    /**
     * Get connection.
     *
     * @return the connection
     * @throws SQLException the SQL exception
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATA_BASE_URL, DATA_BASE_USER, DATA_BASE_PASSWORD);
    }

    private MySQLConnection() {

    }
}
