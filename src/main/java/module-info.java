module org.openjfx.guiprog_ea_thiel_michael_5205110 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.openjfx.guiprog_ea_thiel_michael_5205110 to javafx.fxml;
    exports org.openjfx.guiprog_ea_thiel_michael_5205110;
    exports org.openjfx.guiprog_ea_thiel_michael_5205110.net;
    opens org.openjfx.guiprog_ea_thiel_michael_5205110.net to javafx.fxml;
    exports org.openjfx.guiprog_ea_thiel_michael_5205110.control;
    opens org.openjfx.guiprog_ea_thiel_michael_5205110.control to javafx.fxml;
    exports org.openjfx.guiprog_ea_thiel_michael_5205110.view;
    opens org.openjfx.guiprog_ea_thiel_michael_5205110.view to javafx.fxml;
}