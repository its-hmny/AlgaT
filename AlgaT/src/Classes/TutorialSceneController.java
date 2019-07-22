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

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
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
    @FXML private Label lessonNumber;

    /*  METHODS */
    @Override
    //Takes care of setting up the first "Slide" of the tutorial and the whole list
    public void initialize(URL location, ResourceBundle resources) {
        SlideList = new LinkedList<Slides>();
        currentIndex = 0;

        try {
            setupList();
            //setupList(new File(getClass().getResource("/TextFile/TutorialExplanation.txt").getFile()));
        } catch (Exception e) {
            new AlertBox("There's a bug scanning TutorialExplanation");
            e.printStackTrace();
        }

        tutorialLabel.setFont(new Font("Verdana", 15));
        tutorialLabel.setText(SlideList.get(currentIndex).getText());
        tutorialImage.setImage(SlideList.get(currentIndex).getPicture());
        lessonNumber.setText(SlideList.get(currentIndex).getLessonNumber());

    }

    //Setup/creates the list of Slides
    private void setupList() throws Exception {
        BufferedReader fileReader=null;
        Integer counter = 0;
        String paragraph = "";
        String check = "";
        try{
        InputStream inputFile = getClass().getResourceAsStream("/TextFile/TutorialExplanation.txt");
        fileReader = new BufferedReader(new InputStreamReader(inputFile, Charset.forName("UTF-8")));

        while ((check = fileReader.readLine())!= null) {
            String line = check;

            if (line.contentEquals("##")) {

                addToList(counter, paragraph);
                counter++;
                paragraph = "";

            }
            else
                paragraph = paragraph.concat(line);
        }

        }catch(IOException e){
            e.printStackTrace();
        } finally {
            try {
                maxIndex = counter;
                if (fileReader != null)
                    fileReader.close();
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }

    }

    //Takes the position as parameter to determine if load an image or only text
    private void addToList(Integer pos, String description) {

        if (pos == 0) {
            Image image = new Image(getClass().getResourceAsStream("/Images/firstlesson.jpg"));
            SlideList.add(pos, new Slides(description, image, returnLessonNumber(pos)));

        } else if (pos == 2) {
            Image image = new Image(getClass().getResourceAsStream("/Images/template.jpg"));
            SlideList.add(pos, new Slides(description, image, returnLessonNumber(pos)));

        } else if (pos == 3) {
            Image image = new Image(getClass().getResourceAsStream("/Images/Es5.png"));
            SlideList.add(pos, new Slides(description, image, returnLessonNumber(pos)));

        } else if (pos == 6) {
            Image image = new Image(getClass().getResourceAsStream("/Images/heapdelete1.gif"));
            SlideList.add(pos, new Slides(description, image, returnLessonNumber(pos)));

        } else if (pos == 7) {
            Image image = new Image(getClass().getResourceAsStream("/Images/heapinsertion.gif"));
            SlideList.add(pos, new Slides(description, image, returnLessonNumber(pos)));

        } else if (pos == 8) {
            Image image = new Image(getClass().getResourceAsStream("/Images/heaprestore.gif"));
            SlideList.add(pos, new Slides(description, image, returnLessonNumber(pos)));

        } else if (pos == 9) {
            Image image = new Image(getClass().getResourceAsStream("/Images/restore().PNG"));
            SlideList.add(pos, new Slides(description, image, returnLessonNumber(pos)));

        } else if (pos == 10) {
            Image image = new Image(getClass().getResourceAsStream("/Images/htree.jpg"));
            SlideList.add(pos, new Slides(description, image, returnLessonNumber(pos)));

        } else if (pos == 13) {
            Image image = new Image(getClass().getResourceAsStream("/Images/heapBuild.PNG"));
            SlideList.add(pos, new Slides(description, image, returnLessonNumber(pos)));

        } else if (pos == 14) {
            Image image = new Image(getClass().getResourceAsStream("/Images/heapsort-pseudocode.PNG"));
            SlideList.add(pos, new Slides(description, image, returnLessonNumber(pos)));

        } else
            SlideList.add(pos, new Slides(description, null, returnLessonNumber(pos)));
    }

    //Setup the next slide on "Forward" button clicked
    public void nextSlide(ActionEvent event) {
        currentIndex++;
        try {

            //Means the lessons is finished, so reload Welcome screen
            if(currentIndex >= maxIndex) {

                Parent test1Layout = FXMLLoader.load(getClass().getResource("/UI/Finished.fxml"));
                Scene toSetUp = new Scene(test1Layout);
                Stage window = (Stage) (((Node) event.getSource()).getScene()).getWindow();
                window.setScene(toSetUp);
                window.show();

            } else {
                Slides nextSlide = SlideList.get(currentIndex);

                //Reset the label to the default position (Not centered but on the right)
                if (nextSlide.containsImage()) {

                    tutorialLabel.setTranslateX(0); lessonNumber.setTranslateX(0);
                    tutorialLabel.setText(nextSlide.getText());
                    tutorialImage.setImage(nextSlide.getPicture());
                    lessonNumber.setText(nextSlide.getLessonNumber());

                } else { //If the previous slide contained an Image then it move the label to the center

                    if (SlideList.get(currentIndex - 1).containsImage())
                        tutorialLabel.setTranslateX(-100); lessonNumber.setTranslateX(-100);

                    tutorialLabel.setText(nextSlide.getText());
                    tutorialImage.setImage(nextSlide.getPicture());
                    lessonNumber.setText(nextSlide.getLessonNumber());

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

                Parent welcomeScreenLayout = FXMLLoader.load(getClass().getResource("/UI/Welcome.fxml"));
                Scene toSetUp = new Scene(welcomeScreenLayout);
                Stage window = (Stage) (((Node) event.getSource()).getScene()).getWindow();
                window.setScene(toSetUp);
                window.show();

            } else {
                Slides previousSlide = SlideList.get(currentIndex);

                //Reset the label to the default position (Not centered but on the right)
                if (previousSlide.containsImage()) {

                    tutorialLabel.setTranslateX(0); lessonNumber.setTranslateX(0);
                    tutorialLabel.setText(previousSlide.getText());
                    tutorialImage.setImage(previousSlide.getPicture());
                    lessonNumber.setText(previousSlide.getLessonNumber());

                } else { //If the previous slide contained an Image then it move the label to the center

                    if (SlideList.get(currentIndex + 1).containsImage())
                        tutorialLabel.setTranslateX(-100); lessonNumber.setTranslateX(-100);

                    tutorialLabel.setText(previousSlide.getText());
                    tutorialImage.setImage(previousSlide.getPicture());
                    lessonNumber.setText(previousSlide.getLessonNumber());

                }
            }
            
        } catch (Exception e) {
            currentIndex++;
            new AlertBox("There was a problem moving back!");
        }
    }

    private String returnLessonNumber(int actualPos) {
        if (actualPos < 10)
            return("Lesson 1");
        else if (actualPos < 20)
            return("Lesson 2");
        else return("Out of bound");
    }
}