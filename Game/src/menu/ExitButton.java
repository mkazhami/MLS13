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
public class ExitButton extends Button{

    public ExitButton(String name) throws SlickException{
        super(name);
        this.buttonX = 410;
        this.buttonY = 590;
        this.size = 1.6f;
    }
}
