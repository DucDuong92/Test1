package controllers;

import models.Model;

/**
 * Created by Duc Duong on 12/14/2016.
 */
public interface Body { //Pure abstract
      Model getModel();
     void onContact(Body other);
}
