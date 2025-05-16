module org.example.l28 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens org.example.l28 to javafx.fxml;
    exports org.example.l28;
}