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
                null, 15, 8, 6, 4));
        heroes.add(new Hero(getString(R.string.hero_2), R.drawable.arjhan,
                null, 17, 10, 5, 5));
        heroes.add(new Hero(getString(R.string.hero_17), R.drawable.artemis,
                null, 15, 8, 6, 4));
        heroes.add(new Hero(getString(R.string.hero_18), R.drawable.athrogate,
                null, 14,8, 6, 4));
        heroes.add(new Hero(getString(R.string.hero_3), R.drawable.bruenor,
                null, 16, 10, 5, 5));
        heroes.add(new Hero(getString(R.string.hero_4), R.drawable.catti_brie,
                null, 15, 6, 6, 3));
        heroes.add(new Hero(getString(R.string.hero_5), R.drawable.drizzt,
                null, 16, 8, 7, 4));
        heroes.add(new Hero(getString(R.string.hero_6), R.drawable.heskan,
                null, 14, 6, 6, 3));
        heroes.add(new Hero(getString(R.string.hero_7), R.drawable.immeril,
                null,14, 6, 6, 3));
        heroes.add(new Hero(getString(R.string.hero_8), R.drawable.jarlaxle,
                null, 16, 8, 6, 4));
        heroes.add(new Hero(getString(R.string.hero_9), R.drawable.kat,
                null, 14, 8, 6, 4));
        heroes.add(new Hero(getString(R.string.hero_10), R.drawable.keyleth,
                null, 17, 8, 6, 4));
        heroes.add(new Hero(getString(R.string.hero_11), R.drawable.quinn,
                null, 17, 10, 5, 5));
        heroes.add(new Hero(getString(R.string.hero_12), R.drawable.regis,
                null, 14, 8, 6, 4));
        heroes.add(new Hero(getString(R.string.hero_13), R.drawable.tarak,
                null, 14, 8, 6, 4));
        heroes.add(new Hero(getString(R.string.hero_14), R.drawable.thorgrim,
                null, 16, 8, 5, 4));
        heroes.add(new Hero(getString(R.string.hero_15), R.drawable.vistra,
                null, 17, 8, 5, 4));
        heroes.add(new Hero(getString(R.string.hero_16), R.drawable.wulfgar,
                null, 12, 12, 7, 6));
    }
}
