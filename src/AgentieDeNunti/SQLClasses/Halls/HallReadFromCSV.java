package AgentieDeNunti.SQLClasses.Halls;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Class used to read all the data from .csv
 * and insert it to the database.
 */
public class HallReadFromCSV extends HallCreateTable {

    /**
     * Constructor that reads all the data from .csv file.
     * @param databaseName the name of the database.
     * @throws SQLException thrown in case that there is no database.
     * @throws IOException thrown in case of inadequate input.
     */
    public HallReadFromCSV(String databaseName) throws SQLException, IOException {
        super(databaseName);

        Statement statement = super.getConnection().createStatement();
        File myFile = new File("Halls.csv");
        Scanner read = new Scanner(myFile);
        String lineText;

        read.nextLine();

        //Reading from csv and insert to database
        while (read.hasNext()) {
            lineText = read.nextLine();
            String[] data = lineText.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

            String sql = new StringBuilder()
                    .append("INSERT INTO Halls(idHall, Name, Country, City, Street, NumGuests, PricePerPerson) VALUES ")
                    .append("('")
                    .append(Integer.parseInt(data[0])).append("', '")
                    .append(data[1]).append("', '")
                    .append(data[2]).append("', '")
                    .append(data[3]).append("', '")
                    .append(data[4]).append("', '")
                    .append(Integer.parseInt(data[5])).append("', '")
                    .append(Integer.parseInt(data[6])).append("')").toString();

                super.getSTATEMENT().executeUpdate(sql);
                super.getConnection().commit();
        }
        statement.close();
        read.close();
        super.getConnection().close();
        super.getSTATEMENT().close();
    }
}
