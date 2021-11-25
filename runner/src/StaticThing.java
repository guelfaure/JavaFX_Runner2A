import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/* création d'une image statique (background) */
//Guel FAURE 2G1TP5
public class StaticThing {
    double x;
    double y;
    ImageView bellevue;

    StaticThing(String fileName, double x, double y){
        this.x = x;
        this.y = y;
        Image Background = new Image( fileName);
        bellevue = new ImageView(Background);
    }

    public ImageView getBellevue() {
        return bellevue;
    }
}
