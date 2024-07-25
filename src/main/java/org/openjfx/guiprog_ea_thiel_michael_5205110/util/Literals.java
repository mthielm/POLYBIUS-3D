package org.openjfx.guiprog_ea_thiel_michael_5205110.util;

public interface Literals
{
    String OPEN_PARENTHESIS = "(";
    String COMMA_X = "x,";
    String COMMA_Y = "y,";
    String COMMA_Z = "z";
    String CLOSE_PARENTHESIS = ")";

    String POLYGON_PREFIX = "Polygon:\n";
    String VERTICES_PREFIX = "\tvertices=";
    String EDGES_PREFIX = "\n\tedges=   ";
    String NORMAL_PREFIX = "\n\tnormal=";
    String AREA_PREFIX = ", area=";
    String POLYGON_SUFFIX = "}\n";

    String POLYHEDRON_PREFIX = "Polyhedron{";
    String POLYGONS_PREFIX = "polygons=";
    String SURFACE_AREA_PREFIX = ", surfaceArea=";
    String POLYHEDRON_SUFFIX = "}";

    String SORT_BEFORE = "Vor Sortierung:";
    String SORT_AFTER = "Nach Sortierung:";

    String VOLUME_CALCULATION_COMPLETE = "Finished volume calculation: ";

    String AREA = "Surface Area: ";
    String VOLUME = "Volume : ";
    String AREA_UNIT = "su (square units)";
    String VOLUME_UNIT = "cu (cubic units)";
    String INDENT = "            ";
    String SECONDS = " seconds";

    String NUMBER_OF_TRIANGLES = "Number of triangles: ";

    String WELCOME_TITLE =
            """
                                                                              \s
                    ██████╗  ██████╗ ██╗  ██╗   ██╗██████╗ ██╗██╗   ██╗███████╗
                    ██╔══██╗██╔═══██╗██║  ╚██╗ ██╔╝██╔══██╗██║██║   ██║██╔════╝
                    ██████╔╝██║   ██║██║   ╚████╔╝ ██████╔╝██║██║   ██║███████╗
                    ██╔═══╝ ██║   ██║██║    ╚██╔╝  ██╔══██╗██║██║   ██║╚════██║
                    ██║     ╚██████╔╝███████╗██║   ██████╔╝██║╚██████╔╝███████║
                    ╚═╝      ╚═════╝ ╚══════╝╚═╝   ╚═════╝ ╚═╝ ╚═════╝ ╚══════╝
                                                                              \s""";
    String GUI_TITLE = "POLYBIUS";
    String GUI_ABOUT_MENU = "This program was made with love ♥\n...and tears and sweat and blood\n\nDeveloper: mthiel\nContact: mthiel@stud.hs-bremen.de\n.";
    String PROMPT_FILE_NAME = "Please enter a filename [DEMO FILES: CUBE_ASCII,PYRAMID,SPHERE_750K,ALIEN]: " ;
    String WELCOME_MESSAGE = "Welcome to POLYBIUS, the lightweight and interactive STL Viewer! Files are read in binary and ASCII format.\nInspired by the legendary 1981 arcade game, beware of its psychoactive effects. Proceed with curiosity and caution\n";
    String ARROW = "-->";
    Character NEW_LINE = '\n';

    String ENTER_COMMAND = "Please enter a command: ";
    String ERROR = "Error: ";
    String ERROR_IN_OUT = "Error Eingabe/Ausgabe: ";
    String ERROR_ACCEPTING = "Error accepting: ";
    String CLIENT_CONNECTED = "Client connected!";
    String WAITING_FOR_CLIENT = "Waiting for client to connect...";
    String FILE = " File";
    String POLYGONS = " Polygons";
    String X = "x";
    String Y = "y";
    String Z = "z";
    String RED = "Red";
    String GREEN = "Green";
    String BLUE = "Blue";
    String YELLOW = "Yellow";
    String ORANGE = "Orange";
    String BLACK = "Black";
    String OK = "OK";
    String HOST_NAME = "127.0.0.1";
    String COORDINATES_MSG_1 = "Coordinates: X=";
    String COORDINATES_MSG_2 = ", Y=";
    String COORDINATES_MSG_3 = ", Z=";
    String ROTATION_MSG_1 = "Rotations: X=";
    String ROTATION_MSG_2 = "°, Y=";
    String ROTATION_MSG_3 = "°, Z=";
    String ROTATION_MSG_4 = "°";
    String BACKSPACE = " ";
    String COMMAND = "Command: ";
    String SLASH = "/";
    String TRANSLATE = "translate";
    String CONTROLLER_MSG = "Tatsächlich angesteuerter Controller: ";
    String TRANSLATION = "Translation: (";
    String COMMA = ",";
    String BRACKET = ")";
    String ROTATE = "rotate";
    String ROTATION = "Rotation: (";
    String MAPPING_POINTS = "Mapping Points...";
    String FACET = "facet";
    String REGEX = "\\s+";
    String VERTEX = "vertex";
    String ENDFACET = "endfacet";
    String PARSING_FILE = "Parsing file: ";
    String FINISHED_SURFACE_CALCULATION = "Finished surface calculation: ";
}
