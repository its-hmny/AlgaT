package Classes;

import javafx.scene.image.Image;

public class Slides {

    /*  FIELDS  */
    private Image slideImage;
    private String slideExplanation;

    /*  METHODS */
    Slides(String stringToAssign, Image imageToAssign) {
        super();
        slideImage = imageToAssign;
        slideExplanation = stringToAssign;
    }

    //Returns true if a image should be present in the Slides
    public boolean containsImage() {
        return(slideImage != null);
    }

    public Image getSlideImage() {
        return(slideImage);
    }

    public String getSlideExplanation() {
        return(slideExplanation);
    }
}