import javafx.animation.AnimationTimer;

import java.util.ArrayList;
import java.util.Collections;

public class Foe extends MovingTHING {
    private ArrayList<Double> pastStates;
    double gravity;
    private  Camera camera;


    public Foe(String filename, double x, double y, int maxXindex, int index, double durationbtw, int maxIndex, int minx, int miny, int largeurWindow, int hauteurWndow, int offsetFrame, Camera camera) {
        super (filename, x,y,maxXindex,index, durationbtw, maxIndex, minx, miny, largeurWindow, hauteurWndow, offsetFrame);
        pastStates = new ArrayList(Collections.nCopies(6, 0.0));
        this.pastStates.set(0,super.getX());
        this.pastStates.set(1,-200.0);
        this.camera=camera;
    }
//équation mouvement des ennemis, les ennemis iront de plus en plus vite pour rendre le jeu dynamique et de plus en plus dure
    public void update(){
        double accelX = pastStates.get(2);
        double speedX = pastStates.get(1) + accelX*getDurationbtw();
        double positionX = pastStates.get(0) + speedX*getDurationbtw();



        pastStates.set(0, positionX);
        pastStates.set(1, speedX);
        pastStates.set(2, -2.2);

/*l'équation en y permet aux ennemis de ne pas accompagner la caméra lors d'un saut*/
        super.setX(positionX);
        super.setY(-camera.getY());
    }




}