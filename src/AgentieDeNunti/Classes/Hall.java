package AgentieDeNunti.Classes;

/**
 * Class used for structure of hall with all it's information.
 */
public class Hall {
    private int idHall;
    private int numGuests;
    private String naming;
    private Address address;
    private int pricePerPerson;


    /**
     * Constructor of object hall without any parameters.
     */
    public Hall() {
    }

    /**
     * Constructor of object hall with all the parameters.
     * @param idHall the id of the hall.
     * @param NUM_GUESTS the number of guests that it can take.
     * @param naming the nameing of the hall.
     * @param address the address of the hall.
     * @param pricePerPerson price per person.
     */
    public Hall(int idHall, String NUM_GUESTS, String naming, Address address, String pricePerPerson) {
        this.idHall = idHall;
        this.numGuests = Integer.parseInt(NUM_GUESTS);
        this.naming = naming;
        this.address = address;
        this.pricePerPerson = Integer.parseInt(pricePerPerson);
    }

    /**
     * Getter for id of hall.
     * @return id of hall.
     */
    public int getIdHall() {
        return idHall;
    }

    /**
     * Getter for number of max guests of hall.
     * @return number of max guests of hall.
     */
    public int getNumGuests() {
        return numGuests;
    }

    /**
     * Getter for naming of hall.
     * @return naming of hall.
     */
    public String getNaming() {
        return naming;
    }

    /**
     * Getter for address of hall.
     * @return address of hall.
     */
    public Address getAdress() {
        return address;
    }

    /**
     * Getter for price per person of hall.
     * @return price per person of hall.
     */
    public int getPricePerPerson() {
        return pricePerPerson;
    }

    /**
     * @return Object hall to string.
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        s.append("Max guests: ").append(getNumGuests()).append("\n");
        s.append("Naming: ").append(getNaming()).append("\n");
        s.append(getAdress().toString());
        s.append("Price for person: ").append(getPricePerPerson()).append(" $").append("\n");

        return s.toString();
    }
}
