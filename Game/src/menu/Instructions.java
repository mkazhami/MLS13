/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/**
 *
 * @author kazhm3645
 */
public class Instructions extends BasicGameState {

    public Instructions(int state) {
    }
    Image background = null;
    ExitButton back1 = null;
    ExitButton back2 = null;
    Boolean backFlag = false;
    Image instructions = null;

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        background = new Image("background.png");
        back1 = new ExitButton("back1.png");
        back2 = new ExitButton("back2.png");
        instructions = new Image("instructions.png");
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        background.draw();
        instructions.draw(100, 100);
        if (backFlag == true) {
            back2.draw(back2.getX(), back2.getY(), back2.getSize());
        } else {
            back1.draw(back1.getX(), back1.getY(), back1.getSize());
        }
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();
        if (input.getMouseX() < back1.getWidth() + back1.getX() && input.getMouseX() > back1.getX() && input.getMouseY() < back1.getHeight() + back1.getY() && input.getMouseY() > back1.getY()) {
            back2.resize(back1.getX() + 17, back1.getY() + 5, 1f);
            backFlag = true;
            if (input.isMousePressed(0)) {
                sbg.enterState(3);
            }
        }
        else{
            backFlag = false;
        }
    }

    public int getID() {
        return 5;
    }
}
