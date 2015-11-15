package hokan.closethebreach.utils;

import java.util.ArrayList;

/**
 * Created by Utilisateur on 14/11/2015.
 */
public class Job {
    protected String name;
    protected ArrayList<Power> powers;

    public Job(String name, ArrayList<Power> powers) {
        this.name = name;
        this.powers = powers;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Power> getPowers() {
        return powers;
    }


}
