package hokan.closethebreach.creatures;

import java.util.ArrayList;

import hokan.closethebreach.utils.Job;
import hokan.closethebreach.utils.Power;

/**
 * Created by Utilisateur on 14/11/2015.
 */
public class Hero {
    protected String name;
    protected int image;
    protected Job job;
    protected int armorClass;
    protected int hp;
    protected int vitesse;
    protected int respawn;
    protected ArrayList<Monster> monsters;
    protected ArrayList<Power> powers;
    protected boolean levelTwo;
    protected boolean enable;

    public Hero(String name, int image, Job job, int armorClass, int hp,
                int vitesse, int respawn) {
        this.name = name;
        this.image = image;
        this.job = job;
        this.armorClass = armorClass;
        this.hp = hp;
        this.vitesse = vitesse;
        this.respawn = respawn;
        monsters = new ArrayList<>();
        powers = new ArrayList<>(6); //un personnage n'a jamais plus de 6 pouvoirs
        levelTwo = false;
        enable = true;
    }

    public int getImage() {return image;}

    public String getName() {return name;}

    public ArrayList<Power> getPowers() {
        return powers;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}

