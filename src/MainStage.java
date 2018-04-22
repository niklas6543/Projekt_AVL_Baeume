import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class MainStage extends Application {

    private Stage primaryStage;
    private Tree tree = new Tree(10);
    private AnchorPane anchorPane;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        this.anchorPane = new AnchorPane();

        Scene scene = new Scene(this.anchorPane, 800, 800);


        this.tree.draw(anchorPane);

        Button btn = new Button("edit tree");
        btn.setOnAction(actionEvent -> new FormStage(this));

        anchorPane.getChildren().add(btn);

        this.primaryStage.setScene(scene);
        this.primaryStage.show();

    }

    public Tree getTree() {
        return tree;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
