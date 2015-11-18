package hokan.closethebreach;

import android.app.Application;
import android.graphics.Typeface;
import android.widget.TextView;

import java.util.ArrayList;

import hokan.closethebreach.creatures.Hero;
import hokan.closethebreach.utils.Attack;
import hokan.closethebreach.utils.Job;
import hokan.closethebreach.utils.Power;

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
        
        ArrayList<Job> jobs = generateJob();
        
        heroes = new ArrayList<>();
        heroes.add(new Hero(getString(R.string.hero_1), R.drawable.allisa, 
                jobs.get(0), 15, 8, 6, 4));
        heroes.add(new Hero(getString(R.string.hero_2), R.drawable.arjhan,
                jobs.get(1), 17, 10, 5, 5));
        heroes.add(new Hero(getString(R.string.hero_17), R.drawable.artemis,
                jobs.get(2), 15, 8, 6, 4));
        heroes.add(new Hero(getString(R.string.hero_18), R.drawable.athrogate,
                jobs.get(3), 14,8, 6, 4));
        heroes.add(new Hero(getString(R.string.hero_3), R.drawable.bruenor,
                jobs.get(4), 16, 10, 5, 5));
        heroes.add(new Hero(getString(R.string.hero_4), R.drawable.catti_brie,
                jobs.get(5), 15, 6, 6, 3));
        heroes.add(new Hero(getString(R.string.hero_5), R.drawable.drizzt,
                jobs.get(6), 16, 8, 7, 4));
        heroes.add(new Hero(getString(R.string.hero_6), R.drawable.heskan,
                jobs.get(7), 14, 6, 6, 3));
        heroes.add(new Hero(getString(R.string.hero_7), R.drawable.immeril,
                jobs.get(8),14, 6, 6, 3));
        heroes.add(new Hero(getString(R.string.hero_8), R.drawable.jarlaxle,
                jobs.get(9), 16, 8, 6, 4));
        heroes.add(new Hero(getString(R.string.hero_9), R.drawable.kat,
                jobs.get(10), 14, 8, 6, 4));
        heroes.add(new Hero(getString(R.string.hero_10), R.drawable.keyleth,
                jobs.get(11), 17, 8, 6, 4));
        heroes.add(new Hero(getString(R.string.hero_11), R.drawable.quinn,
                jobs.get(12), 17, 10, 5, 5));
        heroes.add(new Hero(getString(R.string.hero_12), R.drawable.regis,
                jobs.get(13), 14, 8, 6, 4));
        heroes.add(new Hero(getString(R.string.hero_13), R.drawable.tarak,
                jobs.get(14), 14, 8, 6, 4));
        heroes.add(new Hero(getString(R.string.hero_14), R.drawable.thorgrim,
                jobs.get(15), 16, 8, 5, 4));
        heroes.add(new Hero(getString(R.string.hero_15), R.drawable.vistra,
                jobs.get(16), 17, 8, 5, 4));
        heroes.add(new Hero(getString(R.string.hero_16), R.drawable.wulfgar,
                jobs.get(17), 12, 12, 7, 6));
    }

    public ArrayList<Job> generateJob()
    {
        ArrayList<Job> jobs = new ArrayList<>();
        ArrayList<Power> powers = new ArrayList<>();
        powers.add(new Power("Attaque prudente"));
        powers.add(new Power("Tir de chasseur", new Attack(6, 1)));
        powers.add(new Power("Double tir"));
        powers.add(new Power("Course d'attaque"));
        powers.add(new Power("Aide cruciale"));
        powers.add(new Power("Saut d'attaque"));
        jobs.add(new Job(getString(R.string.job_1), powers));
        jobs.add(new Job(getString(R.string.job_2), null));
        jobs.add(new Job(getString(R.string.job_3), null));
        jobs.add(new Job(getString(R.string.job_4), null));
        jobs.add(new Job(getString(R.string.job_5), null));
        jobs.add(new Job(getString(R.string.job_6), null));
        jobs.add(new Job(getString(R.string.job_7), null));
        jobs.add(new Job(getString(R.string.job_8), null));
        jobs.add(new Job(getString(R.string.job_9), null));
        jobs.add(new Job(getString(R.string.job_10), null));
        jobs.add(new Job(getString(R.string.job_11), null));
        jobs.add(new Job(getString(R.string.job_12), null));
        jobs.add(new Job(getString(R.string.job_13), null));
        jobs.add(new Job(getString(R.string.job_14), null));
        jobs.add(new Job(getString(R.string.job_15), null));
        jobs.add(new Job(getString(R.string.job_16), null));
        jobs.add(new Job(getString(R.string.job_17), null));
        jobs.add(new Job(getString(R.string.job_18), null));
        return jobs;
    }
}
