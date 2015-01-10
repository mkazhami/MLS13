/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import org.newdawn.slick.geom.Circle;

/**
 *
 * @author wanga8384
 */
public class Player extends Circle {
    //Declare global variables
    float xvelocity = 0;
    float yvelocity = 0;
    float acc;
    float friction;
    float cAngle;
    float power;
    float speed;
    String name;
    float charge;
    boolean hasPos = false;
    float drib;

    //Player constructor with attributes
    public Player(float acci, float frictioni, int startX, int startY, int radius, float p, float maxS, String nam, float cha, float dribS) {
        super(startX, startY, radius);
        acc = acci;
        friction = frictioni;
        power = p;
        speed = maxS;
        name = nam;
        charge = cha;
        drib = dribS;
    }

    public void setXvelocity(float x) {
        this.xvelocity = x;
    }

    public void setYvelocity(float y){
        this.yvelocity = y;
    }

    public float getXvelocity(){
        return this.xvelocity;
    }

    public float getYvelocity(){
        return this.yvelocity;
    }

    public float getCharge(){
        return this.charge;
    }

    public float getPower(){
        return this.power;
    }

    public float getMaxSpeed(){
        return this.speed;
    }

    //Method to update player speed, angle, and position
    public void updateVars() {
        if (xvelocity > this.getMaxSpeed()) {
            xvelocity = this.getMaxSpeed();
        }
        if (xvelocity < -this.getMaxSpeed()) {
            xvelocity = -this.getMaxSpeed();
        }
        if (yvelocity < -this.getMaxSpeed()) {
            yvelocity = -this.getMaxSpeed();
        }
        if (yvelocity > this.getMaxSpeed()) {
            yvelocity = this.getMaxSpeed();
        }
        this.setXvelocity(this.getXvelocity()*friction);
        this.setYvelocity(this.getYvelocity()*friction);
        super.setCenterX((float) (super.getCenterX() + this.getXvelocity()));
        super.setCenterY((float) (super.getCenterY() + this.getYvelocity()));
    }

    public void addxVel(float x) {
        this.setXvelocity(this.getXvelocity() + x);
    }

    public void addyVel(float y) {
        this.setYvelocity(this.getYvelocity() + y);
    }

    //If players meets a wall, send them the other way
    public void wallBounce(int side) {
        if (side == 0) {
            this.setXvelocity(this.getXvelocity() * -1);
            if (this.getXvelocity() < 0) {
                this.setCenterX(this.getCenterX() - 5);
            } else {
                this.setCenterX(this.getCenterX() + 5);
            }
        } else {
            this.setYvelocity(this.getYvelocity() * -1);
            if (this.getYvelocity() < 0) {
                this.setCenterY(this.getCenterY() - 5);
            } else {
                this.setCenterY(this.getCenterY() + 5);
            }
        }
    }

    //Basic move towards ball method
    public void moveTowards(float ballx, float bally) {
        this.setYvelocity(this.getYvelocity() + ((bally - this.getCenterY()) * 0.3f));
        this.setXvelocity(this.getXvelocity() + ((ballx - this.getCenterX()) * 0.3f));
    }

    //Move towards method for team 1. Player gets behind the ball and hits it towards the opposing side.
    public void moveTowardsTeam1(float ballx, float bally) {
        if (this.getCenterX() - this.getRadius() > ballx - 15) {
            if (this.getCenterY() - ballx < 50 && this.getCenterY() - ballx > -50){
                this.setYvelocity(this.getYvelocity() - 5);
            }
            if (this.getCenterY() + this.getRadius() > bally - 50 && this.getCenterY() - this.getRadius() < bally + 50) {
                this.setXvelocity(this.getXvelocity() - 2.5f);
            } else {
                this.setXvelocity(this.getXvelocity() - 2.5f);
                if (this.getCenterY() > bally-15) {
                    this.setYvelocity(this.getYvelocity() + ((bally + this.getCenterY()) * 0.5f));
                } else {
                    this.setYvelocity(this.getYvelocity() + ((bally - this.getCenterY()) * 0.5f));
                }

            }
        }
        else{
            moveTowards(ballx, bally);
        }
    }

    //Move towards method for team 2. Same concept as team 1's but reversed side.
    public void moveTowardsTeam2(float ballx, float bally) {
       if (this.getCenterX() + this.getRadius() < ballx + 15) {
            if (this.getCenterY() - ballx < 50 && this.getCenterY() - ballx > -50){
                this.setYvelocity(this.getYvelocity() - 5);
            }
            if (this.getCenterY() + this.getRadius() > bally - 50 && this.getCenterY() - this.getRadius() < bally + 50) {
                this.setXvelocity(this.getXvelocity() + 2.5f);
            } else {
                this.setXvelocity(this.getXvelocity() + 2.5f);
                if (this.getCenterY() > bally-15) {
                    this.setYvelocity(this.getYvelocity() - ((bally + this.getCenterY()) * 0.5f));
                } else {
                    this.setYvelocity(this.getYvelocity() + ((bally - this.getCenterY()) * 0.5f));
                }

            }
        }
        else{
            moveTowards(ballx, bally);
        }
        
    }
    
    public String getName(){
        return name;
    }

    //Method to make the goalie move to his original position after the ball has left his zone.
    public void goalieRetreat1() {
        if (this.getX() > 100) {
            xvelocity -= 1;
        }
        if (this.getY() > 320) {
            yvelocity -= 1;
        }
        if (this.getY() < 320) {
            yvelocity += 1;
        }
    }

    //Same as above but different position for other team's goalie
    public void goalieRetreat2() {
        if (this.getX() < 900) {
            xvelocity += 1;
        }
        if (this.getY() > 320) {
            yvelocity -= 1;
        }
        if (this.getY() < 320) {
            yvelocity += 1;
        }
    }

    //Method to keep the opposing defender behind the defense line but still following the ball.
    public void defenseRetreat(float ballx, float bally) {
        if (ballx > 600) {
            this.moveTowards(ballx, bally);
        } else if (this.getX() < 700) {
            xvelocity += 1;
        } else if (this.getX() > 700) {
            xvelocity -= 1;
        }
        if (this.getY() < bally) {
            yvelocity += 1f;
        }
        if (this.getY() > bally) {
            yvelocity -= 1f;
        }
    }
}
