package hokan.closethebreach;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

        if (savedInstanceState == null)
        {
            Intent intent = getIntent();
            if (intent != null)
                heroes = intent.getIntArrayExtra(HEROES);

            GameFragment frag = new GameFragment();

            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.game_container, frag);
            transaction.commit();
        }

    }

    public int[] getHeroes() {
        return heroes;
    }
}
