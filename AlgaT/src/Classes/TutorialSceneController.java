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
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.File;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class TutorialSceneController implements Initializable {

    /*  FIELDS  */
    private Integer currentIndex;
    private Integer maxIndex;
    private LinkedList<Slides> SlideList;
    @FXML private Label tutorialLabel;
    @FXML private ImageView tutorialImage;
    @FXML private Label lessonLabel;

    /*  METHODS */
    @Override
    //Takes care of setting up the first "Slide" of the tutorial and the whole list
    public void initialize(URL location, ResourceBundle resources) {
        SlideList = new LinkedList<Slides>();
        currentIndex = 0;
        tutorialImage.setPreserveRatio(true);
        tutorialImage.setSmooth(true);

        try {
            setupList(new File(getClass().getResource(".." +
                    "/TextFile/TutorialExplanation.txt").getFile()));
        } catch (Exception e) {
            new AlertBox("There's a bug scanning TutorialExplanation");
            e.printStackTrace();
        }

        tutorialLabel.setFont(new Font("Verdana", 15));
        tutorialLabel.setText(SlideList.get(currentIndex).getText());
        tutorialImage.setImage(SlideList.get(currentIndex).getPicture());
        lessonLabel.setText(SlideList.get(currentIndex).returnLessonType());

    }

    //Setup/creates the list of Slides
    private void setupList(File source) throws Exception {

        Integer counter = 0;
        Scanner scanFile = new Scanner(source);
        String paragraph = "";

            while (scanFile.hasNextLine()) {
                String line = scanFile.nextLine()
                        ;
                if(line.contentEquals("##")) {

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

        if (pos == 0) {
            Image image = new Image(getClass().getResourceAsStream("../Images/firstlesson.jpg"));
            SlideList.add(pos, new Slides(description, image, getLessonNumber(pos)));

        } else if (pos == 2) {
            Image image = new Image(getClass().getResourceAsStream("../Images/properties.jpg"));
            SlideList.add(pos, new Slides(description, image, getLessonNumber(pos)));

        } else if (pos == 3) {
            Image image = new Image(getClass().getResourceAsStream("../Images/Es5.png"));
            SlideList.add(pos, new Slides(description, image, getLessonNumber(pos)));

        } else if (pos == 6) {
            Image image = new Image(getClass().getResourceAsStream("../Images/heapdelete1.gif"));
            SlideList.add(pos, new Slides(description, image, getLessonNumber(pos)));

        } else if (pos == 7) {
            Image image = new Image(getClass().getResourceAsStream("../Images/heapinsertion.gif"));
            SlideList.add(pos, new Slides(description, image, getLessonNumber(pos)));

        } else if (pos == 8) {
            Image image = new Image(getClass().getResourceAsStream("../Images/heaprestore.gif"));
            SlideList.add(pos, new Slides(description, image, getLessonNumber(pos)));

        } else if (pos == 9) {
            Image image = new Image(getClass().getResourceAsStream("../Images/restore().PNG"));
            SlideList.add(pos, new Slides(description, image, getLessonNumber(pos)));

        } else if (pos == 10) {
            Image image = new Image(getClass().getResourceAsStream("../Images/heapSort.png"));
            SlideList.add(pos, new Slides(description, image, getLessonNumber(pos)));

        } else if (pos == 13) {
            Image image = new Image(getClass().getResourceAsStream("../Images/heapBuild.PNG"));
            SlideList.add(pos, new Slides(description, image, getLessonNumber(pos)));

        } else if (pos == 14) {
            Image image = new Image(getClass().getResourceAsStream("../Images/heapsort-pseudocode.PNG"));
            SlideList.add(pos, new Slides(description, image, getLessonNumber(pos)));

        } else
            SlideList.add(pos, new Slides(description, null, getLessonNumber(pos)));
    }

    //Setup the next slide on "Forward" button clicked
    public void nextSlide(ActionEvent event) {
        currentIndex++;
        try {

            //Means the lessons is finished, so reload Welcome screen
            if(currentIndex >= maxIndex) {

                Parent test1Layout = FXMLLoader.load(getClass().getResource("../UI/Finished.fxml"));
                Scene toSetUp = new Scene(test1Layout);
                Stage window = (Stage) (((Node) event.getSource()).getScene()).getWindow();
                window.setScene(toSetUp);
                window.show();

            } else {
                Slides nextSlide = SlideList.get(currentIndex);

                //Reset the label to the default position (Not centered but on the right)
                if (nextSlide.containsImage()) {

                    tutorialLabel.setTranslateX(0);
                    lessonLabel.setTranslateX(0);
                    tutorialLabel.setText(nextSlide.getText());
                    tutorialImage.setImage(nextSlide.getPicture());
                    lessonLabel.setText(nextSlide.returnLessonType());

                } else { //If the previous slide contained an Image then it move the label to the center

                    if (SlideList.get(currentIndex - 1).containsImage())
                        tutorialLabel.setTranslateX(-100); lessonLabel.setTranslateX(-100);

                    tutorialLabel.setText(nextSlide.getText());
                    tutorialImage.setImage(nextSlide.getPicture());
                    lessonLabel.setText(nextSlide.returnLessonType());

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

            //Reload welcome screen
            if (currentIndex < 0) {

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
                    lessonLabel.setTranslateX(0);
                    tutorialLabel.setText(previousSlide.getText());
                    tutorialImage.setImage(previousSlide.getPicture());
                    lessonLabel.setText(previousSlide.returnLessonType());

                } else { //If the previous slide contained an Image then it move the label to the center

                    if (SlideList.get(currentIndex + 1).containsImage())
                        tutorialLabel.setTranslateX(-100); lessonLabel.setTranslateX(-100);

                    tutorialLabel.setText(previousSlide.getText());
                    tutorialImage.setImage(previousSlide.getPicture());
                    lessonLabel.setText(previousSlide.returnLessonType());

                }
            }
            
        } catch (Exception e) {
            currentIndex++;
            new AlertBox("There was a problem moving back!");
        }
    }

    private String getLessonNumber(int pos) {
        if(pos < 10)return("Lesson 1");
        else return("Lesson 2");
    }
}