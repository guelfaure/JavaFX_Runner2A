import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import com.sun.javafx.geom.Rectangle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Hello world");
        Group root = new Group();
        Pane pane = new Pane(root);
        GameScene thegamerunner = new GameScene(pane, 600, 300, 0, 0);
        pane.getChildren().add(thegamerunner.getBackgroundLeft().getBellevue());
        pane.getChildren().add(thegamerunner.getBackgroundRight().getBellevue());
        pane.getChildren().add(thegamerunner.getNombredevie().getBellevue());
        pane.getChildren().add(thegamerunner.getHerorobot().getImageAnimated());
        for (int i=1;i< thegamerunner.getNombrefoe()-1;i++){
            pane.getChildren().add(thegamerunner.listfoe.get(i).getImageAnimated());
        }

        primaryStage.setScene(thegamerunner);

        thegamerunner.setOnMouseClicked( (event)->{
            System.out.println("Jump");
            thegamerunner.herorobot.jump();
        });

        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
