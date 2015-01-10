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
public class SelectButton extends Button{

     public SelectButton(String name) throws SlickException{
        super(name);
        this.buttonX = 192;
        this.buttonY = 445;
        this.size = (float) 0.8;
    }
}
