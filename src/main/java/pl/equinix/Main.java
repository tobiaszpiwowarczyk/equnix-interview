package pl.equinix;

import pl.equinix.visitor.VisitorAlgorithm;
import pl.equinix.visitor.VisitorFileItemReader;
import pl.equinix.visitor.VisitorRepository;

/**
 * Main class
 *
 * @author Tobiasz Piwoarczyk
 */
public class Main {

    /**
     * Test file name.
     * IMPORTANT! This file must be placed into {@code resources} folder
     */
    private static final String VISITING_TIMES_FILE_NAME = "visiting-times.txt";



    public static void main(String[] args) {
        VisitorRepository repository = new VisitorRepository();
        VisitorAlgorithm algorithm = new VisitorAlgorithm(repository);
        VisitorFileItemReader reader = new VisitorFileItemReader(repository);


        try {
            reader.readFromFile(VISITING_TIMES_FILE_NAME);
            System.out.println(algorithm.getMostVisitingTimePeriod());
        } catch(Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

    }
}
