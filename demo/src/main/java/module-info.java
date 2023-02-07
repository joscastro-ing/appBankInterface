module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires lombok;

    opens com.group1 to javafx.fxml;
    exports com.group1;
    exports com.group1.controller;
    opens com.group1.controller to javafx.fxml;

}