package hokan.closethebreach.utils;

/**
 * Created by Utilisateur on 14/11/2015.
 */
public class Power {
    protected String name;
    protected String description;
    protected Attack attack;

    public Power(String name) {
        this.name = name;
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do " +
                "eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, " +
                "quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.";
    }

    public Power(String name, Attack attack) {
        this.name = name;
        this.attack = attack;
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do " +
                "eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, " +
                "quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.";
    }

    public Attack getAttack() {
        return attack;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
