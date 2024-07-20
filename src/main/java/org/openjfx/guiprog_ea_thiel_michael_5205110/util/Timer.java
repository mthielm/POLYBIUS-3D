package org.openjfx.guiprog_ea_thiel_michael_5205110.util;

public class Timer
{
    private long startTime;
    private long endTime;

    public void start()
    {
        startTime = System.currentTimeMillis();
    }

    public void stop()
    {
        endTime = System.currentTimeMillis();
    }

    public void resetTimer()
    {
        startTime = 0;
        endTime = 0;
    }

    public String getRuntime()
    {
        return String.valueOf((endTime - startTime)/1000);
    }


}
