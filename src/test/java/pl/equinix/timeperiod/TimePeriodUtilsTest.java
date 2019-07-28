package pl.equinix.timeperiod;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import pl.equinix.provider.VisitingTimesTestArgumentsProvider;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing class for {@link TimePeriodUtils} class
 *
 * @author Tobiasz Piwowarczyk
 * @see TimePeriodUtils
 */
@DisplayName("TimePeriodUtils class testing")
public class TimePeriodUtilsTest {

    /**
     * Testing {@link TimePeriodUtils#toTimePeriod(String)} method
     *
     * @param timePeriod unprocessed comma separated times
     * @see TimePeriodUtils#toTimePeriod(String)
     */
    @ParameterizedTest(name = "toTimePeriod() method testing. Should create TimePeriod object with values \"{0}\"")
    @ArgumentsSource(VisitingTimesTestArgumentsProvider.class)
    void toTimePeriodTest_shouldCreateTimePeriodObject(String timePeriod) {
        TimePeriod period = TimePeriodUtils.toTimePeriod(timePeriod);
        String[] times = timePeriod.split(",");

        assertNotNull(period);
        assertNotNull(period.getBeginTime());
        assertNotNull(period.getEndTime());
        assertEquals(times[0], period.getBeginTime().toString());
        assertEquals(times[1], period.getEndTime().toString());
    }



    /**
     * Testing {@link TimePeriodUtils#getCommon(TimePeriod, TimePeriod)} method
     *
     * @param t1       first time period
     * @param t2       second time period
     * @param expected expected time period
     * @see TimePeriodUtils#getCommon(TimePeriod, TimePeriod)
     */
    @ParameterizedTest(name = "getCommon() method testing. For input \"{0}\" and \"{1}\" the output should be \"{2}\"")
    @CsvSource(value = {
            "10:00,13:00 11:00,14:00 11:00,13:00",
            "09:00,12:00 11:00,11:30 11:00,11:30",
            "09:00,12:00 12:00,14:30 12:00,12:00"
    }, delimiter = ' ')
    void getCommonTest_shouldReturnTimePeriodWithCommonPeriod(String t1, String t2, String expected) {
        TimePeriod common = TimePeriodUtils.getCommon(
                TimePeriodUtils.toTimePeriod(t1),
                TimePeriodUtils.toTimePeriod(t2)
        );

        assertNotNull(common);
        assertNotNull(common.getBeginTime());
        assertNotNull(common.getEndTime());
        assertEquals(expected, common.toString());
    }



    /**
     * Testing {@link pl.equinix.timeperiod.TimePeriodUtils#isCommon(TimePeriod, TimePeriod)} method
     *
     * @param t1 first time period
     * @param t2 second time period
     * @see pl.equinix.timeperiod.TimePeriodUtils#isCommon(TimePeriod, TimePeriod)
     */
    @ParameterizedTest(name = "getCommon() method testing. For input \"{0}\" and \"{1}\" the output should be \"null\"")
    @CsvSource(value = {
            "11:00,12:00 14:00,16:00",
            "10:00,11:00 13:00,15:00",
            "13:00,13:30 14:00,14:30"
    }, delimiter = ' ')
    void getCommonTest_shouldReturnNull(String t1, String t2) {
        TimePeriod common = TimePeriodUtils.getCommon(
                TimePeriodUtils.toTimePeriod(t1),
                TimePeriodUtils.toTimePeriod(t2)
        );

        assertNull(common);
    }



    /**
     * Testing {@link pl.equinix.timeperiod.TimePeriodUtils#isCommon(TimePeriod, TimePeriod)} method
     *
     * @param t1 first time period
     * @param t2 second time period
     * @see pl.equinix.timeperiod.TimePeriodUtils#isCommon(TimePeriod, TimePeriod)
     */
    @ParameterizedTest(name = "isCommon() method testing. For input \"{0}\" and \"{1}\" the output should be \"true\"")
    @CsvSource(value = {
            "10:00,13:00 11:00,14:00",
            "09:00,12:00 11:00,11:30",
            "09:00,12:00 12:00,14:30"
    }, delimiter = ' ')
    void isCommonTest_shouldReturnTrue(String t1, String t2) {
        assertTrue(
                TimePeriodUtils.isCommon(
                        TimePeriodUtils.toTimePeriod(t1),
                        TimePeriodUtils.toTimePeriod(t2)
                )
        );
    }



    /**
     * Testing {@link pl.equinix.timeperiod.TimePeriodUtils#isCommon(TimePeriod, TimePeriod)} method
     *
     * @param t1 first time period
     * @param t2 second time period
     * @see pl.equinix.timeperiod.TimePeriodUtils#isCommon(TimePeriod, TimePeriod)
     */
    @ParameterizedTest(name = "isCommon() method testing. For input \"{0}\" and \"{1}\" the output should be \"false\"")
    @CsvSource(value = {
            "11:00,12:00 14:00,16:00",
            "10:00,11:00 13:00,15:00",
            "13:00,13:30 14:00,14:30"
    }, delimiter = ' ')
    void isCommonTest_shouldReturnFalse(String t1, String t2) {
        assertFalse(
                TimePeriodUtils.isCommon(
                        TimePeriodUtils.toTimePeriod(t1),
                        TimePeriodUtils.toTimePeriod(t2)
                )
        );
    }
}