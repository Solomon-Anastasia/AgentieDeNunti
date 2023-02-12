package AgentieDeNunti.SQLClasses.Event;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Class used to read all the data from .csv
 * and insert it to the database.
 */
public class EventReadFromCSV extends EventCreateTable {

    /**
     * Constructor that reads all the data from .csv file.
     * @param databaseName the name of the database.
     * @throws SQLException thrown in case that there is no database.
     * @throws IOException thrown in case of inadequate input.
     */
    public EventReadFromCSV(String databaseName) throws SQLException, IOException {
        super(databaseName);

        Statement statement = super.getConnection().createStatement();
        File myFile = new File("Events.csv");
        Scanner read = new Scanner(myFile);
        String lineText;

        read.nextLine();

        //Reading from csv and insert to database
        while (read.hasNext()) {
            lineText = read.nextLine();
            String[] data = lineText.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

            String sql = new StringBuilder()
                    .append("INSERT INTO Events(TypeEvent, DateOfEvent, NumOfPersons, IdHall, Price) VALUES ")
                    .append("('")
                    .append(data[0]).append("', '")
                    .append(Integer.parseInt(data[1])).append("-")
                    .append(Integer.parseInt(data[2])).append("-")
                    .append(Integer.parseInt(data[3])).append("', '")
                    .append(Integer.parseInt(data[4])).append("', '")
                    .append(Integer.parseInt(data[5])).append("', '")
                    .append(Double.parseDouble(data[6])).append("')").toString();

            super.getSTATEMENT().executeUpdate(sql);
            super.getConnection().commit();
        }
        statement.close();
        read.close();
        super.getSTATEMENT().close();
        super.getConnection().close();
    }
}
