package pl.equinix.visitor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test class for {@link VisitorUtils} class
 *
 * @author Tobiasz Piwowarczyk
 * @see VisitorUtils
 */
@DisplayName("VisitorUtils class testing")
class VisitorUtilsTest {

    /**
     * Testing {@link VisitorUtils#toVisitor(String)} method
     * @param input - unprocessed comma separated string with times
     * @see VisitorUtils#toVisitor(String)
     */
    @ParameterizedTest(name = "toVisitor method testing. Should create Visitor object with values [{0}]")
    @CsvSource(value = {
            "10:00,12:00",
            "09:00,13:00",
            "14:00,18:00",
            "17:12,17:13"
    }, delimiter = '\n')
    void toVisitorTest_shouldCreateVisitor(String input) {
        Visitor visitor = VisitorUtils.toVisitor(input);
        String[] times = input.split(",");

        assertNotNull(visitor);
        assertNotNull(visitor.getPeriod());
        assertEquals(times[0], visitor.getPeriod().getBeginTime().toString());
        assertEquals(times[1], visitor.getPeriod().getEndTime().toString());
    }
}