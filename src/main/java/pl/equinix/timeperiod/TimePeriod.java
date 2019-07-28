package pl.equinix.timeperiod;

import java.time.LocalTime;

public class TimePeriod {

    /**
     * Visitors begin time
     */
    private final LocalTime beginTime;

    /**
     * Visitors end time
     */
    private final LocalTime endTime;



    public TimePeriod(LocalTime beginTime, LocalTime endTime) {
        this.beginTime = beginTime;
        this.endTime = endTime;
    }



    /**
     * Method returns {@code beginTime} value
     *
     * @return begin time value
     * @see #beginTime
     */
    public LocalTime getBeginTime() {
        return beginTime;
    }



    /**
     * Method return {@code endTime} value
     *
     * @return end time value
     * @see #endTime
     */
    public LocalTime getEndTime() {
        return endTime;
    }



    /**
     * Returns {@link pl.equinix.timeperiod.TimePeriod} value as string
     * @return {@link pl.equinix.timeperiod.TimePeriod} value as string
     */
    @Override
    public String toString() {
        return beginTime.toString() + "," + endTime.toString();
    }



    /**
     * This method check if this object is equal to {@code o} argument
     * @param o any object
     * @return {@code true} if objects are equal; {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        TimePeriod that = (TimePeriod) o;
        return beginTime.equals(that.beginTime) &&
                endTime.equals(that.endTime);
    }
}
