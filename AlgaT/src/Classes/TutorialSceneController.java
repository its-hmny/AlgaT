package Classes;

/*  IMPORTS  */
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.File;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class TutorialSceneController implements Initializable {

    /*  FIELDS  */
    private static Integer currentIndex;
    private static Integer maxIndex;
    private static LinkedList<Slides> SlideList;
    @FXML private Label tutorialLabel;
    @FXML private ImageView tutorialImage;

    /*  METHODS */
    @Override   //Takes care of setting up the first "Slide" of the tutorial and the whole list
    public void initialize(URL location, ResourceBundle resources) {
        SlideList = new LinkedList<Slides>();
        currentIndex = 0;
        try {
            setupList(new File(getClass().getResource("../TextFile/TutorialExplanation.txt").getFile()));
        } catch (Exception e) {
            new AlertBox("There's a bug scanning TutorialExplanation");
            e.printStackTrace();
        }
        //La prima diapositiva deve per forza essere con immagine
        tutorialLabel.setText(SlideList.get(currentIndex).getText());
        tutorialImage.setImage(SlideList.get(currentIndex).getPicture());
    }

    //Setup the list of slides
    private void setupList(File source) throws Exception {
        Integer counter = 0;
        Scanner scanFile = new Scanner(source);
        String paragraph = "";
            while (scanFile.hasNextLine()) {
                String line = scanFile.nextLine();
                if(line.contentEquals("##")) {
                    System.out.println("Paragraph's end reached");
                    addToList(counter, paragraph);
                    counter++;
                    paragraph = "";
                } else {
                    paragraph = paragraph.concat(line);
                }
            }
            maxIndex = counter;
            scanFile.close();
    }

    //Takes the position as parameter to determine if load an image or only text
    private void addToList(Integer pos, String description) {
        if (pos == 0 || pos == 1 || pos == 3) {
            Image image = new Image(getClass().getResourceAsStream("../Images/appLogo.png"));
            SlideList.add(pos, new Slides(description, image));
        } else
            SlideList.add(pos, new Slides(description, null));
    }

    //Setup the next slide on "Forward" button clicked
    public void nextSlide(ActionEvent event) {
        currentIndex++;
        try {
            if(currentIndex >= maxIndex) {
                //Load test screen
                new AlertBox("The tutorial is finished. Now you can make a little test to see if you understood");
                Parent test1Layout = FXMLLoader.load(getClass().getResource("../UI/TestLayout1.fxml"));
                Scene toSetUp = new Scene(test1Layout);
                Stage window = (Stage) (((Node) event.getSource()).getScene()).getWindow();
                window.setScene(toSetUp);
                window.show();
            } else {
                Slides nextSlide = SlideList.get(currentIndex);
                if (nextSlide.containsImage()) {
                    //Reset the label to the default position (Not centered but on the right)
                    tutorialLabel.setTranslateX(0);
                    tutorialLabel.setText(nextSlide.getText());
                    tutorialImage.setImage(nextSlide.getPicture());
                } else {
                    //If the previous slide contained an Image then it move the label to the center
                    if (SlideList.get(currentIndex - 1).containsImage())
                        tutorialLabel.setTranslateX(-100);
                    tutorialLabel.setText(nextSlide.getText());
                    tutorialImage.setImage(nextSlide.getPicture());
                }
            }
        } catch (Exception e) {
            currentIndex--;
            new AlertBox("There was a problem moving forward");
            e.printStackTrace();
        }
    }

    //Setup the previous slide on "Back" button clicked
    public void previousSlide(ActionEvent event) {
        currentIndex--;
        try {
            if (currentIndex < 0) {
                //Reload welcome screen
                Parent welcomeScreenLayout = FXMLLoader.load(getClass().getResource("../UI/Welcome.fxml"));
                Scene toSetUp = new Scene(welcomeScreenLayout);
                Stage window = (Stage) (((Node) event.getSource()).getScene()).getWindow();
                window.setScene(toSetUp);
                window.show();
            } else {
                Slides previousSlide = SlideList.get(currentIndex);
                //Reset the label to the default position (Not centered but on the right)
                if (previousSlide.containsImage()) {
                    tutorialLabel.setTranslateX(0);
                    tutorialLabel.setText(previousSlide.getText());
                    tutorialImage.setImage(previousSlide.getPicture());
                } else {
                    //If the previous slide contained an Image then it move the label to the center
                    if (SlideList.get(currentIndex + 1).containsImage())
                        tutorialLabel.setTranslateX(0);
                    tutorialLabel.setText(previousSlide.getText());
                    tutorialImage.setImage(previousSlide.getPicture());
                }
            }
        } catch (Exception e) {
            currentIndex++;
            new AlertBox("There was a problem moving back!");
        }
    }
}