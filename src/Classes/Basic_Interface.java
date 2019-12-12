package Classes;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

public interface Basic_Interface extends Initializable {
    //Load the next layout on the app, especially used in to change the test layout as the user proceed
    void moveForward(ActionEvent event);
    //Same as moveForward() but it's used to go back
    void moveBack(ActionEvent event);
}
