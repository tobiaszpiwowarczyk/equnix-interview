package pl.equinix.visitor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing class for {@link VisitorFileItemReader} class
 *
 * @author Tobiasz Piwowarczyk
 * @see VisitorFileItemReader
 */
@DisplayName("VisitorFileItemReader class testing")
class VisitorFileItemReaderTest {

    private static final int ITEMS_LENGTH = 239;
    private static final String CORRECT_FILE_NAME = "visiting-times.txt";
    private static final String INCORRECT_FILE_NAME = "visiting-times1.txt";

    private VisitorRepository repository;
    private VisitorFileItemReader visitorFileItemReader;



    @BeforeEach
    void setUp() {
        repository = new VisitorRepository();
        visitorFileItemReader = new VisitorFileItemReader(repository);
    }



    /**
     * Testing {@link VisitorFileItemReader#readFromFile(String)} method.
     *
     * @throws Exception when file was not found
     * @see VisitorFileItemReader#readFromFile(String)
     */
    @DisplayName("readFromFile() method testing. Should read file and save results to repository")
    @Test
    void readFromFileTest_shouldReadFileAndSaveResultsToVisitorRepository() throws Exception {
        visitorFileItemReader.readFromFile(CORRECT_FILE_NAME);

        assertEquals(ITEMS_LENGTH, repository.count());
        repository.findAll().forEach(value -> {
            assertNotNull(value);
            assertNotNull(value.getPeriod());
            assertNotNull(value.getPeriod().getBeginTime());
            assertNotNull(value.getPeriod().getEndTime());
        });
    }



    /**
     * Testing {@link VisitorFileItemReader#readFromFile(String)} method. This method should throw an exception,
     * because file does not exist in {@code resources} folder
     *
     * @see VisitorFileItemReader#readFromFile(String)
     */
    @DisplayName("readFromFile() method testing. Should throw IllegalArgumentException with message")
    @Test
    void readFromFileTest_ShouldThrowIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> visitorFileItemReader.readFromFile(INCORRECT_FILE_NAME));

        assertEquals("Could not open this file: \"" + INCORRECT_FILE_NAME + "\"", exception.getLocalizedMessage());
    }
}