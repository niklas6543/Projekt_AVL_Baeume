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

    public FormStage(MainStage mainStage) {
        this.mainStage = mainStage;
        this.primaryStage = this.mainStage.getPrimaryStage();
        this.subStage = new Stage();

        //this.anchorPane = new AnchorPane();
        this.gridPane = new GridPane();

        Scene scene = new Scene(gridPane, 400, 200);


        TextField txtNodeAdd = new TextField();
        this.gridPane.add(txtNodeAdd, 1,0);
        Button btnAdd = new Button("Add Element");
        btnAdd.setOnAction(actionEvent -> {
            if (!txtNodeAdd.getText().isEmpty()) {
                adding(Integer.parseInt(txtNodeAdd.getText()));
            }
        });
        this.gridPane.add(btnAdd, 2,0);

        TextField txtNodeDel = new TextField();
        this.gridPane.add(txtNodeDel, 1,2);
        Button btnDel = new Button("Delete Element");
        btnDel.setOnAction(actionEvent -> {
            if (!txtNodeDel.getText().isEmpty()) {
                deleting(Integer.parseInt(txtNodeDel.getText()));
            }
        });
        this.gridPane.add(btnDel, 2,2);

        Label lblInOrder = new Label();
        Button btnInOrder = new Button("INORDER");
        btnInOrder.setOnAction(actionEvent -> lblInOrder.setText(inOrder().toString()));
        this.gridPane.add(btnInOrder, 1,3);
        this.gridPane.add(lblInOrder,2,3);

        Label lblPreOrder = new Label();
        Button btnPreOrder = new Button("PREORDER");
        btnPreOrder.setOnAction(actionEvent -> lblPreOrder.setText(preOrder().toString()));
        this.gridPane.add(btnPreOrder, 1,4);
        this.gridPane.add(lblPreOrder,2,4);


        Label lblPostOrder = new Label();
        Button btnPostOrder = new Button("POSTORDER");
        btnPostOrder.setOnAction(actionEvent -> lblPostOrder.setText(postOrder().toString()));
        this.gridPane.add(btnPostOrder, 1,5);
        this.gridPane.add(lblPostOrder,2,5);

        this.subStage.setScene(scene);
        this.subStage.show();
    }

    private void adding(int data) {
        this.mainStage.getTree().insertElement(new TElement(data, null, null));
        this.mainStage.start(this.primaryStage);
    }

    private void deleting(int data) {

        this.mainStage.getTree().deleteElement(this.mainStage.getTree().findElementByData(data));
        this.mainStage.start(this.primaryStage);
    }

    private ArrayList<Integer> inOrder() {

        ArrayList<TElement> elements = this.mainStage.getTree().getElementsInOrder();
        ArrayList<Integer> elementsData = new ArrayList<>();

        for (TElement e : elements) {
            elementsData.add(e.getData());
        }

        return elementsData;
    }

    private ArrayList<Integer> preOrder() {

        ArrayList<TElement> elements = this.mainStage.getTree().getElementsPreOrder();
        ArrayList<Integer> elementsData = new ArrayList<>();

        for (TElement e : elements) {
            elementsData.add(e.getData());
        }

        return elementsData;
    }

    private ArrayList<Integer> postOrder() {

        ArrayList<TElement> elements = this.mainStage.getTree().getElementsPostOrder();
        ArrayList<Integer> elementsData = new ArrayList<>();

        for (TElement e : elements) {
            elementsData.add(e.getData());
        }

        return elementsData;
    }
}

