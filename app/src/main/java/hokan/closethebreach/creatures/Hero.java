package hokan.closethebreach.creatures;

import java.util.ArrayList;

import hokan.closethebreach.utils.Job;

/**
 * Created by Utilisateur on 14/11/2015.
 */
public class Hero {
    protected String name;
    protected int image;
    protected Job job;
    protected int armor;
    protected int hp;
    protected int currentHp;
    protected int speed;
    protected int respawn;
    protected ArrayList<Monster> monsters;
    //protected ArrayList<Power> powers;
    protected boolean levelTwo;
    protected boolean enable;

    public Hero(String name, int image, Job job, int armor, int hp,
                int speed, int respawn) {
        this.name = name;
        this.image = image;
        this.job = job;
        this.armor = armor;
        this.hp = hp;
        this.currentHp = hp;
        this.speed = speed;
        this.respawn = respawn;
        monsters = new ArrayList<>();
        //powers = new ArrayList<>(6); //un personnage n'a jamais plus de 6 pouvoirs
        levelTwo = false;
        enable = true;
    }

    public int getImage() {return image;}

    public String getName() {return name;}

    /*public ArrayList<Power> getPowers() {
        return powers;
    }*/

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Job getJob()
    {
        return job;
    }

    public void setJob(Job job)
    {
        this.job = job;
    }

    public int getArmor() {
        return armor;
    }

    public int getHp() {
        return hp;
    }

    public int getSpeed() {
        return speed;
    }

    public int getRespawn() {
        return respawn;
    }

    public int getCurrentHp() { return currentHp; }
}

