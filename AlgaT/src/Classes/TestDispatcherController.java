package Classes;

/* IMPORTS */
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class TestDispatcherController {  //tre bottoni per passare dagli esercizi sugli HeapTree, a quelli sull' HeapSort e ultimo bottone coming soon

    /* METHODS */
    public void heapTreeSelected(ActionEvent event) {

        try {

            Parent nextLayout = FXMLLoader.load(getClass().getResource("/UI/Test1.fxml"));
            Scene toSetUp = new Scene(nextLayout);
            Stage window = (Stage) (((Node) event.getSource()).getScene()).getWindow();
            window.setScene(toSetUp);
            window.show();

        } catch (Exception e) {
            new AlertBox("Error loading the test");
        }

    }

    public void heapSortSelected(ActionEvent event) {

        try {

            Parent nextLayout = FXMLLoader.load(getClass().getResource("/UI/Test6.fxml"));
            Scene toSetUp = new Scene(nextLayout);
            Stage window = (Stage) (((Node) event.getSource()).getScene()).getWindow();
            window.setScene(toSetUp);
            window.show();

        } catch (Exception e) {
            new AlertBox("Error loading the test");
        }

    }

    public void homeSelected(ActionEvent event) {

        try {

            Parent nextLayout = FXMLLoader.load(getClass().getResource("/UI/Welcome.fxml"));
            Scene toSetUp = new Scene(nextLayout);
            Stage window = (Stage) (((Node) event.getSource()).getScene()).getWindow();
            window.setScene(toSetUp);
            window.show();

        } catch (Exception e) {
            new AlertBox("Error loading the test");
        }

    }

    public void comingSoonSelected(ActionEvent event) {
        new AlertBox("We'll add more test and lessons later");
    }
}
