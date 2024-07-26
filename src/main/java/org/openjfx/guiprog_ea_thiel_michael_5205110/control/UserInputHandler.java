package org.openjfx.guiprog_ea_thiel_michael_5205110.control;

import org.openjfx.guiprog_ea_thiel_michael_5205110.model.FileInfo;
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.File;
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Literals;
import org.openjfx.guiprog_ea_thiel_michael_5205110.view.Console;

public class UserInputHandler
{
    public void printWelcomeMessage()
    {
        // Print welcome message and title
        Console.log(Literals.WELCOME_TITLE);
        Console.log(Literals.WELCOME_MESSAGE);
    }

    public String getFilePathFromUser()
    {
        // Create a Scanner to read user input from console
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

