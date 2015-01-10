/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import java.io.*;

/**
 *
 * @author kazhm3645
 */
public class SelectTeams extends BasicGameState {

    public SelectTeams(int state) {
    }

    //Declare variables
    PrintWriter print = null;
    Image background = null;
    Image teams = null;
    ExitButton back1 = null;
    ExitButton back2 = null;
    Button select1 = null;
    Button select2 = null;
    Button select3 = null;
    Boolean backFlag = false;

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        //Initialize mages/buttons
        background = new Image("background.png");
        teams = new Image("teams.png");
        back1 = new ExitButton("back1.png");
        back1.setX(420);
        back2 = new ExitButton("back2.png");
        back2.resize(back1.getX() + 12, back1.getY() + 6, 1.25f);
        select1 = new Button("select1.png");
        select1.resize(back1.getX() - 180, back1.getY() - 70, 0.95f);
        select2 = new Button("select1.png");
        select2.resize(select1.getX() + 370, select1.getY(), select1.getSize());
        select3 = new Button("select1.png");
        select3.resize(800, 600, 0.95f);
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        //Draw background and teams picture - do not change
        background.draw();
        teams.draw(160, 70, 1.1f);
        //If mouse is hovering over button, change its size
        if(backFlag == true){
            back2.draw(back2.getX(), back2.getY(), back2.getSize());
        }
        else{
            back1.draw(back1.getX(), back1.getY(), back1.getSize());
        }
        //Draw select buttons - do not change (yet)
        select1.draw(select1.getX(), select1.getY(), select1.getSize());
        select2.draw(select2.getX(), select2.getY(), select2.getSize());
        select3.draw(select3.getX(), select3.getY(), select3.getSize());
        g.drawString("Two Player", select3.getX() + 35, select3.getY()-20);

    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        //Get mouse coordinates to determine which button the use presses.
        //Once a button is pressed, change to the appropriate screen.
        Input input = gc.getInput();
        if (input.getMouseX() < back1.getWidth() + back1.getX() && input.getMouseX() > back1.getX() && input.getMouseY() < back1.getHeight() + back1.getY() && input.getMouseY() > back1.getY()) {
            backFlag = true;
            if (input.isMousePressed(0)) {
                sbg.enterState(0);
            }
        }
        else{
            backFlag = false;
        }
        if (input.getMouseX() < select1.getX() + select1.getWidth() && input.getMouseX() > select1.getX() && input.getMouseY() < select1.getHeight() + select1.getY() && input.getMouseY() > select1.getY()) {
            if (input.isMousePressed(0)) {
                Play.teamc = 0;
                sbg.enterState(1);
            }
        }
        if (input.getMouseX() < select2.getX() + select2.getWidth() && input.getMouseX() > select2.getX() && input.getMouseY() < select2.getHeight() + select2.getY() && input.getMouseY() > select2.getY()) {
            if (input.isMousePressed(0)) {
                Play.teamc = 1;
                sbg.enterState(1);
            }
        }
        if (input.getMouseX() < select3.getX() + select3.getWidth()&& input.getMouseX() > select3.getX()&& input.getMouseY() < select3.getHeight() + select3.getY() && input.getMouseY() > select3.getY()) {
            if (input.isMousePressed(0)) {
                sbg.enterState(2);
            }
        }

    }

    public int getID() {
        return 4;
    }
}
