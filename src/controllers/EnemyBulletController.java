package controllers;

import controllers.manangers.BodyManager;
import models.Model;
import utils.Utils;
import views.View;

import java.util.Vector;

/**
 * Created by apple on 12/10/16.
 */
public class EnemyBulletController extends Controller implements Body {

    private static final int SPEED = 7;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;
    private Vector<EnemyBulletController> enemyBulletControllers;

    public EnemyBulletController(Model model, View view) {
        super(model, view);
        enemyBulletControllers = new Vector<>();
        BodyManager.instance.register(this);
    }

    @Override
    public void run() {
        model.move(0, SPEED);
    }

    public static EnemyBulletController create(int x, int y) {
        return new EnemyBulletController(
                new Model(x, y, WIDTH, HEIGHT),
                new View(Utils.loadImage("resources/bullet-round.png"))
        );
    }

    @Override
    public void onContact(Body other) {
        if (other instanceof PlaneController){
            System.out.println("trung dan");
            this.model.setAlive(false);
 //           this.model.dechp(1);
        }

    }
}
