package pl.equinix.localtime;

import java.time.LocalTime;

/**
 * This is the class for basic operations on {@link java.time.LocalTime} class
 *
 * @author Tobiasz Piwowarczyk
 */
public class LocalTimeUtils {

    /**
     * This method checks if {@code current} localtime is between {@code first} and {@code end} localtime
     *
     * @param current current localtime
     * @param start   start localtime
     * @param end     end localtime
     * @return {@code true} if current localtime is between {@code start} and {@code end}; {@code false} otherwise
     */
    public static boolean isBetween(LocalTime current, LocalTime start, LocalTime end) {
        return current.isAfter(start) && current.isBefore(end);
    }

}
