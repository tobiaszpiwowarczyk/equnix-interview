package pl.equinix.visitor;

import pl.equinix.timeperiod.TimePeriodUtils;

/**
 * This is the class for basic operations on {@link pl.equinix.visitor.Visitor} class
 *
 * @author Tobiasz Piwowarczyk
 * @see pl.equinix.visitor.Visitor
 */
public class VisitorUtils {

    /**
     * Method that converts unprocessed string to {@link pl.equinix.visitor.Visitor} object
     * The {@code value} must be in specific format, for example:
     * <ul>
     * <li>{@code 10:00,13:00}</li>
     * <li>{@code 09:40,13:50}</li>
     * </ul>
     *
     * @param value unprocessed comma separated localtime
     * @return {@link pl.equinix.visitor.Visitor} object
     * @see pl.equinix.timeperiod.TimePeriodUtils
     */
    public static Visitor toVisitor(String value) {
        return new Visitor(TimePeriodUtils.toTimePeriod(value));
    }
}
