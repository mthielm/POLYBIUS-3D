package org.openjfx.guiprog_ea_thiel_michael_5205110.util;

/**
 * Represents a timer to measure the runtime of the program.
 * The timer uses the System.currentTimeMillis() method to measure the time.
 *
 * @author mthiel
 */
public class Timer
{
    /**
     * The start time of the timer.
     */
    private long startTime;

    /**
     * The end time of the timer.
     */
    private long endTime;

    /**
     * Starts the timer.
     */
    public void start()
    {
        startTime = System.currentTimeMillis();
    }

    /**
     * Stops the timer.
     */
    public void stop()
    {
        endTime = System.currentTimeMillis();
    }

    /**
     * Resets the timer.
     */
    public void resetTimer()
    {
        startTime = Constants.ZERO;
        endTime = Constants.ZERO;
    }

    /**
     * Getter for the runtime of the timer.
     * Divides result by 1000 to get seconds.
     *
     * @return The runtime of the timer in seconds.
     */
    public String getRuntime()
    {
        return String.valueOf((endTime - startTime)/Constants.MILLISECONDS);
    }
}
