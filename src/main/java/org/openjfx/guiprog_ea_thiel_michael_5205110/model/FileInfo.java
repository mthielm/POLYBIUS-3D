package org.openjfx.guiprog_ea_thiel_michael_5205110.model;

public class FileInfo
{
    private static FileInfo instance = new FileInfo();

    private String fileName;
    private String fileFormat;
    private int polygonCount;

    private FileInfo()
    {
    }

    public static FileInfo getInstance()
    {
        /*if(instance == null)
        {
            instance = new FileInfo();
        }*/
        return instance;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    public String getPolygonCount() {
        return String.valueOf(polygonCount);
    }

    public void setPolygonCount(int polygonCount) {
        this.polygonCount = polygonCount;
    }
}
