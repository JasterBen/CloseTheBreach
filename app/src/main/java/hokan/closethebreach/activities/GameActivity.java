package hokan.closethebreach.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;

import hokan.closethebreach.R;
import hokan.closethebreach.fragment.GameFragment;

/**
 * Created by bmeunier on 17/11/15.
 */
public class GameActivity extends AppCompatActivity {

    public static final String HEROES = "heroes";
    public static final String AVAILABLE_XP = "xp";
    public static final String AVAILABLE_LIFE = "life";

    protected int[] heroes;
    protected int availableXp;
    protected int availableLife;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.game_drawer);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        if (savedInstanceState == null)
        {
            Intent intent = getIntent();
            if (intent != null)
                heroes = intent.getIntArrayExtra(HEROES);

            availableXp = 0;
            availableLife = 3;

            GameFragment frag = new GameFragment();

            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.game_container, frag);
            transaction.commit();
        }
        else
        {
            heroes = savedInstanceState.getIntArray(HEROES);
            availableXp = savedInstanceState.getInt(AVAILABLE_XP);
            availableLife = savedInstanceState.getInt(AVAILABLE_LIFE);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntArray(HEROES, heroes);
        outState.putInt(AVAILABLE_XP, availableXp);
        outState.putInt(AVAILABLE_LIFE, availableLife);
    }

    public int[] getHeroes() {
        return heroes;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.game_drawer);
        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START);
        else
            finish();
    }

    public int getAvailableXp() {
        return availableXp;
    }

    public int getAvailableLife() {
        return availableLife;
    }
}
