/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import java.awt.Font;
import java.util.*;
import java.io.*;
import org.newdawn.slick.util.ResourceLoader;

/**
 *
 * @author Mikhail
 */
public class HighScores extends BasicGameState {

    public HighScores(int state) {
    }
    //Declare variables
    Scanner scan = null;
    PrintWriter write = null;
    Image background = null;
    ExitButton back1 = null;
    ExitButton back2 = null;
    boolean backFlag = false;
    public static boolean updateFlag = true;
    public static ArrayList<String> names = null;
    public static ArrayList<Integer> scores = null;
    public static String[] recordNames = {"Largest Win", "Largest Loss", "Most Scored", "Most Conceded", "Current Streak"};
    public static ArrayList<Integer> records;
    public static ArrayList<String> recordHolder = new ArrayList<String>();
    public static int largestWin;
    public static int largestLoss;
    public static int mostScored;
    public static int mostConceded;
    public static int currentStreak;
    TrueTypeFont font;
    TrueTypeFont font2;

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        //Load font and initialize variables
        Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
        font = new TrueTypeFont(awtFont, false);
        try {
            InputStream inputStream = ResourceLoader.getResourceAsStream("font.ttf");
            Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            awtFont2 = awtFont2.deriveFont(24f); // set font size
            font2 = new TrueTypeFont(awtFont2, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        background = new Image("background.png");
        back1 = new ExitButton("back1.png");
        back2 = new ExitButton("back2.png");
        back2.resize(back1.getX() + 15, back1.getY() + 5, 1.15f);
        names = new ArrayList<String>();
        scores = new ArrayList<Integer>();
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        //Draw background and box to hold scores
        background.draw();
        g.setColor(Color.black);
        //Box for records
        g.fillRect(400, 100, 550, 400);
        //Box for highscore
        g.fillRect(50, 100, 300, 400);
        g.setFont(font);
        g.setColor(Color.white);
        if (updateFlag == true) {
            updateHighscore();
            updateRecords();
        }
        //Fill box with names and scores - will be sorted already
        for (int count = 0; count < 10 && count < names.size(); count++) {
            g.drawString(names.get(count), 65, 110 + (count * 40));
            g.drawString(Integer.toString(scores.get(count)), 180, 110 + (count * 40));
        }
        for (int count = 0; count < records.size(); count++) {
            g.drawString(recordNames[count], 410, 160 + (count * 60));
            g.drawString(Integer.toString(records.get(count)), 600, 160 + (count * 60));
            if (count < 4) {
                g.drawString("by", 680, 160 + (count * 60));
                g.drawString(recordHolder.get(count), 710, 160 + (count * 60));
            }
        }
        if (backFlag == true) {
            back2.draw(back2.getX(), back2.getY(), back2.getSize());
        } else {
            back1.draw(back1.getX(), back1.getY(), back1.getSize());
        }
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();
        if (input.getMouseX() < back1.getWidth() + back1.getX() && input.getMouseX() > back1.getX() && input.getMouseY() < back1.getHeight() + back1.getY() && input.getMouseY() > back1.getY()) {
            backFlag = true;
            if (input.isMousePressed(0)) {
                sbg.enterState(0);
            }
        } else {
            backFlag = false;
        }
        //If screen was just opened, update highscores. This allows the user
        //to check if their score made the list right after they play, as opposed
        //to closing the program and opening up again to have the list update.
        //UpdateFlag starts at true when this state is entered.    
    }

    public int getID() {
        return 6;
    }

    //Bubble sort method to sort the scores and place names accordingly
    public void sort(ArrayList<Integer> list, ArrayList<String> list2) {
        for (int i = 1; i < list.size(); i++) {
            for (int j = 0; j < list.size() - i; j++) {
                if (list.get(j) < list.get(j + 1)) {
                    String temp2 = list2.get(j);
                    list2.set(j, list2.get(j + 1));
                    list2.set(j + 1, temp2);
                    int temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }

    //Method to update the highscore list with any changes.
    //I'm assuming we make a goal 100 points, a goal against -10 points, and then
    //we add it all up in the end to get a total score. This is independent of
    //the records list/
    public void updateHighscore() {
        try {
            scan = new Scanner(new FileReader("highscores.txt"));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        names = new ArrayList<String>();
        scores = new ArrayList<Integer>();
        while (scan.hasNext()) {
            names.add(scan.next());
            scores.add(scan.nextInt());
        }
        sort(scores, names);
        updateFlag = false;
    }

    //Method to update the records list with any changes
    //Basic records: best/worst goal difference, most goals for/against, current
    //streak. Will also record which user (by name) got the record.
    public void updateRecords() {
        try {
            scan = new Scanner(new FileReader("records.txt"));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        //Scan in current records
        largestWin = scan.nextInt();
        recordHolder.add(0, scan.nextLine());
        largestLoss = scan.nextInt();
        recordHolder.add(1, scan.nextLine());
        mostScored = scan.nextInt();
        recordHolder.add(2, scan.nextLine());
        mostConceded = scan.nextInt();
        recordHolder.add(3, scan.nextLine());
        currentStreak = scan.nextInt();
        //Store in array
        records = new ArrayList<Integer>();
        records.add(0, largestWin);
        records.add(1, largestLoss);
        records.add(2, mostScored);
        records.add(3, mostConceded);
        records.add(4, currentStreak);
    }
}
