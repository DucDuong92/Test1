package controllers.manangers;

import controllers.BaseController;
import controllers.Body;
import controllers.Controller;
import controllers.PlaneController;
import controllers.enemies.EnemyController;
import models.Model;
import utils.Utils;
import views.SingleView;
import views.View;

import java.awt.*;

/**
 * Created by Duc Duong on 12/26/2016.
 */
public class BombController extends Controller implements BaseController, Body {
    public BombController(Model model, View view) {
        super(model, view);
        BodyManager.instance.register(this);
    }
    EnemyController enemyController;
    @Override
    public void run() {
    }

    @Override
    public void draw(Graphics g) {
        if (model.isAlive())
            super.draw(g);
    }

    @Override
    public void onContact(Body other) {
        if (other instanceof PlaneController) {
            System.out.println("an bom");
            this.getModel().setAlive(false);
        }

    }

    public static BombController create(int x, int y) {
        return new BombController(new Model(x, y, 40, 40), new SingleView(Utils.loadImage("resources/bomb.png")));
    }

}
