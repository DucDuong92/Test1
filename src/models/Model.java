package models;

import java.awt.*;

/**
 * Created by apple on 12/7/16.
 */
public class Model {
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean isAlive = true;
    private int hp;

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public Model(int x, int y, int width, int height, int hp) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.hp = hp;
    }

    public void move(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public int getMidX() {
        return this.x + this.width / 2;
    }

    public int getMidY() {
        return this.y + this.height / 2;
    }

    public int getBottom() {
        return this.y + this.height;
    }


    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Rectangle getRect(){
        return new Rectangle(x,y,width,height);
    }
    public boolean intersects (Model other){
        Rectangle rect1 = this.getRect();
        if (other == null) {
            //System.out.println(other.getClass().toString());
            //System.out.println("WTH");
        }
        Rectangle rect2 = other.getRect();
        return rect1.intersects(rect2);
    }
    public void dechp (int dec){
        hp--;
        if (hp<=0) {
            isAlive=false;
        }
    }
}
