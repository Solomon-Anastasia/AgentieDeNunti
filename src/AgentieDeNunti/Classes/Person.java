package AgentieDeNunti.Classes;

/**
 * Class Person to be used to store information about the persons at the wedding.
 * Used for grooms and guests.
 */
public class Person {
    private final String NAME;
    private final String SURNAME;

    /**
     * Constructor for object Person
     * @param name name of person
     * @param surname surname of person
     */
    public Person(String name, String surname) {
        this.NAME = name;
        this.SURNAME = surname;
    }

    /**
     * Getter for the name of the person.
     * @return name of the perosn.
     */
    public String getNAME() {
        return NAME;
    }

    /**
     * Getter for the surname of the person.
     * @return surname of the person.
     */
    public String getSURNAME() {
        return SURNAME;
    }

    /**
     * @return object Person to string.
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder()
                .append("Name: ").append(getNAME()).append("\n")
                .append("Surname: ").append(getSURNAME()).append("\n\n");
        return s.toString();
    }
}
