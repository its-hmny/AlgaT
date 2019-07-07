/* AlgaT Projects made by Enea Guidi, Marco Tomasone and Luca Genova */

/* IMPORT */

import Classes.AlertBox;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    /*  FIELDS  */
    private static Stage currentWindow;

    /* METHODS */
    public static void main(String[] args) {
        try {
            launch(args);
        } catch (Exception io)  {
            new AlertBox("I/O Exceptions check that the directory is correct");
            io.printStackTrace();
        } finally {
            System.out.println("Everything went fine, see you next time lucky bastard");
        }
    }

    public void start(Stage primaryStage) throws IOException {
        currentWindow = primaryStage;
        Parent startScreen = FXMLLoader.load(getClass().getResource("UI/Welcome.fxml"));
        setupWelcomeScene(currentWindow, startScreen);
        currentWindow.setOnCloseRequest(e->System.out.println("User closed the application"));
        currentWindow.show();
    }

    public void setupWelcomeScene(Stage window, Parent startScreen) {
        window.getIcons().add(new Image("Images/appLogo.png"));
        Scene welcomeScene = new Scene(startScreen);
        window.setTitle("AlgaT");
        window.setScene(welcomeScene);
    }

    public void startTutorial(ActionEvent event) throws Exception{
        Parent tutorialLayout = FXMLLoader.load(getClass().getResource("UI/TutorialScene.fxml"));
        Scene tutorialScene = new Scene(tutorialLayout);
        currentWindow.setScene(tutorialScene);
    }

    public void startSimulator(ActionEvent event) throws Exception {
        Parent simulatorLayout = FXMLLoader.load(getClass().getResource("UI/Simulator.fxml"));
        Scene simulatorScene = new Scene(simulatorLayout);
        currentWindow.setScene(simulatorScene);
    }

    public void startTests(ActionEvent event) throws Exception {
        Parent simulatorLayout = FXMLLoader.load(getClass().getResource("UI/TestDispatcher.fxml"));
        Scene simulatorScene = new Scene(simulatorLayout);
        currentWindow.setScene(simulatorScene);
    }
}