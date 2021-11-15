import java.util.ArrayList;
import java.util.Collections;

public class Camera {
    private double x;
    private double y;
    private Hero hero;
    private double f;
    private double m;
    private double k;
    private double TimeBetweenUpdate;
    private ArrayList<Double> pastStates;




    Camera(double x, double y, Hero hero){
        this.x = x;
        this.y = y;
        this.hero=hero;
        this.f = 52;
        this.m = 0.8;
        this.k = (f*f*1.6)/4;     //force du ressort
        pastStates = new ArrayList(Collections.nCopies(4, 0.0));
        pastStates.set(0, x);
        pastStates.set(1, 0.0);
        pastStates.set(0, y);
        pastStates.set(3, 0.0);
        TimeBetweenUpdate = 0.016;

    }

/* f et k choisit arbitrairement pour avoir un temps de réponse faible */

    @Override
    public String toString(){
        return (x+"."+y);
    }

/* mise en équation du mouvement de la caméra par rapport au héros, la caméra est lié à un ressort au héros */

    public void update(){
        double accelX = (k/m)*(hero.getX()-40-pastStates.get(0))-(f/m)* pastStates.get(1);
        double speedX = pastStates.get(1) + accelX*TimeBetweenUpdate;
        double Xposition = pastStates.get(0) + speedX*TimeBetweenUpdate;
        pastStates.set(0, Xposition);
        pastStates.set(1, speedX);
        this.x = Xposition;

        double accelY = (k/m)*(hero.getY()-2-pastStates.get(2))-(f/m)* pastStates.get(3);
        double speedY = pastStates.get(3) + accelY*TimeBetweenUpdate;
        double Yposition = pastStates.get(2) + speedY*TimeBetweenUpdate;
        pastStates.set(0, Xposition);
        pastStates.set(1, speedX);
        this.y = Yposition;

    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
