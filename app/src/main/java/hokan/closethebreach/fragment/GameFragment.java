package hokan.closethebreach.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import hokan.closethebreach.activities.GameActivity;
import hokan.closethebreach.GameApplication;
import hokan.closethebreach.R;
import hokan.closethebreach.adapter.GameAdapter;
import hokan.closethebreach.creatures.Hero;
import hokan.closethebreach.utils.Power;

/**
 * Created by bmeunier on 17/11/15.
 */
public class GameFragment extends Fragment implements AdapterView.OnItemClickListener,
        NavigationView.OnNavigationItemSelectedListener {

    protected GameActivity activity;
    protected GameAdapter adapter;

    protected int heroClicked;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_game, container, false);

        //if (savedInstanceState == null)
        //{
            activity = (GameActivity) getActivity();
            Typeface font = GameApplication.getApplication().font;

            ListView listview = (ListView) v.findViewById(R.id.game_heroes_list);
            adapter = new GameAdapter(activity);
            listview.setAdapter(adapter);
            listview.setOnItemClickListener(this);

            TextView xp = (TextView) v.findViewById(R.id.game_xp_text);
            xp.setTypeface(font);
            xp.setText(activity.getString(R.string.available_xp) + activity.getAvailableXp());

            TextView life = (TextView) v.findViewById(R.id.life_number_text);
            life.setTypeface(font);
            life.setText(activity.getString(R.string.lifes) + activity.getAvailableLife());
        //}

        return v;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        DrawerLayout drawerLayout = (DrawerLayout) getActivity().findViewById(R.id.game_drawer);
        heroClicked = position;
        Hero hero = adapter.getItem(position);
        if (hero != null)
        {
            ArrayList<Power> heroPowers = hero.getJob().getPowers();
            int heroPowersSize = heroPowers == null ? 0 : heroPowers.size();

            Typeface font = GameApplication.getApplication().font;

            NavigationView navigation = (NavigationView) drawerLayout.findViewById(R.id.game_navigation);
            Menu menu = navigation.getMenu();
            menu.clear();
            for (Power power : heroPowers)
                menu.add(power.getName());

            navigation.setNavigationItemSelectedListener(this);


            ImageView image = (ImageView) navigation.findViewById(R.id.navigation_header_image);
            image.setImageResource(hero.getImage());

            TextView name = (TextView) navigation.findViewById(R.id.navigation_header_name);
            name.setTypeface(font);
            name.setText(hero.getName());

            TextView health = (TextView) navigation.findViewById(R.id.navigation_header_health);
            health.setTypeface(font);
            health.setText(hero.getCurrentHp() + "/" + hero.getHp());

            drawerLayout.openDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.game_drawer);
        drawer.closeDrawer(GravityCompat.START);

        String title = item.getTitle().toString();
        ArrayList<Power> powers = adapter.getItem(heroClicked).getJob().getPowers();
        for (Power power : powers)
            if (power.getName().equals(title))
                break;

        return false;
    }
}
