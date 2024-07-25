package org.openjfx.guiprog_ea_thiel_michael_5205110.util;

/**
 * Interface for file handling.
 * Contains constants for file handling.
 *
 * @author mthiel
 */
public interface File
{
    String DIRECTORY = "src/main/resources/org/openjfx/" +
                       "guiprog_ea_thiel_michael_5205110/";
    String FXML_FILE = "polygon-view.fxml";
    String EXTENSION = ".stl";
    String ASCII_FORMAT = "ASCII";
    String BINARY_FORMAT = "BINARY";
    String ASCII_HEADER_TAG = "solid";

    String ASCII_FORMAT_MESSAGE = "Datei ist im ASCII Format.";
    String BINARY_FORMAT_MESSAGE = "Datei ist im Binary Format.";
}