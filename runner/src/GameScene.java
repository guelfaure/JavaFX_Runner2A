import com.sun.javafx.geom.Rectangle;
import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
//Guel FAURE 2G1TP5
public class GameScene extends Scene {
    public int startgame;
    Camera camera;
    StaticThing backgroundLeft;
    StaticThing backgroundRight;
    StaticThing nombredevie;
    ArrayList<Foe> listfoe;
    Hero herorobot;
    Double nombrefoe;
    Rectangle2D rectangleHitboxHero;
    private double score;
    private int nombrecoeur;//3 au départ


    GameScene(Parent root, double v1, double v2, int x, int y) {
        super(root, v1, v2);
        this.startgame=0;
        this.nombrecoeur=3;
        this.score=0.0;
        this.herorobot = new Hero("heros.png", 50, 250, 471, 0, 0.1, 6, 20, 0, 85, 100, 84);
        this.camera = new Camera(0,0,this.herorobot);
        this.backgroundLeft = new StaticThing("desert.png", 0,0);
        this.backgroundRight = new StaticThing("desert.png", 0,0);
        this.nombredevie = new StaticThing("hearts.png", 0, 0);
        nombredevie.bellevue.setViewport(new Rectangle2D(0, 0, 91, 24));
        backgroundLeft.bellevue.setViewport(new Rectangle2D(0,0,800,400));
        backgroundRight.bellevue.setViewport(new Rectangle2D(0,0,800,400));
        nombredevie.bellevue.setX(3);
        nombredevie.bellevue.setY(3);

        //géneration des ennemis avec un espacement réaliste
        this.listfoe = new ArrayList<>();
        double xfoerandom = 800+Math.random()*400; //position du premier ennemi
        this.nombrefoe = Math.ceil(Math.random()*1000);
        for (int i=0;i<nombrefoe-1;i++) {
            this.listfoe.add(new Foe("flamme.png",xfoerandom+Math.ceil(Math.random()*100), 346,60,0, 0.1, 2, 5, 5, 60, 95, 60, this.camera));
            xfoerandom=xfoerandom+900;
        }




    }

    public StaticThing getNombredevie() {return nombredevie;}

    public StaticThing getBackgroundLeft(){
        return backgroundLeft;
    }

    public StaticThing getBackgroundRight() {return backgroundRight;}

    public Hero getHerorobot() {return herorobot;}

    public ArrayList<Foe> getListfoe() {
        return listfoe;
    }

    public Double getNombrefoe() {
        return nombrefoe;
    }

    //fonction lancement du jeu

    public void lancementdujeu(){
        if (startgame==1){
            timer.start();
            System.out.print("Lets' go");}

    }



    public void update(long time){
        //mise à jour score qui correspond à la distance parcouru en pixel
        this.score= herorobot.getX();

        //mise à jour de l'écran

        double Xcamera = this.camera.getX();
        double backgroundWidth = 800;
        double backgroundHeight = 400;
        this.backgroundLeft.bellevue.setViewport(new Rectangle2D(Xcamera % backgroundWidth, camera.getY()%backgroundHeight, backgroundWidth-Xcamera % backgroundWidth, backgroundHeight-camera.getY()));
        this.backgroundRight.bellevue.setViewport(new Rectangle2D(0, camera.getY()%backgroundHeight, Xcamera % backgroundWidth,backgroundHeight-camera.getY()));
        this.backgroundRight.bellevue.setX(backgroundWidth-Xcamera % backgroundWidth);


        //mouvement hero et update de sa hitbox
        this.herorobot.imageAnimated.setX(this.herorobot.getX()- this.camera.getX());
        this.herorobot.imageAnimated.setY(this.herorobot.getY()-this.camera.getY());
        this.rectangleHitboxHero = new Rectangle2D(this.herorobot.getX()-this.camera.getX()+5,herorobot.getY()-this.camera.getY()+10,80,90);

        //mouvement ennemi (on veut qu'il soit fixe en y et non pas qu'il suive le mouvement de la caméra) ,update de la hitbox et détection collision
        for (int u=1;u<this.nombrefoe-1;u++) {
            this.listfoe.get(u).imageAnimated.setX(this.listfoe.get(u).getX());
            this.listfoe.get(u).imageAnimated.setY(346+this.listfoe.get(u).getY()-this.camera.getY()); //on positionne le feu au départ sur le chemin

            //detection collision, demande un ajustement minutieux, on compte aussi le nombre de vie restante
            if (herorobot.isInvincible(time)==false && ( new Rectangle2D(this.listfoe.get(u).getX()+10, this.listfoe.get(u).getY()+20+250, 40, 75).intersects(rectangleHitboxHero)==true)){
                System.out.println("Collision!");
                this.herorobot.invincibility = time+3100000000.0; //temps d'invincibilité

                //affichage des coeurs (on en perd à chaque collision)
                if (this.nombrecoeur>=0){this.nombrecoeur-=1;}
                switch (this.nombrecoeur){
                    case 3:
                        this.nombredevie.bellevue.setViewport(new Rectangle2D(0, 0, 91, 24));
                        break;
                    case 2:
                        this.nombredevie.bellevue.setViewport(new Rectangle2D(0, 0, 60, 24));
                        break;
                    case 1:
                        this.nombredevie.bellevue.setViewport(new Rectangle2D(0, 0, 30, 24));
                        break;
                    case 0://on n'affiche plus aucun coeur
                        this.nombredevie.bellevue.setViewport(new Rectangle2D(200, 300, 1, 1));
                        break;
                    case -1://toujours aucune vie mais ce coup ci c'est perdu!
                        timer.stop();
                        this.nombredevie.bellevue.setViewport(new Rectangle2D(300, 300, 1, 1));
                        this.herorobot.imageAnimated.setX(-100); //on fait disparaitre notre héros
                        System.out.println("Game Over!!"+" "+"score="+this.score);

                }

                //vérification jeu gagner:
                if(this.listfoe.get(this.listfoe.size()-1).getX()+60<(herorobot.getX()-this.camera.getX())){    //si le héro a dépasser tous les ennemi alors c'est la victoire
                    System.out.println("Well done! You win");
                }
            }
        }


    }






    long tim1=0;
    //animation du jeu
    AnimationTimer timer = new AnimationTimer()
    {public void handle(long time){
        if (time-tim1>=80_000_000) {       //100_000_000 en ns == durationbtw (temps entre chaques frags)
            tim1=time;
            herorobot.update(time);
            camera.update();
            update(time);

            for (int u=1;u<nombrefoe-1;u++) {
                listfoe.get(u).update();
                listfoe.get(u).updatefrag();
            }



        }
    }
    };



}