module com.github.TrymTv {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.github.trymtv to javafx.fxml;
    exports com.github.trymtv;
}