package AgentieDeNunti.Classes;

/**
 * This class will be used for a structure of the address
 * of the banquet hall
 */
public class Address {
    private final String COUNTRY;
    private final String CITY;
    private final String STREET;

    /**
     * This is the constructor of the Address object.
     * @param country The country of the hall.
     * @param city The city of the hall.
     * @param street The street of the hall.
     */
    public Address(String country, String city, String street) {
        this.COUNTRY = country;
        this.CITY = city;
        this.STREET = street;
    }

    /**
     * Getter for country.
     * @return country of hall.
     */
    public String getCOUNTRY() {
        return COUNTRY;
    }

    /**
     * Getter for city.
     * @return city of hall.
     */
    public String getCITY() {
        return CITY;
    }

    /**
     * Getter for street.
     * @return street of hall.
     */
    public String getSTREET() {
        return STREET;
    }

    /**
     * @return object Address to string.
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder()
                .append("Adress: ").append(getCOUNTRY()).append(", ")
                .append(getCITY()).append(", ")
                .append(getSTREET()).append("\n");
        return s.toString();
    }
}
