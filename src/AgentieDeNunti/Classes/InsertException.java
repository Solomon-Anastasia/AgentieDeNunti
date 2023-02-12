package AgentieDeNunti.Classes;

/**
 * Class that holds the exceptions.
 */
public class InsertException {
    /**
     * Checks if date is correct
     * @param year anul
     * @param month luna
     * @param day ziua
     * @return true if no errors, otherwise false.
     */
    public static boolean setDateException(String year, String month, String day) {
        return (day.matches("[0-9]\\d*") && Integer.parseInt(day) > 0 && Integer.parseInt(day) <= 30)
                && (month.matches("|[0-9]\\d*") && Integer.parseInt(month) > 0 && Integer.parseInt(month) <= 12)
                && (year.matches("[0-9]\\d*") && Integer.parseInt(year) >= 2021 && Integer.parseInt(year) <= 2030);
    }

    /**
     * Check if a String is a number and greater than 0.
     * @param n string number to be checked
     * @return true if is greater, otherwise false.
     */
    public static boolean setNumberException(String n) {
        return n.matches("0|[1-9]\\d*") && Integer.parseInt(n) > 0;
    }
}
