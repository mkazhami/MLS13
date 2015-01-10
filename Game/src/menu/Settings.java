/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/**
 *
 * @author Mikhail
 */
public class Settings extends BasicGameState {

    public Settings(int state) {
    }
    //Declare variables
    Image background = null;
    SettingsButton settings = null;
    ExitButton back1 = null;
    ExitButton back2 = null;
    Boolean backFlag = false;
    Button instr1 = null;
    Button instr2 = null;
    Boolean instrFlag = false;
    Button soundon1 = null;
    Button soundon2 = null;
    Button soundoff1 = null;
    Button soundoff2 = null;
    Boolean soundFlag = false;
    Boolean onOff = true;

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        //Initialize images/buttons
        background = new Image("background.png");
        back1 = new ExitButton("back1.png");
        back2 = new ExitButton("back2.png");
        back2.resize(back1.getX() + 12, back1.getY() + 6, 1.25f);
        instr1 = new Button("instructions1.png");
        instr1.resize(150, 450, 1.4f);
        instr2 = new Button("instructions2.png");
        instr2.resize(instr1.getX() + 20, instr1.getY() + 5, 1.2f);
        soundon1 = new Button("soundon1.png");
        soundon1.resize(600, 450, 1.3f);
        soundon2 = new Button("soundon2.png");
        soundon2.resize(soundon1.getX() + 15, soundon1.getY() + 8, 1.1f);
        soundoff1 = new Button("soundoff1.png");
        soundoff1.resize(600, 450, 1.3f);
        soundoff2 = new Button("soundoff2.png");
        soundoff2.resize(soundoff1.getX() + 15, soundoff1.getY() + 8, 1.1f);
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        //Draw background and buttons - buttons change size when mouse is hovering
        background.draw();
        if (backFlag == true) {
            back2.draw(back2.getX(), back2.getY(), back2.getSize());
        } else {
            back1.draw(back1.getX(), back1.getY(), back1.getSize());
        }
        if (instrFlag == true) {
            instr2.draw(instr2.getX(), instr2.getY(), instr2.getSize());
        } else {
            instr1.draw(instr1.getX(), instr1.getY(), instr1.getSize());
        }
        if (soundFlag == true) {
            if (onOff == true) {
                soundon2.draw(soundon2.getX(), soundon2.getY(), soundon2.getSize());
            } else {
                soundoff2.draw(soundoff2.getX(), soundoff2.getY(), soundoff2.getSize());
            }
        } else {
            if (onOff == true) {
                soundon1.draw(soundon1.getX(), soundon1.getY(), soundon1.getSize());
            } else {
                soundoff1.draw(soundoff1.getX(), soundoff1.getY(), soundoff1.getSize());
            }
        }

    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        //Get mouse coordinates to determine which button the user presses.
        //Take action according to button pressed.
        Input input = gc.getInput();
        if (input.getMouseX() < back1.getWidth() + back1.getX() && input.getMouseX() > back1.getX() && input.getMouseY() < back1.getHeight() + back1.getY() && input.getMouseY() > back1.getY()) {
            backFlag = true;
            if (input.isMousePressed(0)) {
                sbg.enterState(0);
            }
        } else {
            backFlag = false;
        }
        if (input.getMouseX() < instr1.getWidth() + instr1.getX() && input.getMouseX() > instr1.getX() && input.getMouseY() < instr1.getHeight() + instr1.getY() && input.getMouseY() > instr1.getY()) {
            instrFlag = true;
            if (input.isMousePressed(0)) {
                sbg.enterState(5);
            }
        } else {
            instrFlag = false;
        }
        if (input.getMouseX() < soundon1.getWidth() + soundon1.getX() && input.getMouseX() > soundon1.getX() && input.getMouseY() < soundon1.getHeight() + soundon1.getY() && input.getMouseY() > soundon1.getY()) {
            soundFlag = true;
            if (input.isMousePressed(0)) {
                if (onOff == true) {
                    onOff = false;
                    Play.soundOn = false;
                } else {
                    onOff = true;
                    Play.soundOn = true;
                }
            }
        } else {
            soundFlag = false;
        }
    }

    public int getID() {
        return 3;
    }
}
