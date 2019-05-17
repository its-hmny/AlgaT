package Classes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class TutorialSceneController implements Initializable {

    private static Integer index;
    private static LinkedList<Slides> SlideList;
    @FXML private AnchorPane layout;
    @FXML private Label tutorialLabel;
    @FXML private ImageView tutorialImage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SlideList = new LinkedList<Slides>();
        index = 0;
        try {
            setupList(new File(getClass().getResource("../TextFile/TutorialExplanation.txt").getFile()));
        } catch (Exception e) {
            new AlertBox("There's a bug scanning TutorialExplanation");
            e.printStackTrace();
        }
        //La prima diapositiva deve per forza essere con immagine
        tutorialLabel.setText(SlideList.get(index).getSlideExplanation());
        tutorialImage.setImage(SlideList.get(index).getSlideImage());
    }

    /*This function will take the file as input and initialize the double linked list with the
    phrase of the tutorial, we need a function that reads a paragraph and stop before reaching the EOF*/
    private void setupList(File source) throws Exception {
        Integer counter = 0;
        Scanner scanFile = new Scanner(source);
        String paragraph = "";
            while (scanFile.hasNextLine()) {
                String line = scanFile.nextLine();
                if(line.contentEquals("##")) {
                    System.out.println("Paragraph's end reached");
                    SlideList.add(counter, new Slides(paragraph, null));
                    counter++;
                    paragraph = "";
                } else {
                    paragraph = paragraph.concat(line);
                }
            }
            scanFile.close();
    }

    public void nextSlide(ActionEvent event) {
        index++;
        try {
            Slides nextSlide = SlideList.get(index);
            if(nextSlide.containsImage()) {
                tutorialLabel.setTranslateX(0);
                tutorialLabel.setText(nextSlide.getSlideExplanation());
                tutorialImage.setImage(nextSlide.getSlideImage());
            } else {
                if (SlideList.get(index-1).containsImage())
                    tutorialLabel.setTranslateX(-100);
                tutorialLabel.setText(nextSlide.getSlideExplanation());
                tutorialImage = null;
            }
        } catch (Exception e) {
            index--;
            new AlertBox("Sei andato troppo avanti socio");
        }
    }

    public void previousSlide(ActionEvent event) {
        index--;
        try {
            Slides previousSlide = SlideList.get(index);
            if (previousSlide.containsImage()) {
                tutorialLabel.setTranslateX(0);
                tutorialLabel.setText(previousSlide.getSlideExplanation());
                tutorialImage.setImage(previousSlide.getSlideImage());
            } else {
                if(SlideList.get(index+1).containsImage())
                    tutorialLabel.setTranslateX(0);
                tutorialLabel.setText(previousSlide.getSlideExplanation());
                tutorialImage = null;
            }
        } catch (Exception e) {
            index++;
            new AlertBox("Devo ancora capire come tornare indietro alla schermata principale");
        }
    }
}