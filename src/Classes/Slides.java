package Classes;

/*  IMPORTS */
import javafx.scene.image.Image;


//This class will be used for the tutorial part of the app, it contains String and Image that are used
//to set up every "Slide" of the lessons without changing always .fxml file
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

    public Image getPicture() {
        return(picture);
    }

    public String getText() {
        return(text);
    }

    public double[] getWidth_Height() {
        double[] imageSize = new double[2];
        imageSize[0] = picture.getHeight();
        imageSize[1] = picture.getWidth();
        return(imageSize);
    }
}