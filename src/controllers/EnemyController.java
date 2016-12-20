package controllers;

import controllers.manangers.BodyManager;
import models.Model;
import utils.Utils;
import views.View;

import java.awt.*;
import java.util.Vector;

/**
 * Created by apple on 12/10/16.
 */
public class EnemyController extends Controller implements Body {

    private static final int SPEED = 2;
    private static final int WIDTH = 35;
    private static final int HEIGHT = 30;
    private int timeCounter;

    private Vector<EnemyBulletController> enemyBulletControllers;

    public EnemyController(Model model, View view) {
        super(model, view);
        enemyBulletControllers = new Vector<>();
        timeCounter = 0;
        BodyManager.instance.register(this);
    }
    public static double deg=-1;
    @Override
    public void run() {
        //Move

        this.model.move(SPEED, SPEED);



//        create2(100,100).model.move(-SPEED,0);
        //create2(100,100).model.move(0, SPEED);
        //create(200,0).model.move(-SPEED,SPEED);

        timeCounter++;
        if (timeCounter > 50) {
            shoot();
            timeCounter = 0;
        }

        for (EnemyBulletController enemyBulletController : this.enemyBulletControllers) {
            enemyBulletController.run();
        }
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        for (EnemyBulletController enemyBulletController : this.enemyBulletControllers) {
            enemyBulletController.draw(g);
        }
    }

    private void shoot() {
        // Create a new bullet
        EnemyBulletController enemyBulletController = EnemyBulletController.create (
                this.model.getMidX() - EnemyBulletController.WIDTH / 2, //getMidX
                this.model.getBottom()
        );

        // Add bullet to vector
        this.enemyBulletControllers.add(enemyBulletController);
    }

    public static EnemyController create(int x, int y) {
        return new EnemyController(
                new Model(x, y, WIDTH, HEIGHT),
                new View(Utils.loadImage("resources/plane1.png"))
        );
    }
//    public static EnemyController create2 (int a, int b) {
//        return new EnemyController(
//                new Model(a, b, WIDTH, HEIGHT, 1),
//                new View(Utils.loadImage("resources/enemy-green-1.png"))
//        );
 //   }
    public void onContact (Body other) {
        if (other instanceof BulletController) {
            System.out.println("huhu");
//            this.model.dechp(1);
//            if(int model.getHp() =0) {
                this.model.setAlive(false);
//            }
        }
    }
}
