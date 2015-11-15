package hokan.closethebreach.utils;

/**
 * Created by Utilisateur on 14/11/2015.
 */
public class Power {
    protected String name;
    protected String description;

    public Power(String name) {
        this.name = name;
        description = name;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
