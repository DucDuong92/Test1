package controllers;

import controllers.enemies.EnemyBulletController;
import controllers.enemies.EnemyController;
import controllers.manangers.BodyManager;
import controllers.manangers.BombController;
import controllers.manangers.ControllerManager;
import controllers.manangers.EnemyControllerManager;
import models.Model;
import utils.Utils;
import views.Animation;
import views.SingleView;
import views.View;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by apple on 12/3/16.
 */
public class PlaneController extends Controller implements Body {

    private static final int SPEED = 5;

    public KeySetting keySetting;
    EnemyController enemyController;
    private ControllerManager bulletManager;
    private int timecounter =20;
    private int lives =3;

    public static final PlaneController instance =  createPlane(300, 300);

    private PlaneController(Model model, View view) {
        super(model, view);
        bulletManager = new ControllerManager();
        BodyManager.instance.register(this);
    }

    public void keyPressed(KeyEvent e) {
        if(keySetting != null) {
            int keyCode = e.getKeyCode();
            if(keyCode == keySetting.keyUp) {
                model.move(0, -SPEED);
            } else if (keyCode == keySetting.keyDown) {
                model.move(0, SPEED);
            } else if (keyCode == keySetting.keyLeft) {
                model.move(-SPEED, 0);
            } else if (keyCode == keySetting.keyRight) {
                model.move(SPEED, 0);
            } else  if(keyCode == keySetting.keyShoot) {
                shoot();
            }
        }
    }

    @Override
    public void run() {
        super.run();
        bulletManager.run();
        if (!this.model.isAlive()){
            timecounter--;
            if( timecounter ==0){
                this.model.setAlive(true);
                timecounter =20;
                BodyManager.instance.register(this);
                this.model.setX(400);
                this.model.setY(100);
            }
        }
        if (lives == 0){
            System.out.println("Game Over!!!");
            System.out.println("------------");
            System.exit(0);
        }
    }

    @Override
    public void draw(Graphics g) {
        if(this.getModel().isAlive()) {
            super.draw(g);
            bulletManager.draw(g);
        }
    }

    private void shoot() {
        Utils.playSound("resources/shoot.wav", false);
        BulletController bulletController = BulletController.create(this.model.getMidX() - BulletController.WIDTH/ 2,
                this.model.getY() - BulletController.HEIGHT);
        bulletManager.add(bulletController);
    }

    // Design pattern
    // Factory
    private static PlaneController createPlane(int x, int y) {
        PlaneController planeController = new PlaneController(
                new Model(x, y, 70, 50),
                new SingleView(Utils.loadImage("resources/plane3.png"))
        );
        return planeController;
    }

    @Override
    public void onContact(Body other) {
        if (other instanceof EnemyBulletController) {
            System.out.println("Plane:'(");
            this.model.setAlive(false);
            destroy();
            Utils.playSound("resources/Explosion.wav", false);
            lives--;

        }

        if (other instanceof BombController){
            for (int i = 0; i < BodyManager.instance.getBodies().size(); i++) {
                if (BodyManager.instance.getBodies().get(i) instanceof EnemyController) {
                    int x = model.getMidX() - other.getModel().getMidX();
                    int y = model.getMidY() - other.getModel().getMidY();
                    double r = Math.sqrt(x * x + y * y);
                    if (r < 300) {
                        BodyManager.instance.getBodies().get(i).getModel().setAlive(false);
                    }
                }
            }
        }
    }
    public void destroy(){
        ExplosionController explosionController = new ExplosionController(
                new Model(this.getModel().getX()+ 10, this.getModel().getY()+ 10, 32, 32),
                new Animation(Utils.loadSheet("resources/explosion.png", 32,32,1,6))
        );
        ControllerManager.explosion.add(explosionController);
    }
}
