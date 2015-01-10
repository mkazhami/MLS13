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
public class Game extends StateBasedGame{
    //THIS CLASS SETS THE STATES FOR EACH OF THE DIFFERENT MENUS AND CREATES SCREEN

    public static final String gamename = "MLS 13";
    public static final int menu = 0;
    public static final int play = 1;
    public static final int twoplayer = 2;
    public static final int settings = 3;
    public static final int selectteams = 4;
    public static final int instructions = 5;
    public static final int highscores = 6;

    public Game(String gamename){
        super(gamename);
        this.addState(new Menu(menu));
        this.addState(new Play(play));
        this.addState(new TwoPlayer(twoplayer));
        this.addState(new Settings(settings));
        this.addState(new SelectTeams(selectteams));
        this.addState(new Instructions(instructions));
        this.addState(new HighScores(highscores));
    }

    public void initStatesList(GameContainer gc) throws SlickException{
        this.getState(menu).init(gc, this);
        this.getState(play).init(gc, this);
        this.getState(twoplayer).init(gc, this);
        this.getState(settings).init(gc, this);
        this.getState(selectteams).init(gc, this);
        this.getState(instructions).init(gc, this);
        this.getState(highscores).init(gc, this);
        this.enterState(menu);
    }

    public static void main(String[] args) {        
        try{
            AppGameContainer appgc;
            appgc = new AppGameContainer(new Game(gamename));
            appgc.setDisplayMode(1000, 700, false);
            appgc.setTargetFrameRate(60);
            appgc.start();
        }
        catch(SlickException e){
            e.printStackTrace();
        }
    }

}
