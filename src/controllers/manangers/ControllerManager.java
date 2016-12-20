package controllers.manangers;

import controllers.Controller;

import java.awt.*;

import java.util.Iterator;
import java.util.Vector;

/**
 * Created by apple on 12/10/16.
 */
public class ControllerManager {
    protected Vector<Controller> controllers;

    public ControllerManager() {
        controllers = new Vector<>();
    }

    public void draw(Graphics g) {
        for(Controller controller : this.controllers) {
            controller.draw(g);
        }
    }

    public void run() {

        for(Controller controller: this.controllers) {
            controller.run();
        }
        Iterator<Controller> interator = this.controllers.iterator();
        while (interator.hasNext()){
            Controller controller = interator.next();
            if(!controller.getModel().isAlive()){
                interator.remove();
               // BodyManager.instance.remove(controller);
            }
        }

    }

    public void add(Controller controller) {
        this.controllers.add(controller);
    }

    public void remove(Controller controller) {
        this.controllers.remove(controller);
    }
}
