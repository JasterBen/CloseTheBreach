package hokan.closethebreach.creatures;

/**
 * Created by Utilisateur on 14/11/2015.
 */
public abstract class Monster {

    protected String name;
    protected int armorClass;
    protected int hp;
    protected int xp;

    public Monster(String name, int armorClass, int hp, int xp) {
        this.name = name;
        this.armorClass = armorClass;
        this.hp = hp;
        this.xp = xp;
    }

    public abstract void tactic();
}

