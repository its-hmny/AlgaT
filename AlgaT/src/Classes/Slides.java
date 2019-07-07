package Classes;

/*  IMPORTS */
import javafx.scene.image.Image;

public class Slides {

    /*  FIELDS  */
    private Image picture;
    private String text;

    /*  METHODS */
    Slides(String stringToAssign, Image imageToAssign) {
        super();
        picture = imageToAssign;
        text = stringToAssign;
    }

    public boolean containsImage() {
        return(picture != null);
    }

    public boolean containsText() {
        return((text == " ") || (text == ""));
    }

    public Image getPicture() {
        return(picture);
    }

    public String getText() {
        return(text);
    }
}