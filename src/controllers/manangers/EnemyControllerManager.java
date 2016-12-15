package controllers.manangers;

import java.awt.*;
import controllers.EnemyController;

import java.util.Vector;

/**
 * Created by apple on 12/10/16.
 */
public class EnemyControllerManager extends ControllerManager {
    private int timeCounter = 0;
    @Override
    public void run() {
        super.run();
        spawn();
    }

    private void spawn() {
        timeCounter++;
        if(timeCounter > 20) {

            //1: Create enemy
            EnemyController enemyController = EnemyController.create(300, 0);
            EnemyController enemyController2 = EnemyController.create2(200, 0);
            timeCounter = 0;

            //2: Add new enemy to vector
            this.controllers.add(enemyController);
            this.controllers.add(enemyController2);
        }
    }
}
