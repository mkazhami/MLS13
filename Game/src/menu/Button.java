/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package menu;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Mikhail
 */
public class Button{
    Image button;
    float buttonX;
    float buttonY;
    float size;


    public Button(String name) throws SlickException{
        button = new Image(name);
    }

    public void setX(float x){
        this.buttonX = x;
    }

    public void setY(float y){
        this.buttonY = y;
    }

    public void setSize(float s){
        this.size = s;
    }

    public float getX(){
        return this.buttonX;
    }

    public float getY(){
        return this.buttonY;
    }

    public float getSize(){
        return this.size;
    }

    public float getWidth(){
        return button.getWidth()*size;
    }

    public float getHeight(){
        return button.getHeight()*size;
    }

    public void resize(float x, float y, float s){
        setX(x);
        setY(y);
        setSize(s);
    }


    public void draw(float x, float y, float scale){
        button.draw(x, y, scale);
    }


}
