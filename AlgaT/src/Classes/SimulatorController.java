package Classes;

/* IMPORT */
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class SimulatorController implements Initializable {

    /*  FIELDS  */
    @FXML private AnchorPane window;
    @FXML private TextField input;
    private final Integer NIL = -123456;
    private Integer[] vector = {NIL, 6, 5, 1, NIL, NIL, NIL, NIL}; //I for e le altre funzioni partono da 1
    private int currentIndex = 3;
    private Pane treeView;
    private HBox arrayView;


    /* METHODS */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        drawAll();
    }

    private void drawAll() {
        //Draw the vector
        window.getChildren().remove(arrayView);
        arrayView = new HBox(10);
        drawArray();
        arrayView.relocate(100,325);
        window.getChildren().add(arrayView);

        //Draw the HeapTree
        window.getChildren().remove(treeView);
        treeView = new Pane();
        treeView.minHeight(350);
        treeView.minWidth(650);
        drawTree(1, 0,0, 325, 50, 150);
        window.relocate(25,15);
        window.getChildren().add(treeView);
    }

    private void drawArray() {

        //Creates the array visual in the main scene
        for(int i = 1; i < vector.length; i++) {
            Text tmp1 = new Text();
            tmp1.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
            tmp1.setFill(Color.WHITE);
            Rectangle toAssign = new Rectangle(60,60);
            toAssign.setId("my-rectangle");
            if(vector[i] == NIL) tmp1.setText("/");
            else tmp1.setText(vector[i].toString());
            arrayView.getChildren().add(i - 1, new StackPane(toAssign, tmp1));
        }

    }


    private void drawTree(int index, double xOld, double yOld, double xNew, double yNew, double offset) {
        if (index < vector.length) {
            //This two condition check if the current index is a HeapTree node and if is not out of vector length
            if (vector[index] != NIL) {
                double newOffset = offset / 2;

                if ((xOld == 0) && (yOld == 0)) {

                    //Root case, only a circle must be added, no line needed
                    drawTree(index * 2, xNew, yNew, xNew - newOffset, yNew + 100, newOffset);
                    drawTree(index * 2 + 1, xNew, yNew, xNew + newOffset, yNew + 100, newOffset);
                    createCircle(index, xNew, yNew);

                } else {

                    //Non root case, must be added also a line to the parent node
                    Line tmp0 = new Line(xOld + 30, yOld + 30, xNew + 30, yNew + 30);
                    treeView.getChildren().add(tmp0);
                    drawTree(index * 2, xNew, yNew, xNew - newOffset, yNew + 100, newOffset);
                    drawTree(index * 2 + 1, xNew, yNew, xNew + newOffset, yNew + 100, newOffset);
                    createCircle(index, xNew, yNew);
                }
            }
        }
    }

    private void createCircle(int index, double x, double y) {
        Circle tmp1 = new Circle(30);
        tmp1.setId("my-circle");
        Text tmp2 = new Text(vector[index].toString());
        tmp2.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        tmp2.setFill(Color.WHITE);
        StackPane tmp3 = new StackPane(tmp1, tmp2);
        tmp3.relocate(x, y);
        tmp3.toFront();
        treeView.getChildren().add(tmp3);
    }

    public void moveBack(ActionEvent event) {
        //Load the Welcome/Home screen
        try {
            Parent prevLayout = FXMLLoader.load(getClass().getResource("../UI/Welcome.fxml"));
            Scene toSetUp = new Scene(prevLayout);
            Stage window = (Stage) (((Node) event.getSource()).getScene()).getWindow();
            window.setScene(toSetUp);
            window.show();
        } catch (Exception e) {
            new AlertBox("Error loading home screen");
            e.printStackTrace();
        }
    }

    public void insertPressed(ActionEvent e) {
        if(currentIndex < vector.length - 1) {
            String userInput = input.getCharacters().toString();
            Integer toAdd = Integer.valueOf(userInput);
            insert(toAdd);
            input.clear();
            drawAll();
        } else new AlertBox("The vector is full");
    }

    public void removePressed(ActionEvent e) {
        if(currentIndex > 1) {
            deleteMax();
            drawAll();
        } else
            new AlertBox("The vector must contains at least one element");
    }

    public void HeapSortPressed(ActionEvent e) {
        heapSort(currentIndex);
    }

    public void deleteMax() {
        if (currentIndex > 1) {
            swap(1, currentIndex);
            vector[currentIndex]=NIL;
            currentIndex--;
            maxHeapRestore(1, currentIndex);
        }
        //drawAll();
    }

    public void swap(int i, int Pi) {
        Integer tmp;
        tmp = vector[i];
        vector[i] = vector[Pi];
        vector[Pi] = tmp;
    }

    public void insert(Integer n) {
        if (currentIndex == 0) {
            currentIndex++;
            vector[currentIndex] = n;
        }
        if (currentIndex < vector.length) {
            currentIndex++;
            vector[currentIndex] = n;
            maxHeapRestore(currentIndex, vector.length - 1);
        }
        int tmp=currentIndex;
        while ((vector[tmp] > vector[(int) tmp/2])&& tmp>1) {
            swap(tmp, tmp / 2);
            tmp = tmp / 2;
        }
    }


    public void maxHeapRestore(int i, int n) {
        int max = i;
        if (((2 * i) <= currentIndex) && (vector[2 * i] > vector[max]))
            max = 2 * i;
        if (((2 * i + 1) <= currentIndex) && (vector[2 * i + 1] > vector[max]))
            max = 2 * i + 1;
        if (i != max) {
            swap(i,max);
            maxHeapRestore(max, currentIndex);
        }
    }

    public void heapBuild(int n) {
        for (int i = n / 2; i > 1; i--)
            maxHeapRestore(i, n);
    }

    public void heapSort(int n) {
        heapBuild(n);
        for (int i = vector.length - 1; i > 2; i--) {
            int tmp;
            swap(i,1);
            maxHeapRestore(1, i - 1);
        }
    }
}