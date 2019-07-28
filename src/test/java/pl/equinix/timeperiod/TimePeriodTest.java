package pl.equinix.timeperiod;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import pl.equinix.provider.VisitingTimesTestArgumentsProvider;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing class for {@link pl.equinix.timeperiod.TimePeriod} class
 *
 * @author Tobiasz Piwowarczyk
 * @see pl.equinix.timeperiod.TimePeriod
 */
@DisplayName("TimePeriod class testing")
public class TimePeriodTest {

    /**
     * Testing {@link pl.equinix.timeperiod.TimePeriod} object creation
     *
     * @param timePeriod unprocessed comma separated times
     * @see pl.equinix.timeperiod.TimePeriod
     */
    @ParameterizedTest(name = "Object creation. Should create TimePeriod object with value \"{0}\"")
    @ArgumentsSource(VisitingTimesTestArgumentsProvider.class)
    void createTimePeriodTest_shouldCreateTimePeriodObject(String timePeriod) {
        String[] times = timePeriod.split(",");
        TimePeriod period = new TimePeriod(LocalTime.parse(times[0]), LocalTime.parse(times[1]));

        assertNotNull(period);
        assertNotNull(period.getBeginTime());
        assertNotNull(period.getEndTime());
        assertEquals(times[0], period.getBeginTime().toString());
        assertEquals(times[1], period.getEndTime().toString());
    }



    /**
     * Testing {@link pl.equinix.timeperiod.TimePeriod#toString()} method
     *
     * @param timePeriod unprocessed comma separated times
     * @see pl.equinix.timeperiod.TimePeriod#toString()
     */
    @ParameterizedTest(name = "toString() method testing. Should return TimePeriod object with value \"{0}\" as string")
    @ArgumentsSource(VisitingTimesTestArgumentsProvider.class)
    void toStringTest_shouldReturnTimePeriodAsString(String timePeriod) {
        String[] times = timePeriod.split(",");
        TimePeriod period = new TimePeriod(LocalTime.parse(times[0]), LocalTime.parse(times[1]));

        assertEquals(timePeriod, period.toString());
    }



    /**
     * Testing {@link pl.equinix.timeperiod.TimePeriod#equals(Object)} method
     *
     * @param t1 first time period
     * @param t2 second time period
     * @see pl.equinix.timeperiod.TimePeriod#equals(Object)
     */
    @ParameterizedTest(
            name = "equals() method testing. Should return \'true\' for time period 1 = \"{0}\" and time period2 = \"{1}\""
    )
    @CsvSource(value = {
            "10:00,12:00 10:00,12:00",
            "12:00,14:00 12:00,14:00",
            "10:34,11:40 10:34,11:40",
            "15:00,18:00 15:00,18:00",
    }, delimiter = ' ')
    void equalsTest_shouldReturnTrue(String t1, String t2) {
        String[] times1 = t1.split(",");
        String[] times2 = t2.split(",");

        TimePeriod period1 = new TimePeriod(LocalTime.parse(times1[0]), LocalTime.parse(times1[1]));
        TimePeriod period2 = new TimePeriod(LocalTime.parse(times2[0]), LocalTime.parse(times2[1]));

        assertEquals(period1, period2);
        assertEquals(period2, period1);
    }



    @ParameterizedTest(
            name = "equals() method testing. Should return \'false\' for time period 1 = \"{0}\" and time period2 = \"{1}\""
    )
    @CsvSource(value = {
            "10:00,12:00 11:00,12:00",
            "12:03,14:00 13:00,15:00",
            "10:34,11:40 10:14,11:20",
            "15:37,18:00 15:00,16:00",
    }, delimiter = ' ')
    void equalsTest_shouldReturnFalse(String t1, String t2) {
        String[] times1 = t1.split(",");
        String[] times2 = t2.split(",");

        TimePeriod period1 = new TimePeriod(LocalTime.parse(times1[0]), LocalTime.parse(times1[1]));
        TimePeriod period2 = new TimePeriod(LocalTime.parse(times2[0]), LocalTime.parse(times2[1]));

        assertNotEquals(period1, period2);
        assertNotEquals(period2, period1);
    }
}