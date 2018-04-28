import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class MainStage extends Application {

    private Stage primaryStage;
    private Tree tree = new Tree(10);
    private AnchorPane anchorPane;

    /**
     * method to start primary stage
     *
     * starts the scene and draws the tree on anchorPane
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        // init anchorPane
        this.anchorPane = new AnchorPane();

        // build and set the dimensions of scene
        Scene scene = new Scene(this.anchorPane, 800, 800);

        // calls the method draw from tree for drawing the tree
        this.tree.draw(anchorPane);

        // create button to call FormStage
        Button btn = new Button("edit tree");
        btn.setOnAction(actionEvent -> new FormStage(this));

        // add button to anchorPane
        anchorPane.getChildren().add(btn);

        // add scene to primaryStage
        this.primaryStage.setScene(scene);
        // start primaryStage
        this.primaryStage.show();
    }

    // getter and setters

    public Tree getTree() {
        return tree;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
