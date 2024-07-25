package org.openjfx.guiprog_ea_thiel_michael_5205110.util;

import org.openjfx.guiprog_ea_thiel_michael_5205110.model.FileInfo;
import org.openjfx.guiprog_ea_thiel_michael_5205110.view.Console;

/**
 * Represents a scanner for user input.
 * The input is used to construct the full file path for the STLReader.
 *
 * @author mthiel
 */
public class Scanner
{
    /**
     * Prompts the user to enter a file name.
     *
     * @return The full file path.
     * @Precondition: -
     * @Postcondition: The full file path is returned.
     */
    public String prompt()
    {
        // Create a Scanner object for user input
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        Console.log(Literals.PROMPT_FILE_NAME);
        String fileName = scanner.nextLine();

        // Construct the full file path
        String filePath = File.DIRECTORY + fileName + File.EXTENSION;

        // Update file info for GUI
        FileInfo.getInstance().setFileName(fileName + File.EXTENSION);

        return filePath;
    }
}