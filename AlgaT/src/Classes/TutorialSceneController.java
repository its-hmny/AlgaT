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


public class TutorialSceneController implements Initializable {

    /*  FIELDS  */
    private Integer currentIndex;
    private Integer maxIndex;
    private final String EMPTY = "";
    private LinkedList<Slides> SlideList;
    @FXML private Label tutorialLabel;
    @FXML private ImageView tutorialImage;

    /*  METHODS */
    @Override
    //Takes care of setting up the first "Slide" of the tutorial and the whole list
    public void initialize(URL location, ResourceBundle resources) {
        SlideList = new LinkedList<Slides>();
        currentIndex = 0;
        setupList();

        tutorialLabel.setFont(new Font("Verdana", 15));
        tutorialLabel.setText(SlideList.get(currentIndex).getText());
        tutorialImage.setImage(SlideList.get(currentIndex).getPicture());
    }

    //Setup/creates the list of Slides
    private void setupList() {
        try {
            readFromFile();
        } catch(IOException e) {
            e.printStackTrace();
            new AlertBox("There's a bug scanning TutorialExplanation");
        }
    }

    //Read the TutorialExplanation.txt and load the paragraph
    private void readFromFile() throws IOException {
        BufferedReader fileReader;
        Integer counter = 0;  String paragraph = EMPTY;
        String check = EMPTY;    Image imageToLoad = null;
        InputStream inputFile = getClass().getResourceAsStream("/TextFile/TutorialExplanation.txt");
        fileReader = new BufferedReader(new InputStreamReader(inputFile, Charset.forName("UTF-8")));

        while ((check = fileReader.readLine())!= null) {
            String line = check;

            //Reached the end of paragraph, loads all into the slide
            if (line.contentEquals("##")) {
                addToList(counter, paragraph, imageToLoad);
                counter++;
                paragraph = EMPTY; imageToLoad = null;
            }
            //Found an image URL, loads the image and clear the string
            else if(line.contains("**")) {
                line = line.replace("*", " ").trim();
                imageToLoad = loadImageFromURL(line);
                line = EMPTY;
            }

            else paragraph = paragraph.concat(line);
        }

        maxIndex = counter;
        if (fileReader != null)
            fileReader.close();
    }

    private Image loadImageFromURL(String URL) {
        return(new Image(getClass().getResourceAsStream(URL)));
    }

    //Takes the position as parameter to determine if load an image or only text
    private void addToList(Integer pos, String description, Image picture) {
        SlideList.add(pos, new Slides(description, picture));
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

                    tutorialLabel.setTranslateX(0);
                    tutorialLabel.setText(nextSlide.getText());
                    tutorialImage.setImage(nextSlide.getPicture());

                } else { //If the previous slide contained an Image then it move the label to the center

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

                    tutorialLabel.setTranslateX(0);
                    tutorialLabel.setText(previousSlide.getText());
                    tutorialImage.setImage(previousSlide.getPicture());

                } else { //If the previous slide contained an Image then it move the label to the center

                    if (SlideList.get(currentIndex + 1).containsImage())
                        tutorialLabel.setTranslateX(-100);

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