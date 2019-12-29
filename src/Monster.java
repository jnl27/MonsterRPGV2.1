
import java.util.Random;
public class Monster {
    private int monsterHP, monsterGold, maxHP;
    private int monsterDMGmin, monsterDMGmax;
    private String monsterName;


    public Monster(String name, int hp, int mindmg, int maxdmg, int gold){
        monsterName = name;
        monsterHP = hp;
        maxHP = hp;
        monsterDMGmax = maxdmg;
        monsterDMGmin = mindmg;
        monsterGold = gold;

    }

    public void takeDMG(int dmg){
        monsterHP-=dmg;

    }
    public int getHP(){
        return monsterHP;
    }
    public int getMaxHP() { return maxHP; }
    public String getName(){
        return monsterName;
    }
    public int getDMG(){
        Random r = new Random();
        return r.nextInt((monsterDMGmax - monsterDMGmin) + 1) + monsterDMGmin;
    }
    public int getMinDMG(){
        return monsterDMGmin;
    }
    public int getMaxDMG(){
        return monsterDMGmax;
    }
    public int getGold(){ return monsterGold;}
}
