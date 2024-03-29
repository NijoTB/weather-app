module com.elmassihg.weatherhackathon {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires junit;


    opens com.example.weatherhackathon to javafx.fxml;
    exports com.example.weatherhackathon;
}