/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import java.io.*;

public class TwoPlayer extends BasicGameState {

    //Declare global variables
    PrintWriter write;
    ExitButton exit = null;
    Button pause1 = null;
    Button pause2 = null;
    boolean pauseFlag = false;
    Image turf = null;
    Image net1 = null;
    Image net2 = null;
    Image crowd1 = null;
    Image crowd2 = null;
    String name;
    int score;
    int goalsFor;
    int goalsAgainst;
    //True if win, false if loss
    public static boolean winLoss;
    public static Player[] team1 = new Player[3];
    public static Player[] team2 = new Player[3];
    //Team 1 players
    Image player1 = null;
    Image player2 = null;
    Image player3 = null;
    //Team 2 players
    Image player4 = null;
    Image player5 = null;
    Image player6 = null;
    //Ball
    Image ballPic = null;
    public static int control = 1;
    public static int control2 = 1;
    int count = 0;
    int bounceLim = 0;
    boolean button = false;
    Ball ball = null;
    int teamHit = 0;
    boolean power = false;
    boolean power2 = false;
    int time;
    Sound goal = null;
    float energy = 5;
    float energy2 = 5;
    Image sPow;
    Image sPow2;
    Image pow = null;
    Image pow2 = null;

    public TwoPlayer(int state) {
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        //Initialize variables - create players and ball
        exit = new ExitButton("exit1.png");
        exit.setX(885);
        exit.setY(10);
        exit.setSize(1f);
        pause1 = new Button("pause1.png");
        pause1.setX(840);
        pause1.setY(10);
        pause1.setSize(0.9f);
        pause2 = new Button("pause2.png");
        pause2.resize(pause1.getX() + 5, pause1.getY() + 5, 0.6f);
        turf = new Image("turf.png");
        net1 = new Image("net1.png");
        net2 = new Image("net2.png");
        crowd1 = new Image("crowd.png");
        crowd2 = new Image("crowd2.png");
        resetTeam();
        ballPic = new Image("ballPic.png");
        ball = new Ball(490, 340, 15);
        score = 0;
        goalsFor = 0;
        goalsAgainst = 0;
        time = 120000;
        goal = new Sound("goal.wav");
        pow = new Image("power.jpg");
        pow2 = new Image("power.jpg");
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        sPow = pow.getSubImage(0, 0, (int) (pow.getWidth() * ((energy - 5) / 15)), pow.getHeight());
        sPow2 = pow2.getSubImage(0, 0, (int) (pow2.getWidth() * ((energy2 - 5) / 15)), pow2.getHeight());
        //Draw field/stadium
        turf.draw(50, 115);
        net1.draw(0, 260, 0.9f);
        net2.draw(950, 260, 0.9f);
        crowd1.draw();
        crowd1.draw(333, 0);
        crowd1.draw(666, 0);
        crowd2.draw(0, 600);
        crowd2.draw(333, 600);
        crowd2.draw(666, 600);
        //Draw ball
        ballPic.draw(ball.getX(), ball.getY(), 0.05f);
        player1.draw(team1[0].getX(), team1[0].getY(), 0.3f);
        player2.draw(team1[1].getX(), team1[1].getY(), 0.3f);
        player3.draw(team1[2].getX(), team1[2].getY(), 0.3f);
        player4.draw(team2[0].getX(), team2[0].getY(), 0.3f);
        player5.draw(team2[1].getX(), team2[1].getY(), 0.3f);
        player6.draw(team2[2].getX(), team2[2].getY(), 0.3f);
        //Draw players
        for (int c = 0; c < team1.length; c++) {
            g.draw(team1[c]);
            g.draw(team2[c]);
        }
        //Draw exit sign
        exit.draw(exit.getX(), exit.getY(), exit.getSize());
        if (pauseFlag == true) {
            pause2.draw(pause2.getX(), pause2.getY(), pause2.getSize());
        } else {
            pause1.draw(pause1.getX(), pause1.getY(), pause1.getSize());
        }
        g.drawString("NY:  " + goalsFor, 10, 100);
        g.drawString("LA:  " + goalsAgainst, 100, 100);
        g.drawString("Time: " + Integer.toString(time / 1000), 200, 100);
        g.setColor(Color.white);
        g.drawString(team1[control].getName(), team1[control].getMinX() - 2, team1[control].getMinY() - 12);
        g.drawString(team2[control2].getName(), team2[control2].getMinX() - 2, team2[control2].getMinY() - 12);
        sPow.draw(team1[control].getMinX() - 15, team1[control].getMaxY() + 20, 0.5f);
        sPow2.draw(team2[control2].getMinX() - 15, team2[control2].getMaxY() + 20, 0.5f);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();
        //If paused, only resume the game if they press the pause button again
        //Or they can exit the game
        if (gc.isPaused()) {
            if (input.isKeyPressed(Input.KEY_P)) {
                pauseFlag = false;
                gc.resume();
                ////RESUME GAME////
            }
            if ((input.getMouseX() <= exit.getWidth() + exit.getX() && input.getMouseX() >= exit.getX()) && (input.getMouseY() <= exit.getY() + exit.getHeight() && input.getMouseY() >= exit.getY())) {
                if (input.isMousePressed(0)) {
                    resetTeam();
                    init(gc, sbg);
                    sbg.enterState(0);
                    /////////////EXIT GAME//////////////
                }
            }
        } //Only run if the game is not paused
        else {
            time -= delta;
            if (time <= 0) {
                init(gc, sbg);
                sbg.enterState(0);
            }
            if (input.isKeyPressed(Input.KEY_P)) {
                pauseFlag = true;
                gc.pause();
                ////PAUSE GAME////
            }
            if ((input.getMouseX() <= exit.getWidth() + exit.getX() && input.getMouseX() >= exit.getX()) && (input.getMouseY() <= exit.getY() + exit.getHeight() && input.getMouseY() >= exit.getY())) {
                if (input.isMousePressed(0)) {
                    resetTeam();
                    init(gc, sbg);
                    sbg.enterState(0);
                    /////////////EXIT GAME//////////////
                }
            }
            //Space bar to switch players - only once every 10 frames
            if (input.isKeyPressed(Input.KEY_SPACE)) {
                if (control == 2) {
                    control = 1;
                } else {
                    control += 1;
                }
            }
            if (input.isKeyPressed(Input.KEY_RALT)) {
                if (control2 == 2) {
                    control2 = 1;
                } else {
                    control2 += 1;
                }
            }

            //WASD key controls - change player coordinates according to key held
            if (input.isKeyDown(Input.KEY_A)) {
                team1[control].addxVel(-(team1[control].acc));
            }
            if (input.isKeyDown(Input.KEY_D)) {
                team1[control].addxVel(team1[control].acc);
            }

            if (input.isKeyDown(Input.KEY_W)) {
                team1[control].addyVel(-(team1[control].acc));
            }
            if (input.isKeyDown(Input.KEY_S)) {
                team1[control].addyVel(team1[control].acc);
            }
            if (input.isKeyDown(Input.KEY_LSHIFT)) {
                power = true;
                if (energy < 20) {
                    energy += team1[control].getCharge();
                } else {
                    power = false;
                    energy = 5;
                }
            }

            if (input.isKeyDown(Input.KEY_LEFT)) {
                team2[control2].addxVel(-(team2[control2].acc));
            }
            if (input.isKeyDown(Input.KEY_RIGHT)) {
                team2[control2].addxVel(team2[control2].acc);
            }

            if (input.isKeyDown(Input.KEY_UP)) {
                team2[control2].addyVel(-(team2[control2].acc));
            }
            if (input.isKeyDown(Input.KEY_DOWN)) {
                team2[control2].addyVel(team2[control2].acc);
            }
            if (input.isKeyDown(Input.KEY_RSHIFT)) {
                power2 = true;
                if (energy2 < 20) {
                    energy2 += team2[control2].getCharge();
                } else {
                    power2 = false;
                    energy2 = 5;
                }
            }


            //Limit - one bounce per x number of frames
            bounceLim++;
            //Check if scored
            if ((ball.getCenterY() + ball.getRadius() < 260 + net1.getHeight()) && (ball.getCenterY() - ball.getRadius() > 260) && (ball.getCenterX() - ball.getRadius() < 50)) {
                if (Play.soundOn == true) {
                    goal.play();
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    ex.getMessage();
                }
                goalsAgainst += 1;
                score -= 10;
                ball.setCenterX(500);
                ball.setCenterY(350);
                ball.xspeed = 0;
                ball.yspeed = 0;
                resetTeam();
                ball.updateVars();

            } else if ((ball.getCenterY() + ball.getRadius() < 260 + net2.getHeight()) && (ball.getCenterY() - ball.getRadius() > 260) && (ball.getCenterX() + ball.getRadius() > 950)) {
                if (Play.soundOn == true) {
                    goal.play();
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    ex.getMessage();
                }
                goalsFor += 1;
                score += 50;
                ball.setCenterX(500);
                ball.setCenterY(350);
                ball.xspeed = 0;
                ball.yspeed = 0;
                resetTeam();
                ball.updateVars();

            } //If ball meets wall, send it the other way
            else {
                if (ball.getCenterX() + ball.getRadius() > 950 || ball.getCenterX() - ball.getRadius() < 50) {
                    ball.wallBounce(0, teamHit);
                }
                if ((ball.getCenterY() + ball.getRadius() > 585 || ball.getCenterY() - ball.getRadius() < 115)) {
                    ball.wallBounce(1, teamHit);
                }
            }
            ball.updateVars();

            for (int c = 1; c < team1.length; c++) {
                if (team1[c].getCenterX() + team1[c].getRadius() > 950 || team1[c].getCenterX() - team1[c].getRadius() < 50) {
                    team1[c].wallBounce(0);
                }
                if (team1[c].getCenterY() + team1[c].getRadius() > 585 || team1[c].getCenterY() - team1[c].getRadius() < 115) {
                    team1[c].wallBounce(1);
                }
            }

            for (int c = 1; c < team2.length; c++) {
                if (team2[c].getCenterX() + team2[c].getRadius() > 950 || team2[c].getCenterX() - team2[c].getRadius() < 50) {
                    team2[c].wallBounce(0);
                }
                if (team2[c].getCenterY() + team2[c].getRadius() > 585 || team2[c].getCenterY() - team2[c].getRadius() < 115) {
                    team2[c].wallBounce(1);
                }
            }
            //Goalie movements for team 1
            if (ball.getCenterX() - ball.getRadius() < 200 && ball.getCenterY() - ball.getRadius() < 450 && ball.getCenterY() + ball.getRadius() > 200) {
                team1[0].moveTowards(ball.getCenterX(), ball.getCenterY());
            }
            if (ball.getCenterX() - ball.getRadius() > 200 || (ball.getCenterY() - ball.getRadius() > 450 && ball.getCenterY() + ball.getRadius() < 200)) {
                team1[0].goalieRetreat1();
            }
            //Goalie movements for team 2
            if (ball.getCenterX() - ball.getRadius() > 800 && ball.getCenterY() - ball.getRadius() < 450 && ball.getCenterY() + ball.getRadius() > 200) {
                team2[0].moveTowards(ball.getCenterX(), ball.getCenterY());
            }
            if (ball.getCenterX() - ball.getRadius() < 800 || (ball.getCenterY() - ball.getRadius() > 450 && ball.getCenterY() + ball.getRadius() < 200)) {
                if (team2[0].getX() != 900 && team2[0].getY() != 320) {
                    team2[0].goalieRetreat2();
                }
            }
            //Update ball in case of bounce
            ball.updateVars();
            for (int c = 0; c < team1.length; c++) {
                team1[c].updateVars();
                team2[c].updateVars();
            }


            //Check if other player needs to go for ball
            //If controlled player is far away then the other player will attack the ball
            //If the other player gets too far away from the ball, he will approach it but will not attack the ball
            //Made so two players on the user's team aren't fighting each other for the ball
            for (int c = 1; c < team1.length; c++) {
                if (c != control) {
                    if (team1[control].getX() < ball.getX() - 200 || team1[control].getX() > ball.getX() + 250 || team1[control].getY() < ball.getY() - 200 || team1[control].getY() > ball.getY() + 200) {
                        team1[c].moveTowardsTeam1(ball.getCenterX(), ball.getCenterY());
                    } else if ((team1[c].getX() < ball.getX() - 200 || team1[c].getX() > ball.getX() + 250) || (team1[c].getY() < ball.getY() - 200 || team1[c].getY() > ball.getY() + 200)) {
                        team1[c].moveTowardsTeam1(ball.getCenterX(), ball.getCenterY());
                    }

                }
                if (c != control2) {
                    if (team2[control2].getX() < ball.getX() - 200 || team2[control2].getX() > ball.getX() + 250 || team2[control2].getY() < ball.getY() - 200 || team2[control2].getY() > ball.getY() + 200) {
                        team2[c].moveTowardsTeam2(ball.getCenterX(), ball.getCenterY());
                    } else if ((team2[c].getX() < ball.getX() - 200 || team2[c].getX() > ball.getX() + 250) || (team2[c].getY() < ball.getY() - 200 || team2[c].getY() > ball.getY() + 200)) {
                        team2[c].moveTowardsTeam2(ball.getCenterX(), ball.getCenterY());
                    }
                }
            }

            //Check for ball-player interactions
            for (int c = 0; c < team1.length; c++) {
                if (ball.intersects(team1[c])) {
                    if (c == 0) {
                        ball.setCenterX(team1[0].getCenterX() + team1[0].getRadius() + ball.getRadius());
                    } else {
                        if (power == true) {
                            ball.shot(team1[c].getCenterX(), team1[c].getCenterY(), energy / 3, team1[c].getPower());
                            energy = 5;
                        } else {
                            ball.bounce(team1[c].getXvelocity(), team1[c].getYvelocity());
                            power = false;
                        }
                        ball.updateVars();
                        bounceLim = 0;
                        teamHit = 1;
                    }
                }
                if (ball.intersects(team2[c])) {
                    if (c == 0) {
                        ball.setCenterX(team2[0].getCenterX() - team2[0].getRadius() - ball.getRadius());
                    } else {
                        if (power2 == true) {
                            ball.shot(team2[c].getCenterX(), team2[c].getCenterY(), energy2 / 3, team2[c].getPower());
                            energy2 = 5;
                        } else {
                            ball.bounce(team2[c].getXvelocity(), team2[c].getYvelocity());
                            power2 = false;
                        }
                        ball.updateVars();
                        bounceLim = 0;
                        teamHit = 2;
                    }
                }
            }

            //Update ball and player
            for (int c = 0; c < team1.length; c++) {
                team1[c].updateVars();
                team2[c].updateVars();
            }
            ball.updateVars();
        }
    }

    public int getID() {
        return 2;
    }

    public void resetTeam() throws SlickException {
        team1[0] = new Player(1f, 0.7f, 100, 320, 25, 1, 2, "Olave", 0.1f, 3f);
        team1[1] = new Player(5f, 0.5f, 250, 340, 25, 8, 4, "Cahill", 0.09f, 1.5f);
        team1[2] = new Player(8f, 0.5f, 250, 200, 25, 6, 4, "Henry", 0.075f, 1.2f);
        team2[0] = new Player(1f, 0.6f, 900, 320, 25, 1, 2, "Gonzalez", 0.05f, 3f);
        team2[1] = new Player(3f, 0.5f, 750, 340, 25, 7, 3, "Keane", 0.06f, 1.6f);
        team2[2] = new Player(7f, 0.5f, 750, 200, 25, 5, 3, "Donovan", 0.05f, 1.1f);
        //Team 1 pictures
        player1 = new Image("player2.png");
        player2 = new Image("player1.png");
        player3 = new Image("player3.png");
        //Team 2 pictures
        player4 = new Image("player4.png");
        player5 = new Image("player5.png");
        player6 = new Image("player6.png");
    }
}
