package pl.equinix.visitor;

import org.junit.jupiter.api.*;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test class for {@link VisitorRepository} class
 *
 * @author Tobiasz Piwowarczyk
 * @see VisitorRepository
 */
@DisplayName("VisitorRepository class testing")
class VisitorRepositoryTest {

    private String[] times;
    private int timeIndex;
    private VisitorRepository visitorRepository;



    @BeforeEach
    void setUpBeforeEach() {
        times = new String[]{"10:00,12:00", "11:00,13:00", "13:00,14:00"};
        timeIndex = 0;
        visitorRepository = new VisitorRepository();

        for(String time : times)
            visitorRepository.save(VisitorUtils.toVisitor(time));
    }



    @AfterEach
    void tearDown() {
        timeIndex = 0;
    }



    /**
     * Testing {@link VisitorRepository#findAll()} method
     *
     * @see VisitorRepository#findAll()
     */
    @DisplayName("findAll() method testing. Should return ListStack object")
    @Test
    void findAllTest_shouldReturnStackOfVisitors() {
        LinkedList<Visitor> all = visitorRepository.findAll();

        assertNotNull(all);
        assertEquals(3, all.size());
    }



    /**
     * Testing {@link VisitorRepository#findAll()} method.
     *
     * @see VisitorRepository#findAll()
     */
    @DisplayName("findAll() method testing. The linked list should contain certain values")
    @Test
    void findAllTest_theLinkedListShouldContainObjects() {
        visitorRepository.findAll().forEach(value -> {
            String[] currentTime = times[timeIndex++].split(",");

            assertNotNull(value);
            assertNotNull(value.getPeriod());
            assertNotNull(value.getPeriod().getBeginTime());
            assertNotNull(value.getPeriod().getEndTime());

            assertEquals(currentTime[0], value.getPeriod().getBeginTime().toString());
            assertEquals(currentTime[1], value.getPeriod().getEndTime().toString());
        });
    }



    /**
     * Testing {@link VisitorRepository#save(Visitor)} method
     *
     * @see VisitorRepository#save(Visitor)
     */
    @DisplayName("save() method testing. Should save new Visitor object to repository")
    @Test
    void saveTest_shouldPushNewElementToRepository() {
        visitorRepository.save(VisitorUtils.toVisitor("15:00,16:00"));

        assertEquals(4, visitorRepository.count());
    }



    /**
     * Testing {@link VisitorRepository#count()} method
     *
     * @see VisitorRepository#count()
     */
    @DisplayName("count() method testing. Should return total amount of Visitor objects in repository")
    @Test
    void countTest_shouldReturnAmountOfVisitors() {
        assertEquals(3, visitorRepository.count());
    }
}