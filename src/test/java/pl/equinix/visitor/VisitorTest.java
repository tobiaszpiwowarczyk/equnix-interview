package pl.equinix.visitor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.equinix.timeperiod.TimePeriod;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link Visitor} class
 *
 * @author Tobiasz Piwowarczyk
 * @see Visitor
 */
@DisplayName("Visitor class testing")
class VisitorTest {

    /**
     * Method creation test
     *
     * @param beginTime - begin time
     * @param endTime - end time
     */
    @ParameterizedTest(name = "Should create Visitor object with values [{0},{1}]")
    @CsvSource({
            "10:00,12:00",
            "09:00,13:00",
            "14:00,18:00",
            "17:12,17:13"
    })
    void createVisitor_shouldCreateVisitorWithValues(String beginTime, String endTime) {
        Visitor visitor = new Visitor(new TimePeriod(LocalTime.parse(beginTime), LocalTime.parse(endTime)));

        assertNotNull(visitor);
        assertNotNull(visitor.getPeriod());
        assertEquals(beginTime, visitor.getPeriod().getBeginTime().toString());
        assertEquals(endTime, visitor.getPeriod().getEndTime().toString());
    }



    /**
     * Testing <code>getPeriod()</code> method
     *
     * @see Visitor#getPeriod()
     */
    @DisplayName("getPeriod() method testing. Should return 'period' field value")
    @Test
    void getPeriodTest_shouldReturnPeriodValue() {
        Visitor visitor = new Visitor(new TimePeriod(LocalTime.parse("10:00"), LocalTime.parse("12:00")));

        assertEquals(visitor.getPeriod().toString(), new TimePeriod(LocalTime.parse("10:00"), LocalTime.parse("12:00")).toString());
    }



    /**
     * Testing {@code equals()} method
     *
     * @see Visitor#equals(Object)
     */
    @ParameterizedTest(name = "equals() method testing. Should return \"{4}\" for values [{0}, {1}, {2}, {3}]")
    @CsvSource({
            "10:00,12:00,10:00,12:00,true",
            "10:13,12:00,11:00,13:00,false"
    })
    void equalsTest_shouldReturnTrueOrFalse(String t1, String t2, String t3, String t4, boolean expected) {
        Visitor v1 = new Visitor(new TimePeriod(LocalTime.parse(t1), LocalTime.parse(t2)));
        Visitor v2 = new Visitor(new TimePeriod(LocalTime.parse(t3), LocalTime.parse(t4)));

        assertEquals(expected, v1.equals(v2));
        assertEquals(expected, v2.equals(v1));
    }
}