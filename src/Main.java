/* AlgaT Projects made by Enea Guidi, Marco Tomasone and Luca Genova */

/* IMPORT */
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
            io.printStackTrace();
        }
    }

    public void start(Stage primaryStage) throws IOException {
        currentWindow = primaryStage;
        Parent startScreen = FXMLLoader.load(getClass().getResource("UI/Welcome.fxml"));
        setupWelcomeScene(currentWindow, startScreen);
        currentWindow.show();
    }

    private void setupWelcomeScene(Stage window, Parent startScreen) {
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
        Parent simulatorLayout = FXMLLoader.load(getClass().getResource("UI/Test1.fxml"));
        Scene simulatorScene = new Scene(simulatorLayout);
        currentWindow.setScene(simulatorScene);
    }
}