package swingy.model;

import java.util.ArrayList;
import java.util.List;

public class Hero {

    private Stats               stats;
    private String              name;
    private String              heroClass;
    private List<String>        backPack = new ArrayList<>();
    private List<String>        equipped = new ArrayList<>();

    public Stats getStats() {
        return stats;
    }

    public String getName() {
        return name;
    }

    public String getHeroClass() {
        return heroClass;
    }

    public Hero(String name, String heroClass) {
        this.name = name;
        this.heroClass = heroClass;
        InitializeStats(heroClass);
    }

    public void InitializeStats(String heroClass){

        String str = heroClass.toLowerCase();
        if (str.equals("normie")){
            this.stats = new Stats(125,20,5,0,1);
        }
        else if (str.equals("weeb")){
            this.stats = new Stats(145,15,10,0,1);
        }
        else if (str.equals("otaku")){
            this.stats = new Stats(80,35,7,0,1);
        }
        else if (str.equals("MethHead")){
            this.stats = new Stats(100,25,0,500,1);
        }
        else {
            System.out.println("Invalid Class");
        }
    }

    public void initializeSave(int armor, int AtkDmg, int HP, int lvl, int xp, String name, String Class, String[] backpack, String[] equipment){
        System.out.println("Loading last save...");
        this.stats = new Stats(HP, AtkDmg, armor, xp, lvl);
        this.name = name;
        this.heroClass = Class;
        int i = 0;
        while (backpack[i] != null) {
            updateBackPack(backpack[i]);
            i++;
        }
        i = 0;
        while (equipment[i] != null){
            equipItem(equipment[i]);
            i++;
        }
        System.out.println("hero loaded!");
    }

    public void updateBackPack(String item){
        System.out.println(item);
        this.backPack.add(item);
    }

    public void removeItem(String item){
        this.backPack.remove(item);
    }

    public void equipItem(String item){
        this.equipped.add(item);
    }

    public void unequipItem(String item){
        this.equipped.remove(item);
    }

    public List<String> getBackPack() {
        return backPack;
    }

    public List<String> getEquipped() {
        return equipped;
    }


    public void updateConditions(long dmg, long healing, int xp) {
        System.out.println("xpbar: "+stats.getXpBar());

        this.stats = new Stats(
                stats.getHP() - (dmg - healing),
                stats.getAtkDmg(),
                stats.getArmor(),
                stats.getXp() + xp,
                stats.getLevel()
        );

        if (stats.getXp() >= stats.getXpBar()) {
            this.stats = new Stats(
                    stats.getHP(),
                    stats.getAtkDmg(),
                    stats.getArmor(),
                    stats.getXp() - stats.getXpBar(),
                    stats.getLevel() + 1);
        }
    }

}


class Stats {

    private long    HP;
    private long    AtkDmg;
    private int     Armor;
    private int     xp;
    private int     xpBar;
    private int     Level;

    Stats(long HP, long AtkDmg, int Armor, int xp, int Level) {
        this.HP = HP;
        this.AtkDmg = AtkDmg;
        this.Armor = Armor;
        this.Level = Level;
        this.xpBar = Level * 1000 + (int)Math.pow(Level - 1, 2) * 450;
        this.xp = xp;
    }

    public int getXpBar() {
        return this.xpBar;
    }

    public long getHP() {
        return HP;
    }

    public long getAtkDmg() {
        return AtkDmg;
    }

    public int getArmor() {
        return Armor;
    }

    public int getXp() {
        return xp;
    }

    public int getLevel() {
        return Level;
    }
}
