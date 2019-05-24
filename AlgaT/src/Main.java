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
    private static Scene welcomeScene;
    private static Scene tutorialScene;
    private static Scene testScene;

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
        currentWindow.setOnCloseRequest(e->closeWindow());
        currentWindow.show();
    }

    public void setupWelcomeScene(Stage window, Parent startScreen) {
        window.getIcons().add(new Image("Images/appLogo.png"));
        welcomeScene = new Scene(startScreen);
        window.setTitle("AlgaT");
        window.setScene(welcomeScene);
    }

    public void startTutorialEvent(ActionEvent event) throws Exception{
        System.out.println("Start button clicked!");
        Parent tutorialLayout = FXMLLoader.load(getClass().getResource("UI/TutorialScene.fxml"));
        tutorialScene = new Scene(tutorialLayout);
        currentWindow.setScene(tutorialScene);
    }

    public void startTestEvent(ActionEvent event) throws Exception {
        System.out.println("Test button clicked");
        Parent testLayout = FXMLLoader.load(getClass().getResource("UI/TestLayout1.fxml"));
        new AlertBox("You should take the tutorial first!");
        testScene = new Scene(testLayout);
        currentWindow.setScene(testScene);
    }

    public void closeWindow() {
        //new AlertBox("Are you sure to exit?");
        System.out.println("Users closed the application...");
    }
}