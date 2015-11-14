package hokan.closethebreach;

import android.app.Application;
import android.graphics.Typeface;

import java.util.ArrayList;

import hokan.closethebreach.creatures.Hero;

/**
 * Created by Utilisateur on 14/11/2015.
 */
public class GameApplication extends Application {

    protected ArrayList<Hero> heroes;
    public Typeface font;
    protected static GameApplication app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        font = Typeface.createFromAsset(getAssets(), "font/calli.ttf");
        generateHeroes();
    }

    public static GameApplication getApplication() {
        return app;
    }

    public ArrayList<Hero> getHeroes() {
        return heroes;
    }

    public void generateHeroes() {
        heroes = new ArrayList<>();
        heroes.add(new Hero(getString(R.string.hero_1), R.drawable.allisa, 
                null,
                R.integer.hero_1_armor, R.integer.hero_1_hp, 
                R.integer.hero_1_speed, R.integer.hero_1_respawn));
        heroes.add(new Hero(getString(R.string.hero_2), R.drawable.arjhan,
                null,
                R.integer.hero_2_armor, R.integer.hero_2_hp,
                R.integer.hero_2_speed, R.integer.hero_2_respawn));
        heroes.add(new Hero(getString(R.string.hero_17), R.drawable.artemis,
                null,
                R.integer.hero_17_armor, R.integer.hero_17_hp,
                R.integer.hero_17_speed, R.integer.hero_17_respawn));
        heroes.add(new Hero(getString(R.string.hero_18), R.drawable.athrogate,
                null,
                R.integer.hero_18_armor, R.integer.hero_18_hp,
                R.integer.hero_18_speed, R.integer.hero_18_respawn));
        heroes.add(new Hero(getString(R.string.hero_3), R.drawable.bruenor,
                null,
                R.integer.hero_3_armor, R.integer.hero_3_hp,
                R.integer.hero_3_speed, R.integer.hero_3_respawn));
        heroes.add(new Hero(getString(R.string.hero_4), R.drawable.catti_brie,
                null,
                R.integer.hero_4_armor, R.integer.hero_4_hp,
                R.integer.hero_4_speed, R.integer.hero_4_respawn));
        heroes.add(new Hero(getString(R.string.hero_5), R.drawable.drizzt,
                null,
                R.integer.hero_5_armor, R.integer.hero_5_hp,
                R.integer.hero_5_speed, R.integer.hero_5_respawn));
        heroes.add(new Hero(getString(R.string.hero_6), R.drawable.heskan,
                null,
                R.integer.hero_6_armor, R.integer.hero_6_hp,
                R.integer.hero_6_speed, R.integer.hero_6_respawn));
        heroes.add(new Hero(getString(R.string.hero_7), R.drawable.immeril,
                null,
                R.integer.hero_7_armor, R.integer.hero_7_hp,
                R.integer.hero_7_speed, R.integer.hero_7_respawn));
        heroes.add(new Hero(getString(R.string.hero_8), R.drawable.jarlaxle,
                null,
                R.integer.hero_8_armor, R.integer.hero_8_hp,
                R.integer.hero_8_speed, R.integer.hero_8_respawn));
        heroes.add(new Hero(getString(R.string.hero_9), R.drawable.kat,
                null,
                R.integer.hero_9_armor, R.integer.hero_9_hp,
                R.integer.hero_9_speed, R.integer.hero_9_respawn));
        heroes.add(new Hero(getString(R.string.hero_10), R.drawable.keyleth,
                null,
                R.integer.hero_10_armor, R.integer.hero_10_hp,
                R.integer.hero_10_speed, R.integer.hero_10_respawn));
        heroes.add(new Hero(getString(R.string.hero_11), R.drawable.quinn,
                null,
                R.integer.hero_11_armor, R.integer.hero_11_hp,
                R.integer.hero_11_speed, R.integer.hero_11_respawn));
        heroes.add(new Hero(getString(R.string.hero_12), R.drawable.regis,
                null,
                R.integer.hero_12_armor, R.integer.hero_12_hp,
                R.integer.hero_12_speed, R.integer.hero_12_respawn));
        heroes.add(new Hero(getString(R.string.hero_13), R.drawable.tarak,
                null,
                R.integer.hero_13_armor, R.integer.hero_13_hp,
                R.integer.hero_13_speed, R.integer.hero_13_respawn));
        heroes.add(new Hero(getString(R.string.hero_14), R.drawable.thorgrim,
                null,
                R.integer.hero_14_armor, R.integer.hero_14_hp,
                R.integer.hero_14_speed, R.integer.hero_14_respawn));
        heroes.add(new Hero(getString(R.string.hero_15), R.drawable.vistra,
                null,
                R.integer.hero_15_armor, R.integer.hero_15_hp,
                R.integer.hero_15_speed, R.integer.hero_15_respawn));
        heroes.add(new Hero(getString(R.string.hero_16), R.drawable.wulfgar,
                null,
                R.integer.hero_16_armor, R.integer.hero_16_hp,
                R.integer.hero_16_speed, R.integer.hero_16_respawn));
    }
}
