package pl.equinix.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import pl.equinix.visitor.VisitorRepository;
import pl.equinix.visitor.VisitorUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * This is argument provider for {@link pl.equinix.visitor.VisitorAlgorithm} class testing
 *
 * @author Tobiasz Piwowarczyk
 * @see pl.equinix.visitor.VisitorAlgorithm
 * @see pl.equinix.visitor.VisitorAlgorithmTest
 */
public class VisitingAlgorithmInputProvider implements ArgumentsProvider {

    private static final String VISITING_TIMES_TEST_FILE_NAME = "visiting-times-test.txt";
    private static final String VISITING_TIMES_ANSWERS_FILE_NAME = "visiting-times-answers.txt";



    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        ArrayList<Arguments> res = new ArrayList<>();

        ArrayList<String> inputs = new ArrayList<>();
        ArrayList<String> outputs = new ArrayList<>();

        URL resource = getClass().getClassLoader().getResource(VISITING_TIMES_TEST_FILE_NAME);
        URL resource1 = getClass().getClassLoader().getResource(VISITING_TIMES_ANSWERS_FILE_NAME);

        if(resource != null && resource1 != null) {
            BufferedReader reader = new BufferedReader(new FileReader(resource.getFile()));
            BufferedReader reader1 = new BufferedReader(new FileReader(resource1.getFile()));

            String line;
            while((line = reader.readLine()) != null)
                inputs.add(line);

            while((line = reader1.readLine()) != null)
                outputs.add(line);

            reader.close();
            reader1.close();


            for(int i = 0; i < inputs.size(); i++) {
                VisitorRepository repository = new VisitorRepository();
                for(int j = 0; j <= i; j++)
                    repository.save(VisitorUtils.toVisitor(inputs.get(j)));

                res.add(Arguments.of(repository, outputs.get(i)));
            }

        }


        return res.stream();
    }
}
