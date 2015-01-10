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
public class SettingsButton extends Button{

    public SettingsButton(String name) throws SlickException{
        super(name);
        this.buttonX = 385;
        this.buttonY = 485;
        this.size = 1.5f;
    }
}
