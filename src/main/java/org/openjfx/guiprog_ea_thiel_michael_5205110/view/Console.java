package org.openjfx.guiprog_ea_thiel_michael_5205110.view;

/**
 * Provides a simple logging mechanism.
 *
 * @author mthiel
 */
public class Console
{
    /**
     * Logs a message to the console.
     *
     * @param msg The message to log.
     * @Precondition: -
     * @Postcondition: Message is logged to the console.
     */
    public static void log(String msg)
    {
        System.out.println(msg);
    }
}