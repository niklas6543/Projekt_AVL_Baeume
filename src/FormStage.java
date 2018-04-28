import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class FormStage {

    private MainStage mainStage;
    private Stage primaryStage;
    private Stage subStage;

    private GridPane gridPane;

    /**
     * builds FormStage to edit the tree
     * handle editing and deleting of elements
     *
     * @param mainStage : MainStage : main stage to draw the tree
     * @version 2.0
     */
    public FormStage(MainStage mainStage) {
        this.mainStage = mainStage;
        this.primaryStage = this.mainStage.getPrimaryStage();
        this.subStage = new Stage();

        // init grid pane layout
        this.gridPane = new GridPane();

        Scene scene = new Scene(gridPane, 400, 200);

        // init text field for user entries
        TextField txtNodeAdd = new TextField();
        // add text field to grid layout
        this.gridPane.add(txtNodeAdd, 1,0);
        // init button to add an element
        Button btnAdd = new Button("Add Element");
        // call lambda function adding()
        btnAdd.setOnAction(actionEvent -> {
            if (!txtNodeAdd.getText().isEmpty()) {
                adding(Integer.parseInt(txtNodeAdd.getText()));
            }
        });
        // add button to grid pane
        this.gridPane.add(btnAdd, 2,0);

        // init text field for user entries
        TextField txtNodeDel = new TextField();
        // add text field to grid layout
        this.gridPane.add(txtNodeDel, 1,2);
        // init button to delete an element
        Button btnDel = new Button("Delete Element");
        // call lambda function deleting()
        btnDel.setOnAction(actionEvent -> {
            if (!txtNodeDel.getText().isEmpty()) {
                deleting(Integer.parseInt(txtNodeDel.getText()));
            }
        });
        // add button to grid pane
        this.gridPane.add(btnDel, 2,2);

        // init label
        Label lblInOrder = new Label();
        // init button to get elements inorder
        Button btnInOrder = new Button("INORDER");
        // call lambda function to modify text from lblInOrder
        btnInOrder.setOnAction(actionEvent -> lblInOrder.setText(inOrder().toString()));
        // add label and button to grid layout
        this.gridPane.add(btnInOrder, 1,3);
        this.gridPane.add(lblInOrder,2,3);

        // init label
        Label lblPreOrder = new Label();
        // init button to get elements preorder
        Button btnPreOrder = new Button("PREORDER");
        // call lambda function to modify text from lblPreOrder
        btnPreOrder.setOnAction(actionEvent -> lblPreOrder.setText(preOrder().toString()));
        // add label and button to grid layout
        this.gridPane.add(btnPreOrder, 1,4);
        this.gridPane.add(lblPreOrder,2,4);

        // init label
        Label lblPostOrder = new Label();
        // init button to get elements postorder
        Button btnPostOrder = new Button("POSTORDER");
        // call lambda function to modify text from lblPostOrder
        btnPostOrder.setOnAction(actionEvent -> lblPostOrder.setText(postOrder().toString()));
        // add label and button to grid layout
        this.gridPane.add(btnPostOrder, 1,5);
        this.gridPane.add(lblPostOrder,2,5);

        // add scene to subStage
        this.subStage.setScene(scene);
        // start stage
        this.subStage.show();
    }

    /**
     * calls the inserting method from current tree
     * then the tree will be redraw on main stage
     *
     * @param data : int : data from element to add
     */
    private void adding(int data) {
        // inserts the element
        this.mainStage.getTree().insertElement(new TElement(data, null, null));
        // redraw the tree
        this.mainStage.start(this.primaryStage);
    }

    /**
     * calls the deleting method from current tree
     * then the tree will be redraw on main stage
     *
     * @param data : int : data from element to delete
     */
    private void deleting(int data) {
        // deletes the element
        this.mainStage.getTree().deleteElement(this.mainStage.getTree().findElementByData(data));
        // redraw the tree
        this.mainStage.start(this.primaryStage);
    }

    /**
     * collects all element inorder from current tree
     * then build a new list with elements data
     *
     * @return elementsData : ArrayList<Integer>() : returns elements data inorder
     */
    private ArrayList<Integer> inOrder() {
        // get elements inorder
        ArrayList<TElement> elements = this.mainStage.getTree().getElementsInOrder();
        ArrayList<Integer> elementsData = new ArrayList<>();

        // iterate through elements to collect elements data
        for (TElement e : elements) {
            elementsData.add(e.getData());
        }

        return elementsData;
    }

    /**
     * collects all element preorder from current tree
     * then build a new list with elements data
     *
     * @return elementsData : ArrayList<Integer>() : returns elements data preorder
     */
    private ArrayList<Integer> preOrder() {
        // get elements preorder
        ArrayList<TElement> elements = this.mainStage.getTree().getElementsPreOrder();
        ArrayList<Integer> elementsData = new ArrayList<>();

        // iterate through elements to collect elements data
        for (TElement e : elements) {
            elementsData.add(e.getData());
        }

        return elementsData;
    }

    /**
     * collects all element postorder from current tree
     * then build a new list with elements data
     *
     * @return elementsData : ArrayList<Integer>() : returns elements data postorder
     */
    private ArrayList<Integer> postOrder() {
        // get elements postorder
        ArrayList<TElement> elements = this.mainStage.getTree().getElementsPostOrder();
        ArrayList<Integer> elementsData = new ArrayList<>();

        // iterate through elements to collect elements data
        for (TElement e : elements) {
            elementsData.add(e.getData());
        }

        return elementsData;
    }
}

