package pl.equinix.timeperiod;

import pl.equinix.localtime.LocalTimeUtils;

import java.time.LocalTime;

/**
 * This is the class for basic operation on {@link pl.equinix.timeperiod.TimePeriod} class
 *
 * @author Tobiasz Piwowarczyk
 * @see pl.equinix.timeperiod.TimePeriod
 */
public class TimePeriodUtils {

    /**
     * Method that converts unprocessed string to {@link pl.equinix.timeperiod.TimePeriod} class.
     * The {@code value} must be in specific format, for example:
     * <ul>
     * <li>{@code 10:00,13:00}</li>
     * <li>{@code 09:40,13:50}</li>
     * </ul>
     *
     * @param value unprocessed comma separated localtime
     * @return {@link pl.equinix.timeperiod.TimePeriod} object
     * @see pl.equinix.timeperiod.TimePeriod
     */
    public static TimePeriod toTimePeriod(String value) {
        String[] times = value.split(",");
        return new TimePeriod(LocalTime.parse(times[0]), LocalTime.parse(times[1]));
    }



    /**
     * Method that return common localtime period of {@code first} and {@code second} localtime period.
     * Examples:
     * <ol>
     * <li>
     * For localtime periods
     * <ul>
     * <li>first:  {@code 10:00,13:00}</li>
     * <li>second: {@code 11:00,14:00}</li>
     * </ul>
     * The output should be {@link pl.equinix.timeperiod.TimePeriod} object with values: {@code 11:00,13:00}
     * </li>
     * <li>
     * For localtime periods
     * <ul>
     * <li>first:  {@code 09:00,12:00}</li>
     * <li>second: {@code 11:00,11:30}</li>
     * </ul>
     * The output should be {@link pl.equinix.timeperiod.TimePeriod} object with values: {@code 11:00,11:30}
     * </li>
     * <li>
     * For localtime periods
     * <ul>
     * <li>first:  {@code 09:00,12:00}</li>
     * <li>second: {@code 12:00,14:30}</li>
     * </ul>
     * The output should be {@link pl.equinix.timeperiod.TimePeriod} object with values: {@code 12:00,12:00}
     * </li>
     * <li>
     * For localtime periods
     * <ul>
     * <li>first:  {@code 11:00,12:00}</li>
     * <li>second: {@code 14:00,16:00}</li>
     * </ul>
     * The output should be {@code null}, because {@code first} end localtime (12:00) is before {@code second} begin localtime (14:00)
     * </li>
     * </ol>
     *
     * @param first  first localtime period
     * @param second second localtime period
     * @return {@link pl.equinix.timeperiod.TimePeriod} object with common localtime;
     * {@code null} if {@code first} and {@code second} localtime period hasn't common period
     */
    public static TimePeriod getCommon(TimePeriod first, TimePeriod second) {

        if(!isCommon(first, second)) return null;

        LocalTime newCurrent, newNext;
        if(first.getBeginTime().isBefore(second.getBeginTime()))
            newCurrent = second.getBeginTime();
        else
            newCurrent = first.getBeginTime();

        if(first.getEndTime().isBefore(second.getEndTime()))
            newNext = first.getEndTime();
        else
            newNext = second.getEndTime();

        return new TimePeriod(newCurrent, newNext);
    }



    /**
     * Checks that provided two time periods has common period.
     * Examples:
     * <ol>
     * <li>
     * For localtime periods
     * <ul>
     * <li>first:  {@code 10:00,13:00}</li>
     * <li>second: {@code 11:00,14:00}</li>
     * </ul>
     * The output should be {@code true}, because common time period is {@code 11:00,13:00}
     * </li>
     * <li>
     * For localtime periods
     * <ul>
     * <li>first:  {@code 09:00,12:00}</li>
     * <li>second: {@code 11:00,11:30}</li>
     * </ul>
     * The output should be {@code true}, because common time period is {@code 11:00,11:30}
     * </li>
     * <li>
     * For localtime periods
     * <ul>
     * <li>first:  {@code 09:00,12:00}</li>
     * <li>second: {@code 12:00,14:30}</li>
     * </ul>
     * The output should be {@code true}, because common time period is {@code 12:00,12:00}
     * </li>
     * <li>
     * For localtime periods
     * <ul>
     * <li>first:  {@code 11:00 12:00}</li>
     * <li>second: {@code 14:00 16:00}</li>
     * </ul>
     * The output should be {@code false} because {@code first} and {@code second} time period doesn't have any common period
     * </li>
     * </ol>
     *
     * @param first  first time period
     * @param second second time period
     * @return {@code true} if both time periods has common time period;
     * {@code false} otherwise
     * @see pl.equinix.localtime.LocalTimeUtils#isBetween(LocalTime, LocalTime, LocalTime)
     */
    public static boolean isCommon(TimePeriod first, TimePeriod second) {
        if(first.getBeginTime().equals(second.getEndTime()) || first.getEndTime().equals(second.getBeginTime()))
            return true;

        if(LocalTimeUtils.isBetween(second.getBeginTime(), first.getBeginTime(), first.getEndTime()))
            return true;

        if(LocalTimeUtils.isBetween(second.getBeginTime(), second.getBeginTime(), second.getEndTime()))
            return true;

        if(LocalTimeUtils.isBetween(second.getEndTime(), first.getBeginTime(), first.getEndTime()))
            return true;

        return LocalTimeUtils.isBetween(first.getEndTime(), second.getBeginTime(), second.getEndTime());
    }
}
