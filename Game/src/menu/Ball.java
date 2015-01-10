/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import org.newdawn.slick.geom.Circle;

/**
 *
 * @author user
 */
public class Ball extends Circle {
    //Declare global variables

    float angle = 0;
    float xspeed = 0;
    float yspeed = 0;
    float velocity = 0;
    float friction = (float) 0.98;
    float speedLim = 20;

    //Ball constructor
    public Ball(float x, float y, float r) {
        super(x, y, r);
    }

    //Method to bounce if the ball hits a player at a corresponding velocity/angle
    public void bounce(float pX, float pY) {
        //Make its velocity the same as the player's
        xspeed = pX;
        yspeed = pY;
    }
    public void shot(float x, float y,float power,float str){
        float bx = getCenterX();
        float by = getCenterY();
        angle = (float) Math.atan((by-y)/(bx-x));
        if(x>bx){
            angle += Math.PI;
        }
        System.out.println("angle" + Math.toDegrees(angle));
        System.out.println("x " + str*power*Math.cos(angle));
        System.out.println("y" + str*power*Math.sin(angle));
        xspeed = (float) (str*power*Math.cos(angle));
        yspeed = (float) (str*power*Math.sin(angle));
    }



    public void updateVars() {
        //If ball gets below a certain speed, make it 0. Otherwise it gets infinitely small, but never 0
        if (xspeed < 0.2 && xspeed > -0.2) {
            xspeed = 0;
        }
        if (yspeed < 0.2 && yspeed > -0.2) {
            yspeed = 0;
        }
        //Limit speed to 5
        if (xspeed > 5) {
            xspeed = 5;
        }
        if (xspeed < -5) {
            xspeed = -5;
        }
        if (yspeed > 5) {
            yspeed = 5;
        }
        if (yspeed < -5) {
            yspeed = -5;
        }
        xspeed *= friction;
        yspeed *= friction;
        setCenterX((float) (getCenterX() + xspeed));
        setCenterY((float) (getCenterY() + yspeed));
        velocity = (float) Math.sqrt(Math.pow(xspeed, 2) + Math.pow(yspeed, 2));
    }

    //If ball meets a wall, send the other way
    public void wallBounce(int side, int team) {
        if (side == 0) {
            if(yspeed == 0){
                yspeed += 1;
            }
            xspeed *= -1.5f;
            if (xspeed < 0) {
                this.setCenterX(this.getCenterX() - 5);
            } else {
                this.setCenterX(this.getCenterX() + 5);
            }
        } else {
            if(xspeed == 0 && team == 1){
                xspeed += 1;
            }
            if(xspeed == 0 && team == 2){
                xspeed -= 1;
            }
            yspeed *= -1.5f;
            if (yspeed < 0) {
                this.setCenterY(this.getCenterY() - 5);
            } else {
                this.setCenterY(this.getCenterY() + 5);
            }
        }

    }

    public float getXspeed() {
        return this.xspeed;
    }

    public float getYspeed() {
        return this.yspeed;
    }

    public float getVelocity() {
        return this.velocity;
    }
}
