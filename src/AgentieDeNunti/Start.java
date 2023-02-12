package AgentieDeNunti;

import AgentieDeNunti.Menu.Menu;
import AgentieDeNunti.Classes.*;
import AgentieDeNunti.Classes.Date;
import AgentieDeNunti.SQLClasses.ConnectToDatabase;
import AgentieDeNunti.SQLClasses.SQLDataBase;

import java.io.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * Class used to make the main functional of the project.
 */
public class Start extends ConnectToDatabase {
    private ArrayList<Event> events = new ArrayList<>();
    private ArrayList<Hall> halls = new ArrayList<>();
    private ArrayList<Hall> hallsAux;

    /**
     * Class that has the menu and the functional shown to the user.
     */
    public Start() {
        super("AgentieDeNunti");

        //Inserting all the data to .csv files
        getHallsFromCSV();
        createEvent();
        insertEventsInCSV();

        //Creating the database
        new SQLDataBase();

        Scanner s = new Scanner(System.in);
        String iterator = "-1";

        //The menu
        while (!iterator.equals("0")) {
            Menu.getMenu();
            System.out.print("Enter key: ");
            iterator = s.next();
            System.out.println();
            Menu.cls();

            switch (iterator) {
                case "0" -> System.out.println("Goodbye!");
                case "1" -> {
                    String nrOfGuests = null;
                    boolean checkNumber = false;
                    boolean checkHalls = false;

                    Hall hallEvent = null;

                    List<Person> guests = new ArrayList<>();
                    List<Person> grooms = new ArrayList<>();
                    String name;
                    String surname;

                    Date dateEvent;
                    String year = null;
                    String month = null;
                    String day = null;

                    TypeEvent typeEvent;
                    String choosenTypeEvent = null;

                    if (hallsAux.isEmpty()) {
                        System.out.println("All halls are busy!");
                    } else {
                        while (!checkNumber) {
                            System.out.print("Number of guests: ");
                            nrOfGuests = s.next();

                            if (InsertException.setNumberException(nrOfGuests)) {
                                for (Hall i : halls) {
                                    if (Integer.parseInt(nrOfGuests) <= i.getNumGuests()) {
                                        checkNumber = true;
                                        checkHalls = true;
                                    }
                                }
                                if (!checkHalls)
                                    System.out.println("There is no hall with this much nr. of guests!");
                            } else System.out.println("Wrong input! Try again");
                        }

                        for (Hall i : hallsAux) {
                            if (i.getNumGuests() >= Integer.parseInt(nrOfGuests)) {
                                hallEvent = i;
                                hallsAux.remove(i);
                                break;
                            }
                        }

                        System.out.println("--Enter list of guests--");
                        for (int i = 0; i < Integer.parseInt(nrOfGuests); i++) {
                            System.out.print("Name: ");
                            name = s.next();
                            System.out.print("Surname: ");
                            surname = s.next();
                            System.out.println();
                            guests.add(new Person(name, surname));
                        }

                        System.out.println("--Enter grooms--");
                        for (int i = 0; i < 2; i++) {
                            System.out.print("Name: ");
                            name = s.next();
                            System.out.print("Surname: ");
                            surname = s.next();
                            System.out.println();
                            grooms.add(new Person(name, surname));
                        }

                        checkNumber = false;
                        while (!checkNumber) {
                            System.out.print("Enter date (y/m/d): ");
                            year = s.next();
                            month = s.next();
                            day = s.next();

                            if (InsertException.setDateException(year, month, day)) {
                                checkNumber = true;
                            } else System.out.println("Invalid date! Try again");
                        }
                        dateEvent = new Date(year, month, day);

                        checkNumber = false;
                        while (!checkNumber) {
                            System.out.print("Enter type event (1 - wedding || 2 - splice): ");
                            choosenTypeEvent = s.next();

                            if (choosenTypeEvent.equals("1") || choosenTypeEvent.equals("2")
                                    && InsertException.setNumberException(choosenTypeEvent)) {
                                checkNumber = true;
                            } else System.out.println("Wrong input! Try again");
                        }

                        if (choosenTypeEvent.equals("1")) {
                            typeEvent = TypeEvent.WEDDING;
                        } else typeEvent = TypeEvent.SPLICE;

                        events.add(new Event(nrOfGuests, hallEvent, guests, grooms, dateEvent, typeEvent));

                        Statement statement;
                        try {
                            statement = super.getConnection().createStatement();
                            String sql = new StringBuilder()
                                    .append("INSERT INTO Events(TypeEvent, DateOfEvent, NumOfPersons, IdHall, Price) VALUES ")
                                    .append("('")
                                    .append(typeEvent).append("', '")
                                    .append(dateEvent.getYEAR()).append("-")
                                    .append(dateEvent.getMONTH()).append("-")
                                    .append(dateEvent.getDAY()).append("', '")
                                    .append(nrOfGuests).append("', '")
                                    .append(hallEvent.getIdHall()).append("', '")
                                    .append(events.get(events.size() - 1).getTOTAL_PRICE()).append("')").toString();

                            super.getConnection().setAutoCommit(false);
                            statement.executeUpdate(sql);
                            super.getConnection().commit();

                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                    System.out.println();
                }
                case "2" -> {
                    String hall;
                    boolean checkHall = false;

                    if (events.isEmpty()) {
                        System.out.println("There is no event to delete!");
                    } else {
                        System.out.println("--Used halls--");
                        List<Hall> differences = new ArrayList<>(halls);
                        differences.removeAll(hallsAux);
                        for (Hall i : differences) {
                            System.out.println(i);
                        }

                        while (!checkHall) {
                            System.out.print("Enter naming of hall: ");
                            hall = s.next();

                            for (Event i : events) {
                                if (i.getHallEvent().getNaming().equals(hall)) {
                                    Statement statement;
                                    try {
                                        statement = super.getConnection().createStatement();
                                        String sql = new StringBuilder()
                                                .append("DELETE FROM Events WHERE IdHall = ")
                                                .append(i.getHallEvent().getIdHall()).toString();

                                        super.getConnection().setAutoCommit(false);
                                        statement.executeUpdate(sql);
                                        super.getConnection().commit();

                                    } catch (SQLException throwables) {
                                        throwables.printStackTrace();
                                    }

                                    hallsAux.add(i.getHallEvent());
                                    events.remove(i);
                                    checkHall = true;
                                    break;
                                }
                            }
                            if (!checkHall)
                                System.out.println("There is no took hall with this naming! Try again");
                        }
                    }
                    System.out.println();
                }
                case "3" -> {
                    String country;
                    boolean checkCountry = false;

                    System.out.print("Enter a country: ");
                    country = s.next();

                    System.out.println("\n--Founded halls--");
                    for (Hall i : halls) {
                        if (country.equals(i.getAdress().getCOUNTRY())) {
                            System.out.println(i.toString());
                            checkCountry = true;
                        }
                    }
                    if (!checkCountry)
                        System.out.println("There is no hall with the country entered from keyboard!\n");
                }
                case "4" -> {
                    ArrayList<Event> sortedEvents = new ArrayList<>(events);

                    Collections.copy(sortedEvents, events);

                    if (events.isEmpty()) {
                        System.out.println("There is no events!\n");
                    } else {
                        System.out.println("--Events in calendar order--");
                        for (int i = 0; i < sortedEvents.size() - 1; i++) {
                            for (int j = i + 1; j < sortedEvents.size(); j++) {
                                if (sortedEvents.get(i).getDateEvent().toString().compareTo(sortedEvents.get(j).getDateEvent().toString()) > 0) {
                                    Collections.swap(sortedEvents, i, j);
                                }
                            }
                        }
                        for (Event i : sortedEvents) {
                            System.out.println(i.toString());
                        }
                    }
                }
                case "5" -> {
                    boolean checkHall = false;

                    System.out.println("--Founded halls--");
                    for (Hall i : halls) {
                        if (i.getNumGuests() > 25 && i.getPricePerPerson() <= 150) {
                            System.out.println(i.toString());
                            checkHall = true;
                        }
                    }
                    if (!checkHall)
                        System.out.println("There is no hall with such characteristic!\n");
                }
                case "6" -> {
                    String hall;
                    boolean checkHall = false;

                    String year = null;
                    String month = null;
                    String day = null;
                    boolean checkDate = false;

                    List<Hall> differences = new ArrayList<>(halls);
                    differences.removeAll(hallsAux);
                    System.out.println("--Used halls in events--");
                    for (Hall i : differences) {
                        System.out.println(i);
                    }

                    while (!checkHall) {
                        while (!checkDate) {
                            System.out.print("Enter your're new date (y/m/d): ");
                            year = s.next();
                            month = s.next();
                            day = s.next();

                            if (InsertException.setDateException(year, month, day) &&
                                    InsertException.setNumberException(year) &&
                                    InsertException.setNumberException(month) &&
                                    InsertException.setNumberException(day)) {
                                checkDate = true;
                            } else System.out.println("Invalid date! Try again");
                        }

                        System.out.print("Enter naming of hall: ");
                        hall = s.next();

                        for (Event i : events) {
                            if (i.getHallEvent().getNaming().equals(hall)) {
                                i.setDateEvent(new Date(year, month, day));

                                Statement statement;
                                try {
                                    statement = super.getConnection().createStatement();
                                    String sql = new StringBuilder()
                                            .append("UPDATE Events SET DateOfEvent = ")
                                            .append("'").append(year).append("-")
                                            .append(month).append("-")
                                            .append(day).append("'")
                                            .append(" WHERE IdHall = ")
                                            .append(i.getHallEvent().getIdHall()).toString();

                                    super.getConnection().setAutoCommit(false);
                                    statement.executeUpdate(sql);
                                    super.getConnection().commit();

                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                }
                                checkHall = true;
                            }
                        }
                        if (!checkHall)
                            System.out.println("There is no hall with this naming! Try again");
                    }
                    System.out.println();
                }
                case "7" -> {
                    ArrayList<Event> sortedEvents = new ArrayList<>(events);

                    if (events.isEmpty()) {
                        System.out.println("There is no events!");
                    } else {
                        System.out.println("--Events sorted by price--");
                        for (int i = 0; i < sortedEvents.size() - 1; i++) {
                            for (int j = i + 1; j < sortedEvents.size(); j++) {
                                if (sortedEvents.get(i).getTOTAL_PRICE() > sortedEvents.get(j).getTOTAL_PRICE()) {
                                    Collections.swap(sortedEvents, i, j);
                                }
                            }
                        }
                        for (Event i : sortedEvents) {
                            System.out.println(i.toString());
                        }
                    }
                }
                case "8" -> {
                    String showEvent = null;
                    boolean checkEvent = false;

                    if (events.isEmpty()) {
                        System.out.println("There is no events!\n");
                    } else {
                        while (!checkEvent) {
                            System.out.print("Enter type event (1 - weddings || 2 - splice): ");
                            showEvent = s.next();

                            if (showEvent.equals("1")) {
                                showEvent = "WEDDING";
                                checkEvent = true;
                            } else if (showEvent.equals("2")) {
                                showEvent = "SPLICE";
                                checkEvent = true;
                            } else System.out.println("Wrong input! Try again");
                        }

                        System.out.println("\n--Founded events--");
                        for (Event i : events) {
                            if (showEvent.equals(String.valueOf(i.getTypeEvent()))) {
                                System.out.println(i.toString());
                            }
                        }
                    }
                }
                case "9" -> {
                    if (hallsAux.isEmpty()) {
                        System.out.println("All halls are busy!\n");
                    } else {
                        System.out.println("--All halls--");
                        for (Hall i : hallsAux) {
                            System.out.println(i.toString());
                        }
                    }
                }
                case "10" -> {
                    if (events.isEmpty()) {
                        System.out.println("There is no events!\n");
                    } else {
                        System.out.println("--All events--");
                        for (Event i : events) {
                            System.out.println(i.toString());
                        }
                    }
                }
                default -> System.out.println("Wrong input! Try again\n");
            }
        }
    }

    /**
     * Gets the halls from the .csv file and inserts them to the local array.
     */
    public void getHallsFromCSV() {
        File myFile = new File("Halls.csv");
        Scanner read = null;
        try {
            read = new Scanner(myFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String lineText;

        assert read != null;
        read.nextLine();

        //Reading from csv and insert to array
        while(read.hasNext()) {
            lineText = read.nextLine();
            String[] data = lineText.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

            Address adr = new Address(data[2], data[3], data[4]);
            halls.add(new Hall(Integer.parseInt(data[0]), data[5], data[1], adr, data[6]));
        }
    }

    /**
     * Adds the data to ArrayList of events.
     */
    public void createEvent() {
        int numberOfGuests;
        Hall hallEvent = new Hall();

        hallsAux = new ArrayList<>(halls);
        List<Person> guests = new ArrayList<>();
        List<Person> grooms = new ArrayList<>();

        Date dateEvent;
        TypeEvent typeEvent;
        int randTypeEvent;

        Random rand = new Random();

        File  f = new File("Persons.txt");
        Scanner s;

        for(int i = 0; i < 15; i++) {
            numberOfGuests = (int) (Math.random() * (50 - 2)) + 2;

            boolean checkNumberOfGuests = false;

            while (!checkNumberOfGuests) {
                for (Hall j : hallsAux) {
                    if (j.getNumGuests() >= numberOfGuests) {
                        hallEvent = j;
                        hallsAux.remove(j);
                        checkNumberOfGuests = true;
                        break;
                    }
                }
                if (!checkNumberOfGuests) {
                    numberOfGuests -= 5;
                }
            }

            try {
                s = new Scanner(f);

                for (int k = 0; k < numberOfGuests; k++) {
                    guests.add(new Person(s.next(), s.next()));
                }
                if(grooms.size() != 2)
                    grooms.add(new Person(s.next(), s.next()));

            } catch (FileNotFoundException e) {
                System.err.println("Oops, file not found!");
                e.printStackTrace();
            }

            dateEvent = new Date(String.valueOf((int) (Math.random() * (2030 - 2021)) + 2021),
                    String.valueOf((int) (Math.random() * (13 - 1)) + 1),
                    String.valueOf((int) (Math.random() * (31 - 1)) + 1));

            randTypeEvent = rand.nextInt(2);
            if (randTypeEvent == 0) {
                typeEvent = TypeEvent.WEDDING;
            } else typeEvent = TypeEvent.SPLICE;

            events.add(new Event(String.valueOf(numberOfGuests), hallEvent, guests, grooms, dateEvent, typeEvent));
        }
    }

    /**
     * Inserts to Events.csv all the data from ArrayList events.
     */
    public void insertEventsInCSV() {
        FileWriter w = null;

        try {
            w = new FileWriter("Events.csv", false);

            w.write("TypeEvent,Year,Month,Day,NumOfPerson,IdHall,Price,,,,,\n");
            for (Event i: events) {
                w.write(i.getTypeEvent() + ",");
                w.write(i.getDateEvent().getYEAR() + "," + i.getDateEvent().getMONTH() + "," + i.getDateEvent().getDAY() + "," );
                w.write(i.getNumberOfGuests() + ",");
                w.write(i.getHallEvent().getIdHall() + ",");
                w.write(i.getTOTAL_PRICE() + ",,,,,\n");
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            try {
                w.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }
}
