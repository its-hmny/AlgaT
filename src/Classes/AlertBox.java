package Classes;

/*  IMPORTS */
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
    public AlertBox(String messageError) {
        //Creates the stage
        Stage windowError = new Stage();
        windowError.initModality(Modality.APPLICATION_MODAL);
        windowError.setTitle(" Warning!");
        windowError.setMinWidth(400);
        windowError.setMinHeight(200);

        //Initializes the fields
        Label errorMessage = new Label(messageError);
        errorMessage.setTextAlignment(TextAlignment.JUSTIFY);
        errorMessage.setMaxWidth(200);
        errorMessage.setMaxHeight(100);
        errorMessage.setWrapText(true);
        Button confirm = new Button("OK");
        confirm.setOnAction(e->windowError.close());

        //Creates the layouts and the scene
        VBox layout = new VBox(30);
        layout.getChildren().addAll(errorMessage, confirm);
        layout.setAlignment(Pos.CENTER);
        Scene alertScene = new Scene(layout);
        windowError.setScene(alertScene);

        windowError.showAndWait();
    }
}
