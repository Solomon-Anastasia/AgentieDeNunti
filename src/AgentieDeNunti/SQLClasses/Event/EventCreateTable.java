package AgentieDeNunti.SQLClasses.Event;

import AgentieDeNunti.SQLClasses.ConnectToDatabase;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class to create the table in the database.
 */
public class EventCreateTable extends ConnectToDatabase {
    private final Statement STATEMENT = super.getConnection().createStatement();

    /**
     * Constructor that creates the table in the sqlite database
     * @param databaseName the name of the database.
     * @throws SQLException is thrown in case that the database does not exist.
     */
    public EventCreateTable(String databaseName) throws SQLException {
        super(databaseName);

        String createTable = new StringBuilder()
                .append("CREATE TABLE IF NOT EXISTS Events \n(\n")
                .append("\tIdEvent INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n")
                .append("\tTypeEvent VARCHAR(10) NOT NULL,\n")
                .append("\tDateOfEvent DATE NOT NULL,\n")
                .append("\tNumOfPersons INT NOT NULL,\n")
                .append("\tIdHall INT NOT NULL,\n")
                .append("\tPrice DOUBLE NOT NULL,\n")
                .append("\tFOREIGN KEY(IdHall) REFERENCES Halls(IdHall))\n").toString();
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
