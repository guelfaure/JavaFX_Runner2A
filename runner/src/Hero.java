import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Collections;

public class Hero extends AnimatedThing {
    private ArrayList<Double> pastStates;
    double gravity;
    double invincibility;

    public Hero(String filename, double x,double y, int maxXindex, int index, double durationbtw, int maxIndex, int minx, int miny, int largeurWindow, int hauteurWndow, int offsetFrame) {
        super(filename, x,y, maxXindex, index, durationbtw, maxIndex, minx, miny, largeurWindow, hauteurWndow, offsetFrame);
        pastStates = new ArrayList(Collections.nCopies(6, 0.0));
        this.pastStates.set(0,super.getX());    //position initiale en X du héro
        this.pastStates.set(1, 400.0);          //vitesse initiale en X du héro
        this.pastStates.set(2, 0.0);            //accéleration initiale en X du héro
        this.pastStates.set(3, super.getY());         //altitude initiale du héro
        this.gravity = 2500.0;
        this.invincibility = 3100000000.0;  //cette valeur permet dans la plupart du temps de ne pas prendre en compte le prochain ennemi (3.1s d'invincibilité)
    }

/* équation du mouvement du héros (méthode d'Euler) */

    public void update(){
        double accelX = pastStates.get(2);
        double speedX = pastStates.get(1)+super.getDurationbtw()*accelX;
        double positionX = pastStates.get(0)+super.getDurationbtw()*speedX;

        double accelY = pastStates.get(5);
        double speedY = pastStates.get(4) + super.getDurationbtw()*accelY;
        double positionY = pastStates.get(3) + super.getDurationbtw()*speedY;
        if (positionY>250){
            positionY=250;
            speedY=0;
        }


        pastStates.set(0, positionX);
        pastStates.set(1, speedX);
        pastStates.set(2, 1.8);        //accéleration en X constante choisie arbitrairement

        pastStates.set(3, positionY);
        pastStates.set(4, speedY);
        pastStates.set(5, this.gravity);

        super.setX(positionX);
        super.setY(positionY);

/* on modifie l'animation lors du saut pour ne pas avoir un héros qui courre lorsqu'il est en l'air*/

        if (speedY==0 && positionY==250) super.updatefrag(100_000_000);
        else if(speedY>0) imageAnimated.setViewport(new Rectangle2D(0, 160 , 86, 105)); //jump down
        else imageAnimated.setViewport(new Rectangle2D(0,160, 85, 105));                //jump up
    }
/*le saut est une impulsion d'accéleration en y*/
    public void jump(){
        if (pastStates.get(3)==250.0){
            pastStates.set(5,-3.8*this.gravity);
        }

    }
/*lorsque le héros est invincible on ne vérifie pas la collisions*/
    public boolean isInvincible(long time) {
        if (this.invincibility > time) {return true;}
        else {return false;}
    }


}

