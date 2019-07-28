package pl.equinix.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Class for providing data for {@link pl.equinix.timeperiod.TimePeriod} and {@link pl.equinix.timeperiod.TimePeriodUtils} class testing
 *
 * @author Tobiasz Piwowarczyk
 * @see pl.equinix.timeperiod.TimePeriod
 * @see pl.equinix.timeperiod.TimePeriodTest
 * @see pl.equinix.timeperiod.TimePeriodUtils
 * @see pl.equinix.timeperiod.TimePeriodUtilsTest
 */
public class VisitingTimesTestArgumentsProvider implements ArgumentsProvider {

    private static final String VISITING_TIMES_TEST_FILE_NAME = "visiting-times-test.txt";



    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        ArrayList<Arguments> res = new ArrayList<>();

        URL resource = getClass().getClassLoader().getResource(VISITING_TIMES_TEST_FILE_NAME);
        if(resource != null) {
            BufferedReader reader = new BufferedReader(new FileReader(resource.getFile()));

            String line;
            while((line = reader.readLine()) != null)
                res.add(Arguments.of(line));

            reader.close();
        }

        return res.stream();
    }
}
