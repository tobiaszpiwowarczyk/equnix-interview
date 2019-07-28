package pl.equinix.visitor;

import pl.equinix.timeperiod.TimePeriod;

import java.util.Objects;

/**
 * Simple Visitor class
 *
 * @author Tobiasz Piwowarczyk
 */
public class Visitor {

    /**
     * This field keeps value of time period of visitor
     */
    private final TimePeriod period;



    public Visitor(TimePeriod period) {
        this.period = period;
    }



    /**
     * Returns value of {@code period} field
     *
     * @return value of {@code period} field
     */
    public TimePeriod getPeriod() {
        return period;
    }



    /**
     * This method checks if this object is equal to {@code o} argument
     *
     * @param o any object
     * @return {@code true} if objects are equal; {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Visitor visitor = (Visitor) o;
        return Objects.equals(period, visitor.period);
    }



    /**
     * This method returns all values of object as string
     *
     * @return all values of object as string
     */
    @Override
    public String toString() {
        return "Visitor{" +
                "period=" + period +
                '}';
    }
}
