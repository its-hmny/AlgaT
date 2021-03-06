package Classes;

/*  IMPORTS */
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

    /*  FIELDS */
    @FXML private AnchorPane window;
    @FXML private TextField input;
    private Pane treeView;
    private HBox arrayView;
    private final Integer NIL = -123456;
    private Integer[] vector = {NIL, 6, 5, 1, NIL, NIL, NIL, NIL};
    private int currentIndex = 3;
    //Contains the values on which the algorithm should be executed
    private int value = 1, y = currentIndex;
    //Indicates which algorithm the user is executing
    private int whatToDO;

    /*  METHODS */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        drawAll();
    }

    //Draw the Array and HeapTree
    private void drawAll() {
        //Draw the vector
        window.getChildren().remove(arrayView);
        arrayView = new HBox(10);
        drawArray();
        arrayView.relocate(100, 325);
        window.getChildren().add(arrayView);

        //Draw the HeapTree
        window.getChildren().remove(treeView);
        treeView = new Pane();
        treeView.minHeight(350);
        treeView.minWidth(650);
        drawTree(1, 0, 0, 325, 50, 150);
        window.relocate(25, 15);
        window.getChildren().add(treeView);
    }

    //Cycles through the array and make the array graphics
    private void drawArray() {

        for (int i = 1; i < vector.length; i++) {

            Text tmp1 = new Text();
            tmp1.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
            tmp1.setFill(Color.WHITE);
            Rectangle toAssign = new Rectangle(60, 60);
            toAssign.setId("my-rectangle");
            if (vector[i] == NIL) tmp1.setText("/");
            else tmp1.setText(vector[i].toString());
            arrayView.getChildren().add(i - 1, new StackPane(toAssign, tmp1));

        }

    }

    //Cycles through the array and put every node in the right position (thanks to the offset)
    private void drawTree(int index, double xOld, double yOld, double xNew, double yNew, double offset) {
        if (index < vector.length) {

            if (vector[index] != NIL) {
                double newOffset = offset / 2;

                if ((xOld == 0) && (yOld == 0)) {


                    drawTree(index * 2, xNew, yNew, xNew - newOffset, yNew + 100, newOffset);
                    drawTree(index * 2 + 1, xNew, yNew, xNew + newOffset, yNew + 100, newOffset);

                    Circle tmp1 = new Circle(30);
                    tmp1.setId("my-circle");
                    Text tmp2 = new Text(vector[index].toString());
                    tmp2.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
                    tmp2.setFill(Color.WHITE);
                    StackPane tmp3 = new StackPane(tmp1, tmp2);
                    tmp3.relocate(xNew, yNew);
                    tmp3.toFront();
                    treeView.getChildren().add(tmp3);

                } else {

                    Line tmp0 = new Line(xOld + 30, yOld + 30, xNew + 30, yNew + 30);
                    treeView.getChildren().add(tmp0);

                    drawTree(index * 2, xNew, yNew, xNew - newOffset, yNew + 100, newOffset);
                    drawTree(index * 2 + 1, xNew, yNew, xNew + newOffset, yNew + 100, newOffset);

                    Circle tmp1 = new Circle(30);
                    tmp1.setId("my-circle");
                    Text tmp2 = new Text(vector[index].toString());
                    tmp2.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
                    tmp2.setFill(Color.WHITE);
                    StackPane tmp3 = new StackPane(tmp1, tmp2);
                    tmp3.relocate(xNew, yNew);
                    tmp3.toFront();
                    treeView.getChildren().add(tmp3);
                }
            }
        }
    }

    //Load the welcome screen
    public void moveBack(ActionEvent event) {

        try {

            Parent prevLayout = FXMLLoader.load(getClass().getResource("/UI/Welcome.fxml"));
            Scene toSetUp = new Scene(prevLayout);
            Stage window = (Stage) (((Node) event.getSource()).getScene()).getWindow();
            window.setScene(toSetUp);
            window.show();

        } catch (Exception e) {

            new AlertBox("Error loading the Welcome screen");
            e.printStackTrace();

        }
    }

    public void insertPressed(ActionEvent e) {

        if (currentIndex < vector.length - 1) {

            String userInput = input.getCharacters().toString();
            Integer toAdd = Integer.valueOf(userInput);
            insert(toAdd);
            input.clear();

        } else new AlertBox("The vector is full, please remove at least one element");
    }

    public void removePressed(ActionEvent e) {
        whatToDO = 3;
        if (currentIndex > 1)
            deleteMax();
        else
            new AlertBox("The vector must contains at least one element");
    }

    public void HeapSortPressed(ActionEvent e) {
        heapSort(currentIndex);
    }

    //Depending on which algorithm has to execute, makes a step in the execution
    public void stepPressed(ActionEvent e) {
        if(whatToDO==1)
            value = step(value, y);
        else
            y =step(value, y);
    }

    //Deletes the node with the higher value, in this case the root of the tree
    private void deleteMax() {

        if (currentIndex > 1) {

            swap(1, currentIndex);
            vector[currentIndex] = NIL;
            currentIndex--;
            y =currentIndex;
            maxHeapRestore(1, currentIndex);

        }

        drawAll();
    }

    private void swap(int first, int second) {

        Integer tmp;
        tmp = vector[first];
        vector[first] = vector[second];
        vector[second] = tmp;

    }

    //Inserts a new node in the HeapTree
    private void insert(Integer n) {

        if (currentIndex == 0) {
            currentIndex++;
            y++;
            vector[currentIndex] = n;
        }

        if (currentIndex < vector.length) {
            currentIndex++;
            y =currentIndex;
            vector[currentIndex] = n;
        }

        drawAll();
        value=currentIndex;
        whatToDO=1;

    }

    private void maxHeapRestore(int i, int n) {
        whatToDO=2;
        int max = i;

        if (((2 * i) <= n) && (vector[2 * i] > vector[max]))
            max = 2 * i;

        if (((2 * i + 1) <= n) && (vector[2 * i + 1] > vector[max]))
            max = 2 * i + 1;

        if (i != max) {
            swap(i, max);
            maxHeapRestore(max, n);
        }

    }

    //Cycles through the array and creates the HeapTree with the value in the array
    private void heapBuild(int n) {
        whatToDO=2;
        for (int i = (n / 2); i >= 1; i--) {
            maxHeapRestore(i, n);
        }

    }

    //HeapSort algorithm, uses the HeapTree to sort the array
    private void heapSort(int n) {
        whatToDO=2;
        heapBuild(n);

    }

    // Makes a step in the algorithm when step button has been pressed
    public int step(int tmp, int y) {
        //If insert has been pressed
        if(whatToDO==1) {

            if ((vector[tmp] > vector[tmp / 2]) && tmp > 1) {
                swap(tmp, tmp / 2);
                tmp = tmp / 2;
                drawAll();
                return tmp;
            }
        }
        //If heapsort has been pressed
        else if(whatToDO==2)

            if(y >= 2) {
                swap(1, y);
                maxHeapRestore(1, y - 1);
                drawAll();
                y--;
                return y;
            }
            drawAll();
        return tmp;
    }

    //End the algorithm when all steps has been pressed
    public void allStepPressed(ActionEvent e){
        if(whatToDO==1)
            while((vector[value] > vector[value / 2]) && value > 1)
                    value = step(value, y);
        else
            while(y >= 2)
                y = step(value, y);
    }
}

