import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.concurrent.TimeUnit;

//Guel FAURE 2G1TP5
/* création d'images animées, principalement le héro */

public abstract class AnimatedThing {
    public double x;
    public double y;
    public ImageView imageAnimated;
    private int maxXindex;
    private int index;
    private double durationbtw;
    private int maxIndex;
    private int largeurWindow;
    private int hauteurWndow;
    private int offsetFrame;

    public AnimatedThing(String filename, double x, double y, int maxXindex, int index, double durationbtw, int maxIndex,int minx, int miny, int largeurWindow, int hauteurWndow, int offsetFrame) {
        this.maxXindex = maxXindex;
        this.x = x;
        this.y = y;
        this.index = index;
        this.durationbtw = durationbtw;
        this.maxIndex = maxIndex;
        this.largeurWindow = largeurWindow;
        this.hauteurWndow = hauteurWndow;
        this.offsetFrame = offsetFrame;
        Image imageanim = new Image(filename);
        imageAnimated = new ImageView(imageanim);
        imageAnimated.setViewport( new Rectangle2D(minx, miny, largeurWindow, hauteurWndow));
        imageAnimated.setX(x);
        imageAnimated.setY(y);


    }

    public ImageView getImageAnimated(){
        return imageAnimated;
    }

    public double getDurationbtw() {
        return durationbtw;
    }
/* création de l'animation du héros en train de courrir: */

    public void updatefrag(){

        index = (index + 1) % maxIndex;
        int indiceX = 0;
        if (indiceX<maxXindex){
            indiceX = indiceX + index*offsetFrame;
        }
        else{
            indiceX=0;
        }
        imageAnimated.setViewport(new Rectangle2D(indiceX, 0, largeurWindow, hauteurWndow));

    }

    public  double getX(){
        return x;
    }
    public   double getY(){
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
