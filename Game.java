import java.util.*;

public class Game {

    public static boolean start = false;
    public static int healCost = 15;
    public static int bombCost = 10;
    public static int intelCost = 125;
    public static int stamCost = 150;
    public static int bombDmg = 20;

    public static void fight(Player player, Monster monster){
        println("Your next enemy is: The "+ monster.getName()+"! It deals " + monster.getMinDMG() + " to " + monster.getMaxDMG() + " damage and has " + monster.getHP() + " HP!");
        delay(1000);
        println("");

        while (player.getHP()>0 && monster.getHP()>0){
            boolean typo = true;
            boolean itemEsc = false;
            Scanner n = new Scanner(System.in);
            println("Your HP: " + player.getHP() + "/" + player.getMaxHP());
            println(monster.getName()+"'s HP: " + monster.getHP() + "/" + monster.getMaxHP());
            while (typo) {
                print("What do you do? [[A] attack, [I] items, [D] drop out]");
                println("");
                String choice = n.next();
                delay(300);
                //PLAYER's TURN
                if (choice.toLowerCase().equals("attack") || choice.toLowerCase().equals("a")) { //attack
                    int dmg = player.getDMG();
                    player.addTotalDMGdealt(dmg);
                    println(player.getName() + " attacked! ");
                    delay(1000);
                    println(player.getName() + " used " + player.getAttackName() + "! It did " + dmg + " damage! ");
                    delay(1000);
                    monster.takeDMG(dmg);
                    typo = false;
                    if (monster.getHP() > 0) {
                        println("The " + monster.getName() + " has " + monster.getHP() + " HP left!");
                        println("");
                        delay(1500);
                    }
                } else if (choice.toLowerCase().equals("items") || choice.toLowerCase().equals("i")) { //items
                    Scanner i = new Scanner(System.in);
                    println("");
                    while (!itemEsc){
                        print("Which item would you like to use? ");
                        delay(300);
                        println("You currently have:\n\t [H] Heals: " + player.getHeals() + "\n\t [B] Bombs: " + player.getBombs() + "\n\t [E] Exit Item Menu");
                        String itemchoice = i.nextLine();
                        delay(300);
                        if (itemchoice.toLowerCase().equals("h") || itemchoice.toLowerCase().equals("heals")) {
                            if (player.getHeals() > 0) {
                                println("You used a heal!");
                                delay(1000);
                                println(player.getName() + " used " + player.getHealName());
                                delay(1000);
                                player.useHeal();
                                println("You healed yourself for 30 HP");
                                delay(1000);
                                println("Your HP is now " + player.getHP());
                                println("");
                                delay(1500);
                                typo = false;
                                itemEsc = true;
                            } else {
                                println("You have no more heals left!!");
                                delay(1000);
                                println("");
                                typo = false;
                                itemEsc = true;
                            }
                        } else if (itemchoice.toLowerCase().equals("b") || itemchoice.toLowerCase().equals("bombs")) {
                            if (player.getBombs() > 0) {
                                println(player.getName() + " used a bomb!");
                                delay(1000);
                                println(player.getName() + " used " + player.getBombName());
                                delay(1000);
                                println("BOOM");
                                delay(1000);
                                player.useBomb();
                                monster.takeDMG(bombDmg);
                                println("The bomb did " + bombDmg + " damage! ");
                                delay(1000);
                                if (monster.getHP() > 0) {
                                    println(monster.getName() + " has " + monster.getHP() + " HP left!");
                                    delay(1000);
                                }
                                println("You have " + player.getBombs() + " bombs left!");
                                println("");
                                delay(1500);
                                typo = false;
                                itemEsc = true;
                            } else {
                                println("You have no more bombs!! ");
                                delay(1500);
                                println("");
                                typo = false;
                                itemEsc = true;
                            }
                        }else if (itemchoice.toLowerCase().equals("e") || itemchoice.toLowerCase().equals("exit item menu")){
                            itemEsc = true;
                        }
                        else{
                            println("Invalid selection. Please try again.");
                            delay(1000);
                            println("");
                        }

                    }
                } else if (choice.toLowerCase().equals("drop out") || choice.toLowerCase().equals("d")) { //give up
                    println("You chose to drop out. Maybe another time. ");
                    delay(300);
                    player.takeDMG(50);
                    gameOver();

                }
                else{
                    println("Invalid selection. Try again" );
                    delay(1000);
                }
            }
            //Check if monster is still alive. If it is, then monster's turn
            if (monster.getHP()<=0){
                println("You have defeated the " + monster.getName() + "!!");
                delay(2000);
                println("");
                println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                delay(1000);
                println(player.getName() + " earned " + monster.getGold() + " Gold!");
                player.changeGold(monster.getGold());
                delay(1000);
                println("You now have " + player.getGold() + " Gold");
                delay(1000);
                println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                println("");
            }
            if (monster.getHP()>0){
                println("The " + monster.getName() + " attacked! ");
                delay(1000);
                int dmg = monster.getDMG();
                println("Ouch! " + player.getName() + " took " + dmg + " damage!!");
                delay(1000);
                player.takeDMG(dmg);
                if (player.getHP()>0) {
                    println(player.getName() + " has " + player.getHP() + " HP left");
                    println("");
                    delay(1000);
                }
                println("");

            }


        }
        if (player.getHP()<=0){
            println("You have been defeated by the " + monster.getName());
            delay(1000);
            println("As a result, you failed the class, and was subsequently expelled from the school for this year. Good luck next time!");
            gameOver();
        }
    }

    public static void shop(Player player){
        boolean itemtypo = true;
        Scanner s = new Scanner(System.in);
        println("The elusive Student Trader has appeared!");
        println("");
        delay(1000);
        println("Would you like to go shopping? [y/n]");
        if (s.next().toLowerCase().equals("y")){
            while (itemtypo) {
                Scanner y = new Scanner(System.in);
                println("Which item would you like to buy? \n\t [H] Heals (restores 30HP) - 15 gold each \n\t [X] Bombs (instantly deal 20 damage) - 10 gold each \n\t [I] Upgrade Intelligence (upgrades dmg to 5-20) - 125 gold \n\t [S] Upgrade Stamina (upgrades HP to 100) - 150 gold \n\t [E] Exit Shop");
                String shopChoice = y.nextLine();
                if (shopChoice.toLowerCase().equals("h")) {
                    Scanner x = new Scanner(System.in);
                    println("How many heals would you like to buy? ");
                    int healNum = x.nextInt();
                    if (player.getGold() >= healNum * healCost) {
                        println("You bought " + healNum + " heals!");
                        delay(1000);
                        player.changeGold(-healNum * healCost);
                        player.addHeals(healNum);
                        println("You now have " + player.getHeals() + " heals.");
                        delay(800);
                        println("You have " + player.getGold() + " Gold left.");
                    } else {
                        println("You don't have enough Gold for that!");
                        delay(1000);
                        println("");
                    }
                } else if (shopChoice.toLowerCase().equals("x")) {
                    Scanner x = new Scanner(System.in);
                    println("How many bombs would you like to buy? ");
                    int bombNum = x.nextInt();
                    if (player.getGold() >= bombNum * bombCost) {
                        println("You bought " + bombNum + " bombs!");
                        delay(1000);
                        player.changeGold(-bombNum * bombCost);
                        player.addBomb(bombNum);
                        println("You now have " + player.getBombs() + " Bombs.");
                        delay(800);
                        println("You have " + player.getGold() + " Gold left.");
                    } else {
                        println("You don't have enough Gold for that!");
                        delay(1000);
                        println("");
                    }
                }else if (shopChoice.toLowerCase().equals("i")){
                    if (player.getGold() >= intelCost){
                        println("You upgraded your Intelligence! ");
                        delay(1000);
                        player.changeGold(-intelCost);
                        println("Your attacks now deal 10-30 damage!");
                        player.upgradeIntel(10, 30);
                        delay(1000);
                        println("You have " + player.getGold() + " Gold left.");
                    }else{
                       println("You don't have enough Gold for that!");
                       delay(1000);
                       println("");
                    }
                }else if (shopChoice.toLowerCase().equals("s")){
                    if (player.getGold() >= stamCost){
                        println("You upgraded your Stamina! ");
                        delay(1000);
                        player.changeGold(-stamCost);
                        println("Your max HP has been upgraded to 100! By upgrading, you have also restored your HP to max!");
                        player.upgradeStamina(100);
                        delay(1000);
                        println("You have " + player.getGold() + " Gold left.");
                    }else{
                        println("You don't have enough Gold for that!");
                        delay(1000);
                        println("");
                    }
                }
                else if (shopChoice.toLowerCase().equals("e")) {
                    itemtypo = false;
                }else{
                    println("Invalid selection, please try again.");
                    delay(1000);
                }
                Scanner z = new Scanner(System.in);
                println("Would you like to continue shopping? [y/n] ");
                if (z.next().toLowerCase().equals("n")){
                    itemtypo = false;
                }else{
                    itemtypo = true;
                }
            }
        }

    }

    public static void gameStart(Player player){

        boolean typo = true;
        println("Welcome to Student Simulator 2018! Your goal is to survive the year and eliminate all 'monsters' you encounter! Good luck!");
        Scanner p = new Scanner(System.in);
        println("");
        delay(1000);
        while (typo == true) {
            print("You may quit and leave now before your suffering for the school year 2018 begins. Do you wish to continue? [y/n] ");
            if (p.next().toLowerCase().equals("y")) {
                println("Nice choice. Good luck out there.");
                println("Note: you can always drop out whenever you like. Don't push yourself too hard :)");
                start = true;
                typo = false;
                delay(300);
                println("");println("");
                println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~GAME START~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                println("");
                println("");
                delay(1000);

                Scanner j = new Scanner(System.in);
                print("What shall your name be? ");
                player.setName(j.nextLine());
                delay(500);
                println("Welcome Student " + player.getName());
                delay(1000);
                println("");
                println("");
            } else if (p.next().toLowerCase().equals("n")) {
                println("Respectable. Now dropping out of *Insert school name here*... ");
                println("");
                typo = false;
                delay(300);
                println("");
                gameOver();
            } else {
                println("Incorrect option. Try again lol");
                println("");
                delay(300);
            }
        }



    }

    public static void main(String[] args){
        Player player = new Player();
        gameStart(player);
        Monster preTest = new Monster("Pre test", 10, 1, 10, 25);
        fight(player, preTest);

        Monster quiz = new Monster("Quiz #1", 15, 1, 12, 30);
        fight(player, quiz);

        Monster test1 = new Monster("First Test", 20, 5, 10, 40);
        fight(player, test1);

        shop(player);

        Monster quarter = new Monster("Quarterly", 40, 1, 10, 35);
        fight(player, quarter);

        Monster midterm = new Monster("Midterm", 25, 7, 20, 50);
        fight(player, midterm);

        shop(player);

        Monster quiz2 = new Monster("Quiz V2", 30, 5, 10, 75);
        fight(player, quiz2);

        Monster test2 = new Monster("Test Mk II", 50, 5, 10, 71);
        fight(player, test2);

        shop(player);

        Monster ap = new Monster("AP TEST", 80, 1, 7, 100);
        fight(player, ap);

        shop(player);

        Monster fin = new Monster("FINAL", 100, 10, 20, 200);
        fight(player, fin);

        gameComplete(player);
    }

    public static void print(String s){ //wrapper methods to make tedious things less tedious
        System.out.print(s);
    }
    public static void println(String s){
        System.out.println(s);
    }
    public static void delay(int ms){
        try {
            Thread.sleep(ms);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void gameOver(){
        println("");
        println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~GAME OVER~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        println("");
        println("");
        System.exit(1);
    }
    public static void gameComplete(Player player){
        println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        println("");
        println("Congratulations!! You have usurped all academic challenges that awaited you, and passed the year. Well done!");
        delay(1000);
        println("Stats:\n\n\t Name: " + player.getName() + "\n\t Intelligence: " + player.getIntel() + "\n\t Stamina: " + player.getStam() + "\n\t Total damage dealt: " + player.getTotalDMGdealt() + "\n\t Total damage taken: " + player.getTotalDMGtaken() + "\n\t Final Gold Total: " + player.getGold());
    }
}








