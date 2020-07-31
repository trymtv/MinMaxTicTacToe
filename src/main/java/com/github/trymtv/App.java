package com.github.trymtv;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static FXMLLoader loader;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 420, 420);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml, boolean isAi) throws IOException {
        scene.setRoot(loadFXML(fxml));
        SecondaryController controller = loader.getController();
        controller.setUseMinMax(isAi);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        loader = fxmlLoader;
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}