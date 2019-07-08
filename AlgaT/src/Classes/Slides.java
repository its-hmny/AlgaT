package Classes;

/*  IMPORTS */
import javafx.scene.image.Image;

public class Slides {

    /*  FIELDS  */
    private Image picture;
    private String text;
    private String lesson;

    /*  METHODS */
    Slides(String stringToAssign, Image imageToAssign, String lessonNumber) {
        super();
        picture = imageToAssign;
        text = stringToAssign;
        lesson = lessonNumber;
    }

    public boolean containsImage() {
        return(picture != null);
    }

    public String returnLessonType() {
        return(lesson);
    }

    public Image getPicture() {
        return(picture);
    }

    public String getText() {
        return(text);
    }
}