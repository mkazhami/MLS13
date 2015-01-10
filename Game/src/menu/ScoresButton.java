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
public class ScoresButton extends Button{

    public ScoresButton(String name) throws SlickException{
        super(name);
        this.buttonX = 385;
        this.buttonY = 370;
        this.size = 1.5f;
    }
}
