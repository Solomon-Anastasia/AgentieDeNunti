package AgentieDeNunti.Classes;

/**
 * This class will be used for a structure of the date
 * of the banquet hall
 */
public class Date {
    private final String YEAR;
    private final String MONTH;
    private final String DAY;


    /**
     * Constructor of Date object.
     * @param year year of date.
     * @param month month of date.
     * @param day day of date.
     */
    public Date(String year, String month, String day) {
        this.YEAR = year;
        this.MONTH = month;
        this.DAY = day;
    }

    /**
     * Getter for year.
     * @return year of date.
     */
    public String getYEAR() {
        return YEAR;
    }

    /**
     * Getter for month.
     * @return month of date.
     */
    public String getMONTH() {
        return MONTH;
    }

    /**
     * Getter for day.
     * @return day of date.
     */
    public String getDAY() {
        return DAY;
    }

    /**
     * @return object Date to string.
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder()
                .append("Date of event (y/m/d): ").append(getYEAR()).append("/")
                .append(getMONTH()).append("/").append(getDAY()).append("\n");
        return s.toString();
    }
}
