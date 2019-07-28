package pl.equinix.localtime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testing class for {@link pl.equinix.localtime.LocalTimeUtils} class
 *
 * @author Tobiasz Piwowarczyk
 * @see pl.equinix.localtime.LocalTimeUtils
 */
@DisplayName("LocalTimeUtils class testing")
class LocalTimeUtilsTest {

    /**
     * Testing {@link pl.equinix.localtime.LocalTimeUtils#isBetween(LocalTime, LocalTime, LocalTime)} method
     *
     * @param current - current time
     * @param start   - start time
     * @param end     - end time
     * @see pl.equinix.localtime.LocalTimeUtils#isBetween(LocalTime, LocalTime, LocalTime)
     */
    @ParameterizedTest(
            name = "isBetween() method testing. For currentTime=\"{0}\", start=\"{1}\" and end=\"{2}\" should return \"false\""
    )
    @CsvSource(value = {
            "10:00 09:00 11:00",
            "12:00 09:00 17:00",
            "10:59 09:00 11:00",
            "13:00 09:00 14:00",
    }, delimiter = ' ')
    void isBetweenTest_shouldReturnTrue(String current, String start, String end) {
        assertTrue(
                LocalTimeUtils.isBetween(
                        LocalTime.parse(current),
                        LocalTime.parse(start),
                        LocalTime.parse(end)
                )
        );
    }



    /**
     * Testing {@link pl.equinix.localtime.LocalTimeUtils#isBetween(LocalTime, LocalTime, LocalTime)} method
     *
     * @param current - current time
     * @param start   - start time
     * @param end     - end time
     * @see pl.equinix.localtime.LocalTimeUtils#isBetween(LocalTime, LocalTime, LocalTime)
     */
    @ParameterizedTest(
            name = "isBetween() method testing. For currentTime=\"{0}\", start=\"{1}\" and end=\"{2}\" should return \"true\""
    )
    @CsvSource(value = {
            "12:00 09:00 11:00",
            "09:00 09:00 17:00",
            "08:59 09:00 11:00",
            "15:00 09:00 14:00",
    }, delimiter = ' ')
    void isBetweenTest_shouldReturnFalse(String current, String start, String end) {
        assertFalse(
                LocalTimeUtils.isBetween(
                        LocalTime.parse(current),
                        LocalTime.parse(start),
                        LocalTime.parse(end)
                )
        );
    }
}