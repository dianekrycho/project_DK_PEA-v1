module com.example.project_dk_peav1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.project_dk_peav1 to javafx.fxml;
    exports com.example.project_dk_peav1;
}