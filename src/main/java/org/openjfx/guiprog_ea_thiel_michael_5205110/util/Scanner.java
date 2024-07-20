package org.openjfx.guiprog_ea_thiel_michael_5205110.util;

import org.openjfx.guiprog_ea_thiel_michael_5205110.model.FileInfo;

public class Scanner
{
    public String prompt()
    {
        // Create a Scanner object for user input
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.println(Literals.PROMPT_FILE_NAME);
        String fileName = scanner.nextLine();

        // Construct the full file path
        String filePath = File.DIRECTORY + fileName + File.EXTENSION;

        // Update File Info (FOR GUI)
        FileInfo.getInstance().setFileName(fileName + File.EXTENSION);

        return filePath;
    }
}
