/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package menu;

import org.newdawn.slick.SlickException;

/**
 *
 * @author Mikhail
 */
public class StartButton extends Button{

    public StartButton(String name) throws SlickException{
        super(name);
        this.buttonX = 405;
        this.buttonY = 250;
        this.size = 1.05f;
    }
}
