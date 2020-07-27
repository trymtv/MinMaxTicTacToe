module com.github.TrymTv {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.github.TrymTv to javafx.fxml;
    exports com.github.TrymTv;
}