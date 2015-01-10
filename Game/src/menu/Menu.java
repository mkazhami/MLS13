/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import org.newdawn.slick.*;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.*;
import java.awt.Font;
import java.util.*;
import java.io.*;

public class Menu extends BasicGameState {

    public Menu(int state) {
    }
    //Declare variables
    Scanner scan = null;
    StartButton start1 = null;
    StartButton start2 = null;
    SettingsButton settings1 = null;
    SettingsButton settings2 = null;
    ExitButton exit1 = null;
    ExitButton exit2 = null;
    ScoresButton scores1 = null;
    ScoresButton scores2 = null;
    Image background = null;
    boolean startFlag = false;
    boolean settingsFlag = false;
    boolean exitFlag = false;
    boolean scoresFlag = false;
    int frameCount = 0;
    Image base = null;
    Image player = null;
    float playerMoveX = 50;
    Image ball = null;
    float ballMoveX = 0;
    float ballMoveY = 400;
    TextField textBox;
    TrueTypeFont font;
    public static String name;

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        //Initialize images/buttons
        start1 = new StartButton("start1.png");
        start2 = new StartButton("start2.png");
        start2.resize(start1.getX() + 15, start1.getY() + 6, 0.9f);
        settings1 = new SettingsButton("settings1.png");
        settings2 = new SettingsButton("settings2.png");
        settings2.resize(settings1.getX() + 20, settings1.getY() + 8, 1.25f);
        exit1 = new ExitButton("exit1.png");
        exit2 = new ExitButton("exit2.png");
        exit2.resize(exit1.getX() + 15, exit1.getY() + 6, 1.25f);
        scores1 = new ScoresButton("highscores1.png");
        scores2 = new ScoresButton("highscores2.png");
        scores2.resize(scores1.getX() + 20, scores1.getY() + 6, 1.25f);
        background = new Image("background.png");
        base = new Image("base.png");
        ball = new Image("awful.png");
        player = new Image("player.png");
        Font awtFont = new Font("Verdana", Font.BOLD, 24);
        font = new TrueTypeFont(awtFont, false);
        this.textBox = new TextField(gc, this.font, 30, 60, 200, 50);
        this.textBox.setBackgroundColor(Color.white);
        this.textBox.setBorderColor(Color.black);
        this.textBox.setTextColor(Color.blue);
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        //While intro is playing
        if (frameCount < 270) {
            base.draw();
            player.draw(playerMoveX, 80, 2f);
            ball.draw(ballMoveX, ballMoveY, 3f);
            frameCount += 1;
        } //After intro is finished, show main menu. Flags are used to determine if
        //the mouse is hovering over the button, causing it to change size.
        else {
            background.draw();
            if (startFlag == false) {
                start1.draw(start1.getX(), start1.getY(), start1.getSize());
            } else {
                start2.draw(start2.getX(), start2.getY(), start2.getSize());
            }
            if (settingsFlag == false) {
                settings1.draw(settings1.getX(), settings1.getY(), settings1.getSize());
            } else {
                settings2.draw(settings2.getX(), settings2.getY(), settings2.getSize());
            }
            if (exitFlag == false) {
                exit1.draw(exit1.getX(), exit1.getY(), exit1.getSize());
            } else {
                exit2.draw(exit2.getX(), exit2.getY(), exit2.getSize());
            }
            if (scoresFlag == false) {
                scores1.draw(scores1.getX(), scores1.getY(), scores1.getSize());
            } else {
                scores2.draw(scores2.getX(), scores2.getY(), scores2.getSize());
            }

            textBox.render(gc, g);
            textBox.setFocus(true);
            g.setFont(font);
            g.drawString("NAME", 85, 20);
        }

    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        if (frameCount == 1) {
            addRecords();
        }
        //For the intro - change values to make images move
        if (frameCount < 270) {
            if (frameCount < 100) {
                playerMoveX += 1.5;
                ballMoveX += 0.75;
                ballMoveY -= 3.2;
            } else if (frameCount < 270 && frameCount > 100) {
                ballMoveX += 3.9;
                ballMoveY += 2.3;
            }
        } else {
            //Get mouse coordinates to determine which button the user presses.
            //Once a button is pressed, change to the appropriate screen.
            Input input = gc.getInput();
            if ((input.getMouseX() <= start1.getWidth() + start1.getX() && input.getMouseX() >= start1.getX()) && (input.getMouseY() <= start1.getY() + start1.getHeight() && input.getMouseY() >= start1.getY())) {
                startFlag = true;
                if (input.isMousePressed(0)) {
                    sbg.enterState(4);
                    /////////////ENTER GAME/////////////////////
                }
            } else {
                startFlag = false;
            }
            if ((input.getMouseX() <= settings1.getWidth() + settings1.getX() && input.getMouseX() >= settings1.getX()) && (input.getMouseY() <= settings1.getY() + settings1.getHeight() && input.getMouseY() >= settings1.getY())) {
                settingsFlag = true;
                if (input.isMousePressed(0)) {
                    sbg.enterState(3);
                    /////////////ENTER SETTINGS MENU//////////////////
                }
            } else {
                settingsFlag = false;
            }
            if ((input.getMouseX() <= exit1.getWidth() + exit1.getX() && input.getMouseX() >= exit1.getX()) && (input.getMouseY() <= exit1.getY() + exit1.getHeight() && input.getMouseY() >= exit1.getY())) {
                exitFlag = true;
                if (input.isMousePressed(0)) {
                    gc.exit();
                    /////////////EXIT GAME//////////////
                }
            } else {
                exitFlag = false;
            }
            if ((input.getMouseX() <= scores1.getWidth() + scores1.getX() && input.getMouseX() >= scores1.getX()) && (input.getMouseY() <= scores1.getY() + scores1.getHeight() && input.getMouseY() >= scores1.getY())) {
                scoresFlag = true;
                if (input.isMousePressed(0)) {
                    HighScores.updateFlag = true;
                    sbg.enterState(6);
                    /////////////ENTER HIGH SCORES MENU//////////////
                }
            } else {
                scoresFlag = false;
            }
            if (textBox.getText().trim().equals("")) {
                name = " unknown";
            } else {
                name = " " + textBox.getText();
            }
        }
    }

    public void addRecords() {
        try {
            scan = new Scanner(new FileReader("records.txt"));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        //Scan in current records
        if (scan.hasNext()) {
            HighScores.largestWin = scan.nextInt();
            HighScores.recordHolder.add(0, scan.nextLine());
            HighScores.largestLoss = scan.nextInt();
            HighScores.recordHolder.add(1, scan.nextLine());
            HighScores.mostScored = scan.nextInt();
            HighScores.recordHolder.add(2, scan.nextLine());
            HighScores.mostConceded = scan.nextInt();
            HighScores.recordHolder.add(3, scan.nextLine());
            HighScores.currentStreak = scan.nextInt();
        }

        HighScores.records = new ArrayList<Integer>();
        HighScores.records.add(0, HighScores.largestWin);
        HighScores.records.add(1, HighScores.largestLoss);
        HighScores.records.add(2, HighScores.mostScored);
        HighScores.records.add(3, HighScores.mostConceded);
        HighScores.records.add(4, HighScores.currentStreak);
    }

    public int getID() {
        return 0;
    }
}
