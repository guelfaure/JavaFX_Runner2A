import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import com.sun.javafx.geom.Rectangle;
//Guel FAURE 2G1TP5
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("farwest's runner");
        Group root = new Group();
        Pane pane = new Pane(root);
        GameScene thegamerunner = new GameScene(pane, 600, 300, 0, 0);//taille de la fenêtre
        pane.getChildren().add(thegamerunner.getBackgroundLeft().getBellevue());
        pane.getChildren().add(thegamerunner.getBackgroundRight().getBellevue());

        for (int i=1;i< thegamerunner.getNombrefoe()-1;i++){
            pane.getChildren().add(thegamerunner.listfoe.get(i).getImageAnimated());
        }

        primaryStage.setScene(thegamerunner);
        System.out.println("click on the mouse to start");//pour lancer le jeu effectuez un clic


        thegamerunner.setOnMouseClicked((event) -> {
            thegamerunner.herorobot.jump();//on crée une animation au démarrage du jeu
            thegamerunner.startgame+=1;
            thegamerunner.lancementdujeu();
            if (thegamerunner.startgame==1){pane.getChildren().add(thegamerunner.getNombredevie().getBellevue());
            pane.getChildren().add(thegamerunner.getHerorobot().getImageAnimated());}
        });

        thegamerunner.setOnKeyPressed( (event)->{   //la fonction sauter est lancé par un appui sur la touche espace
            System.out.println("Jump");
            thegamerunner.herorobot.jump();
        });

        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
