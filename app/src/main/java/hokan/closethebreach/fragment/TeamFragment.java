package hokan.closethebreach.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import hokan.closethebreach.FieldActivity;
import hokan.closethebreach.GameActivity;
import hokan.closethebreach.GameApplication;
import hokan.closethebreach.R;
import hokan.closethebreach.adapter.TeamAdapter;
import hokan.closethebreach.creatures.Hero;

/**
 * Created by Utilisateur on 14/11/2015.
 */
public class TeamFragment extends Fragment implements View.OnClickListener {


    public static final int REQ_CODE = 1;
    public static final String HEROES = "heroes";

    protected GridView grid;
    protected TeamAdapter adapter;
    protected View selectedView;
    protected int selectedViewPosition;
    protected Button start;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_team, container, false);
        final Activity activity = getActivity();
        Typeface font = GameApplication.getApplication().font;

        TextView title = (TextView) v.findViewById(R.id.team_title);
        title.setTypeface(font);

        grid = (GridView) v.findViewById(R.id.team_grid);
        adapter = new TeamAdapter(activity);
        if (savedInstanceState != null)
            adapter.setHeroesSelected(savedInstanceState.getIntArray(HEROES));
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                FragmentManager mgr = getFragmentManager();
//                FragmentTransaction transaction = mgr.beginTransaction();
//                CharactersFragment c_frag = new CharactersFragment();
//                transaction.replace(R.id.main_container, c_frag);
//                transaction.addToBackStack("Team");
//                transaction.commit();
                selectedView = view;
                selectedViewPosition = position;
                FragmentManager manager = getFragmentManager();
                CharactersFragment dialog = new CharactersFragment();
                dialog.setTargetFragment(TeamFragment.this, REQ_CODE);
                dialog.show(manager, "dialog");
            }
        });

        start = (Button) v.findViewById(R.id.team_button);
        start.setOnClickListener(this);
        start.setClickable(false);
        start.setTypeface(font);

        return v;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntArray(HEROES, adapter.getHeroesSelected());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        int heroPos = data.getIntExtra(CharactersFragment.POSITION, -1);
        int image = R.drawable.shield;
        String name = null;
        if (heroPos != -1)
        {
            Hero hero = GameApplication.getApplication().getHeroes().get(heroPos);
            if (adapter.getHero(selectedViewPosition) != -1)
                GameApplication.getApplication().getHeroes().get(adapter.getHero(selectedViewPosition)).setEnable(true);
            adapter.setHero(selectedViewPosition, heroPos);
            image = hero.getImage();
            name = hero.getName();
        }
        ImageView imageView = (ImageView) selectedView.findViewById(R.id.character_image);
        TextView nameView = (TextView) selectedView.findViewById(R.id.character_name_textview);
        imageView.setImageResource(image);
        nameView.setText(name);
        if (!start.isClickable())
            start.setClickable(true);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), GameActivity.class);
        intent.putExtra(GameActivity.HEROES, adapter.getHeroesSelected());
        startActivity(intent);
    }
}
