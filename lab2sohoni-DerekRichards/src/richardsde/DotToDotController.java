/*
 * Course: CS2852 - 071
 * Spring 2021
 * Lab 2 - Connect the Dots
 * Name: Derek Richards
 * Created: 3/21/2021
 */
package richardsde;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;

/**
 * The controller for the FXML file used to create the Graphical User Interface
 */
public class DotToDotController {
    FileChooser fileChooser = new FileChooser();
    @FXML
    Canvas imageDisplay;

    Path imagePath;
    Picture picture;
    List<Dot> emptyList = new LinkedList<>();

    /**
     * The method that opens a .dot file
     */
    @FXML
    public void fileOpen(){
        try{
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Text files", "*.dot"));
            fileChooser.setInitialDirectory(new File("./data"));
            picture = new Picture(emptyList);
            picture.setCanvas(imageDisplay);
            File selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null){
                imagePath = selectedFile.toPath();
                picture.load(imagePath);
            }
        } catch (IllegalArgumentException e){
            Alert error = new Alert(Alert.AlertType.ERROR, "A valid file type was not selected");
            error.setTitle("Error Dialog");
            error.setHeaderText("File Format Error");
            error.show();
        }
    }

    /**
     * Tries to save the current image.
     */
    @FXML
    public void fileSave(){
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text files", "*.dot"));
        fileChooser.setInitialDirectory(new File("./data"));
        File selectedFile = fileChooser.showSaveDialog(null);
        if (selectedFile != null && picture != null){
            imagePath = selectedFile.toPath();
            picture.save(imagePath);
        }
    }

    /**
     * Closes the application.
     */
    @FXML
    public void fileClose(){
        Platform.exit();
    }

    /**
     * Tries to recreate the current image with only lines.
     */
    @FXML
    public void linesOnly(){
        if (picture != null){
            picture.drawLines(imageDisplay);
        }
    }

    /**
     * Tries to recreate the current image with only dots.
     */
    @FXML
    public void dotsOnly(){
        if (picture != null){
            picture.drawDots(imageDisplay);
        }
    }

    /**
     * Tries to adjust an image so that the drawing only contains a certain amount of dots.
     */
    @FXML
    public void numberOfDots(){
        try {
            TextInputDialog setNumDots = new TextInputDialog();
            setNumDots.setHeaderText("Number of Dots");
            setNumDots.setTitle("Number of Dots");
            setNumDots.setContentText(
                    "Please select the number of dots you want the image to have");
            setNumDots.showAndWait();
            int numDots = Integer.parseInt(setNumDots.getEditor().getText());
            TextInputDialog setStrategy = new TextInputDialog();
            setStrategy.setHeaderText("Strategy to Use");
            setStrategy.setTitle("Strategy to Use");
            setStrategy.setContentText("Would you like to use indices or iterators?");
            setStrategy.showAndWait();
            String strategy = setStrategy.getEditor().getText();
            if (picture != null){
                picture.removeDots(numDots, strategy);
            }
        } catch (IllegalArgumentException e){
            Alert error = new Alert(Alert.AlertType.ERROR,
                    "Can't create an image with less than three dots.");
            error.setTitle("Error Dialog");
            error.setHeaderText("Not Enough Dots Error");
            error.show();
        } catch (InputMismatchException e){
            Alert error = new Alert(Alert.AlertType.ERROR,
                    "User input is not one of the two supported strategies");
            error.setTitle("Error Dialog");
            error.setHeaderText("Wrong Strategy Error");
            error.show();
        }

    }
}
