
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Player{
    private int playerHP;
    private static int maxHP = 50;
    private int playerDMGmin, playerDMGmax;
    private int totalDMGtaken, totalDMGdealt;
    private String playerName;
    private int playerGold;
    private int heals;
    private int bombs;
    private ArrayList<String> attackNames = new ArrayList<String>();
    private ArrayList<String> healNames = new ArrayList<String>();
    private ArrayList<String> bombNames = new ArrayList<String>();




    public Player(){
        playerName = "";
        playerDMGmin = 1;
        playerDMGmax = 10;
        heals = 3; bombs = 0;
        playerHP = 50; playerGold = 0;
        totalDMGtaken = 0; totalDMGdealt = 0;

        attackNames.add("Effective writing utensils");
        attackNames.add("Graphing Calculator");
        attackNames.add("Top tier eraser");
        attackNames.add("Java quick reference");
        attackNames.add("Sharpened #2 pencil");
        attackNames.add("Yeet");
        attackNames.add("Process of elimination");
        attackNames.add("Critical thinking");
        attackNames.add("Ask Mrs. Jessu for help");

        healNames.add("Good night's sleep");
        healNames.add("a good breakfast");
        healNames.add("Kickstart");
        healNames.add("a nice cup of coffee");
        healNames.add("a refreshing cup of iced tea");

        bombNames.add("SLADER");
        bombNames.add("GITHUB");
        bombNames.add("STACK OVERFLOW");
        bombNames.add("JAVA API");
        bombNames.add("KNOWLEDGE");
        bombNames.add("Straight up GOOGLE");

    }

    public String getName(){
        return playerName;
    }
    public int getHP(){
        return playerHP;
    }
    public int getMaxHP() { return maxHP; }
    public int getGold(){
        return playerGold;
    }
    public int getDMG(){
        Random r = new Random();
        return r.nextInt((playerDMGmax - playerDMGmin) + 1) + playerDMGmin;
    }
    public int getBombs() { return bombs; }
    public int getHeals() { return heals; }
    public String getAttackName() {
        Random rand = new Random();
        int picker = rand.nextInt(attackNames.size());
        return attackNames.get(picker);
    }
    public String getHealName(){
        Random rand = new Random();
        int picker = rand.nextInt(healNames.size());
        return healNames.get(picker);
    }
    public String getBombName(){
        Random rand = new Random();
        int picker = rand.nextInt(bombNames.size());
        return bombNames.get(picker);
    }
    public String getIntel(){
        if (playerDMGmax==20){
            return "Uber-intelligent youngster";
        }else{
            return "Average smarts youngin";
        }
    }
    public String getStam(){
        if (maxHP==100){
            return "Hella Stamina";
        }else{
            return "Average Stamina";
        }
    }
    public int getTotalDMGtaken(){
        return totalDMGtaken;
    }
    public int getTotalDMGdealt(){
        return totalDMGdealt;
    }

    public void setName(String name){
        playerName = name;
    }
    public void useHeal(){
        playerHP+=30;
        if (playerHP>maxHP){
            playerHP=maxHP;
        }
        heals--;

    }
    public void changeGold(int gold){
        playerGold+=gold;
    }
    public void addHeals(int healNum){
        heals+=healNum;
    }
    public void addBomb(int bombNum){
        bombs+=bombNum;
    }


    public void useBomb(){
        bombs--;
        totalDMGdealt+=Game.bombDmg;
    }



    public void takeDMG(int dmg){
        playerHP-=dmg;
        totalDMGtaken+=dmg;
    }
    public void addTotalDMGdealt(int dmg){
        totalDMGdealt+=dmg;
    }

    public void upgradeIntel(int newMin, int newMax){
        playerDMGmin = newMin;
        playerDMGmax = newMax;
    }
    public void upgradeStamina(int newHP){
        playerHP = newHP;
        maxHP = newHP;
    }


}
