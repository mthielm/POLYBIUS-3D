package org.openjfx.guiprog_ea_thiel_michael_5205110.model;

/**
 * Represents information about the file that is currently loaded.
 * This class is a singleton.
 * It stores the file name, the file format and the number of polygons in the file.
 * The class is used to display the information in the GUI
 *
 * @author Michael Thiel
 */
public class FileInfo
{
    /**
     * The singleton instance of the class.
     */
    private static final FileInfo instance = new FileInfo();

    /**
     * The name of the file.
     */
    private String fileName;

    /**
     * The format of the file.
     */
    private String fileFormat;

    /**
     * The number of polygons in the file.
     */
    private int polygonCount;

    /**
     * Default private Constructor.
     */
    private FileInfo()
    {}

    /**
     * Returns the singleton instance of the class.
     *
     * @return The singleton instance of the class.
     */
    public static FileInfo getInstance()
    {
        return instance;
    }

    /**
     * Returns the name of the file.
     *
     * @return The name of the file.
     */
    public String getFileName()
    {
        return fileName;
    }

    /**
     * Returns the format of the file.
     *
     * @return The format of the file.
     */
    public String getFileFormat()
    {
        return fileFormat;
    }

    /**
     * Returns the number of polygons in the file.
     *
     * @return The number of polygons in the file.
     */
    public String getPolygonCount()
    {
        return String.valueOf(polygonCount);
    }

    /**
     * Sets the name of the file.
     *
     * @param fileName The name of the file.
     */
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    /**
     * Sets the format of the file.
     *
     * @param fileFormat The format of the file.
     */
    public void setFileFormat(String fileFormat)
    {
        this.fileFormat = fileFormat;
    }

    /**
     * Sets the number of polygons in the file.
     *
     * @param polygonCount The number of polygons in the file.
     */
    public void setPolygonCount(int polygonCount)
    {
        this.polygonCount = polygonCount;
    }
}
