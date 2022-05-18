/*
 * Course: CS2852 - 071
 * Spring 2021
 * Lab 2 - Connect the Dots
 * Name: Derek Richards
 * Created: 3/21/2021
 */
package richardsde;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The driver class of the program.
 */
public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("DotToDot.fxml"));
            stage.setTitle("Dot to Dot");
            final int canvasWidth = 600;
            final int canvasHeight = 375;
            stage.setScene(new Scene(root, canvasWidth, canvasHeight));
            stage.show();
        } catch (IOException e){
            Alert error = new Alert(Alert.AlertType.ERROR, "An IO exception occurred.");
            error.setHeaderText("IO Exception");
            error.setTitle("Error Dialog");
            error.show();
        }

    }
}
