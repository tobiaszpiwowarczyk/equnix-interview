package pl.equinix.visitor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import pl.equinix.provider.VisitingAlgorithmInputProvider;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Testing class for {@link VisitorAlgorithm} class
 *
 * @author Tobiasz Piwowarczyk
 * @see VisitorAlgorithm
 */
@DisplayName("VisitorAlgorithm class testing")
public class VisitorAlgorithmTest {



    @ParameterizedTest(name = "For input: {0}\n\toutput should be: \"{1}\"")
    @ArgumentsSource(VisitingAlgorithmInputProvider.class)
    void getMostVisitingTimePeriodTest_shouldReturnFormattedString(VisitorRepository repository, String expected) {
        VisitorAlgorithm algorithm = new VisitorAlgorithm(repository);
        assertEquals(expected, algorithm.getMostVisitingTimePeriod());
    }
}