package Classes;

/* IMPORTS */
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;


public class FinishTestController implements Initializable {

    /*  METHODS */
    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    public void returnHome(ActionEvent event) {

        try {

            Parent nextLayout = FXMLLoader.load(getClass().getResource("/UI/Welcome.fxml")); //bottone che ti permette di tornare alla HOME
            Scene toSetUp = new Scene(nextLayout);
            Stage window = (Stage) (((Node) event.getSource()).getScene()).getWindow();
            window.setScene(toSetUp);
            window.show();

        } catch (Exception e) {
            new AlertBox("Error loading the next page");
        }
    }
}
