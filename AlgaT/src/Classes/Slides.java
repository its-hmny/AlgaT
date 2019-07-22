package Classes;

/*  IMPORTS */
import javafx.scene.image.Image;


//This class will be used for the tutorial part of the app, it contains String and Image that are used
//to set up every "Slide" of the lessons without changing always .fxml file
public class Slides {

    /*  FIELDS  */
    private Image picture;
    private String text;
    private String lessonNumber;

    /*  METHODS */
    Slides(String stringToAssign, Image imageToAssign, String lessonToAssign) {
        super();
        picture = imageToAssign;
        text = stringToAssign;
        lessonNumber = lessonToAssign;
    }

    public boolean containsImage() {
        return(picture != null);
    }

    public String getLessonNumber() {
        return(lessonNumber);
    }

    public Image getPicture() {
        return(picture);
    }

    public String getText() {
        return(text);
    }
}