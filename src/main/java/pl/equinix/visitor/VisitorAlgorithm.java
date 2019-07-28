package pl.equinix.visitor;

import pl.equinix.timeperiod.TimePeriod;
import pl.equinix.timeperiod.TimePeriodUtils;

import java.util.LinkedList;

/**
 * Algorithm class implementation for Equinix interview
 *
 * @author Tobiasz Piwowarczyk
 */
public class VisitorAlgorithm {

    /**
     * the repository with all visitors
     */
    private VisitorRepository repository;

    /**
     * The most common time period with biggest amount of visitors
     */
    private TimePeriod period;

    /**
     * total count of visitors in {@link #period}
     */
    private int count;

    /**
     * Temporary time period (this is for algorithm usage)
     * @see #getMostVisitingTimePeriod()
     */
    private TimePeriod tempPeriod;

    /**
     * Temporary count, that holds amount of visitors for each iteration
     * @see #getMostVisitingTimePeriod()
     */
    private int tempCount;



    public VisitorAlgorithm(VisitorRepository repository) {
        this.repository = repository;
        this.count = 1;
        this.tempCount = 1;
    }



    /**
     * This method search common time period with most visitors.
     * How it works? Well:
     * <ol>
     *     <li>This algorithm gets each value from repository.</li>
     *     <li>Saves temporary time period to {@link #tempPeriod} field with the time period value using {@link Visitor#getPeriod()} method</li>
     *     <li>
     *         Let's say, that {@code value} is the the visitor from current loop, and {@code value1} is the visitor value
     *         from nested loop.
     *         So algorithm iterates through repository and check if {@code value} is not equal to {@code value1}
     *     </li>
     *     <li>
     *         Checks, that {@link #tempPeriod} and {@code value1} has common period.
     *         If so, {@code tempCount} value is incremented
     *     </li>
     *     <li>Next, the algorithm get common time period for {@link #tempPeriod} and {@code value1}</li>
     *     <li>
     *         If common time period doesn't have equal time (for example 10:00,10:00),
     *         replace the value of {@link #tempPeriod} with new common time period
     *     </li>
     *     <li>After nested iteration we need to check, if {@link #tempCount} is greater than {@code count}</li>
     *     <li>
     *         If so, replace the current value of {@link #count} with value of {@link #tempCount}.
     *         Also we need to replace value of {@link #period} with value of {@link #tempPeriod}
     *     </li>
     *     <li>After all, restart {@link #tempPeriod} value. Just set the value to {@code 0}</li>
     * </ol>
     *
     * Time complexity of algorithm: {@code O(n^2)}
     * Space complexity of algorithm: {@code O(n)}
     *
     * Examples:
     * 1. Let's say, that we have this time periods in repository:
     * <ul>
     *     <li>{@code 10:15,14:20}</li>
     * </ul>
     *
     * So the output of this method should be {@code 10:15 - 14:20; 1}
     * 1. Let's say, that we have this times periods in repository:
     * <ul>
     *     <li>{@code 10:15,14:20}</li>
     *     <li>{@code 11:10,15:22}</li>
     * </ul>
     *
     * So the output of this method should be {@code 11:10 - 14:20; 2}
    *
     * 2. Let's say, that we have this times periods in repository:
     * <ul>
     *     <li>{@code 10:15,14:20}</li>
     *     <li>{@code 11:10,15:22}</li>
     *     <li>{@code 12:00,16:00}</li>
     * </ul>
     *
     * So the output of this method should be {@code 12:00 - 14:20; 3}
     *
     * 3. Let's say, that we have this times periods in repository:
     * <ul>
     *     <li>{@code 10:15,14:20}</li>
     *     <li>{@code 11:10,15:22}</li>
     *     <li>{@code 12:00,16:00}</li>
     *     <li>{@code 16:00,18:00}</li>
     * </ul>
     *
     * So the output of this method should be {@code 12:00 - 14:20; 3}
     *
     *
     * @return formatted string with required information. The string format looks like this: {@code <start time> - <time period end>; <number of visitors>}
     * @see pl.equinix.timeperiod.TimePeriodUtils#isCommon(TimePeriod, TimePeriod)
     * @see pl.equinix.timeperiod.TimePeriodUtils#getCommon(TimePeriod, TimePeriod)
     */
    public String getMostVisitingTimePeriod() {

        LinkedList<Visitor> all = repository.findAll();

        all.forEach(value -> {

            tempPeriod = value.getPeriod();

            all.forEach(value1 -> {
                if(!value.equals(value1)) {
                    if(TimePeriodUtils.isCommon(tempPeriod, value1.getPeriod())) {
                        tempCount++;

                        TimePeriod common = TimePeriodUtils.getCommon(tempPeriod, value1.getPeriod());

                        if(!common.getBeginTime().equals(common.getEndTime()))
                            tempPeriod = common;
                    }
                }
            });

            if(tempCount >= count) {
                count = tempCount;
                period = tempPeriod;
            }
            tempCount = 0;
        });

        return period.getBeginTime() + " - " + period.getEndTime() + "; " + count;
    }
}
