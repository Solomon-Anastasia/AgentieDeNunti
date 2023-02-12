package AgentieDeNunti.SQLClasses;

import AgentieDeNunti.SQLClasses.Event.EventReadFromCSV;
import AgentieDeNunti.SQLClasses.Halls.HallReadFromCSV;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Class used to make the connection, create the database,
 * read the information from csv and insert it to database.
 */
public class SQLDataBase {
    public SQLDataBase() {
        try {
            new HallReadFromCSV("AgentieDeNunti");
            new EventReadFromCSV("AgentieDeNunti");
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }
}
