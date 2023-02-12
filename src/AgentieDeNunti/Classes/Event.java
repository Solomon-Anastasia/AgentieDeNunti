package AgentieDeNunti.Classes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Class used to store all the information about the Event.
 */
public class Event {
    private final int numberOfGuests;
    private final Hall hallEvent;
    private final List<Person> guests;
    private final List<Person> grooms;
    private Date dateEvent;
    private final TypeEvent typeEvent;
    private final double TOTAL_PRICE;


    /**
     * Constructor for Event object
     * @param NUMBER_OF_GUESTS number of guests for event.
     * @param hallEvent the hall for event.
     * @param guests the list of guests.
     * @param grooms the list of grooms.
     * @param dateEvent the date of event.
     * @param typeEvent the type of event.
     */
    public Event(String NUMBER_OF_GUESTS, Hall hallEvent, List<Person> guests, List<Person> grooms, Date dateEvent, TypeEvent typeEvent) {
        this.numberOfGuests = Integer.parseInt(NUMBER_OF_GUESTS);
        this.hallEvent = hallEvent;
        this.guests = guests;
        this.grooms = grooms;
        this.dateEvent = dateEvent;
        this.typeEvent = typeEvent;
        this.TOTAL_PRICE = truncation(hallEvent.getPricePerPerson() * Integer.parseInt(NUMBER_OF_GUESTS) * 1.15);
    }

    /**
     * Getter of number of guests.
     * @return number of guests of event.
     */
    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    /**
     * Getter of hall.
     * @return hall of event.
     */
    public Hall getHallEvent() {
        return hallEvent;
    }

    /**
     * Getter of date.
     * @return date of event.
     */
    public Date getDateEvent() {
        return dateEvent;
    }

    /**
     * Getter of type.
     * @return type of event.
     */
    public TypeEvent getTypeEvent() {
        return typeEvent;
    }

    /**
     * Getter of number of guests.
     * @return number of guests of event.
     */
    public double getTOTAL_PRICE() {
        return TOTAL_PRICE;
    }

    /**
     * Setter for the date of the event (used for 6th exercises)
     * @param dateEvent date to be used.
     */
    public void setDateEvent(Date dateEvent) {
        this.dateEvent = dateEvent;
    }

    /**
     * @return Object event to string.
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Number of guests: ").append(getNumberOfGuests()).append("\n");
        s.append(getDateEvent());
        s.append("Type event: ").append(getTypeEvent()).append("\n");
        s.append("Total price for event: ").append(getTOTAL_PRICE()).append(" $\n");
        return s.toString();
    }

    /**
     * Makes double with only 2 decimals.
     * @param input number to be trucated
     * @return truncated number (2.22)
     */
    public double truncation(double input) {
        BigDecimal bd = new BigDecimal(input).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
