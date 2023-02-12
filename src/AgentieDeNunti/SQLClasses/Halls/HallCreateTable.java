package AgentieDeNunti.SQLClasses.Halls;

import AgentieDeNunti.SQLClasses.ConnectToDatabase;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class to create the table in the database.
 */
public class HallCreateTable extends ConnectToDatabase {
    private final Statement STATEMENT = super.getConnection().createStatement();

    /**
     * Constructor that creates the table in the sqlite database
     * @param databaseName the name of the database.
     * @throws SQLException is thrown in case that the database does not exist.
     */
    public HallCreateTable(String databaseName) throws  SQLException {
        super(databaseName);

        String createTable = new StringBuilder()
                .append("CREATE TABLE IF NOT EXISTS Halls \n(\n")
                .append("\tIdHall INT NOT NULL PRIMARY KEY,\n")
                .append("\tName VARCHAR(50) NOT NULL,\n")
                .append("\tCountry VARCHAR(50) NOT NULL,\n")
                .append("\tCity VARCHAR(50) NOT NULL,\n")
                .append("\tStreet VARCHAR(50) NOT NULL,\n")
                .append("\tNumGuests INT NOT NULL,\n")
                .append("\tPricePerPerson INT NOT NULL)\n").toString();

        STATEMENT.executeUpdate(createTable);
        super.getConnection().setAutoCommit(false);
        super.getConnection().commit();
    }

    /**
     * @return returns statement to reduce the memory usage.
     */
    public Statement getSTATEMENT() {
        return STATEMENT;
    }
}
