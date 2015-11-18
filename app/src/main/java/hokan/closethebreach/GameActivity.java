package hokan.closethebreach;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;

import hokan.closethebreach.creatures.Hero;
import hokan.closethebreach.fragment.GameFragment;

/**
 * Created by bmeunier on 17/11/15.
 */
public class GameActivity extends AppCompatActivity {

    public static final String HEROES = "heroes";

    protected int[] heroes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);

        if (savedInstanceState == null)
        {
            Intent intent = getIntent();
            if (intent != null)
                heroes = intent.getIntArrayExtra(HEROES);
        }
        else
        {
            heroes = savedInstanceState.getIntArray(HEROES);
        }

        GameFragment frag = new GameFragment();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.game_container, frag);
        transaction.commit();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntArray(HEROES, heroes);
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
}
