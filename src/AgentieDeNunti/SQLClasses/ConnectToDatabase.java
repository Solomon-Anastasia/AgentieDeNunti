package AgentieDeNunti.SQLClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class used to make the connection between the database and other
 * classes to get access to the methods of sql jdbc.
 */
public class ConnectToDatabase {
    private Connection connection;

    /**
     * Constructor that makes the connection.
     * @param databaseName the name of the database.
     */
    public ConnectToDatabase(String databaseName) {
        String url = "jdbc:sqlite:" + databaseName + ".db";
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Getter to get the connection and not create
     * a lot of connections.
     * @return connection.
     */
    public Connection getConnection() {
        return connection;
    }
}
