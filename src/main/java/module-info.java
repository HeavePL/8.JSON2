module org.example.json2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json.chargebee;


    opens org.example.json2 to javafx.fxml;
    exports org.example.json2;
}