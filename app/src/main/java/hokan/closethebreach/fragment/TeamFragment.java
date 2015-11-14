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

import hokan.closethebreach.GameApplication;
import hokan.closethebreach.R;
import hokan.closethebreach.adapter.TeamAdapter;
import hokan.closethebreach.creatures.Hero;

/**
 * Created by Utilisateur on 14/11/2015.
 */
public class TeamFragment extends Fragment {

    public static final String IMAGE = "image";
    public static final String NAME = "name";
    public static final int REQ_CODE = 1;

    GridView grid;
    TeamAdapter adapter;
    View selectedView;
    Button start;

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
                FragmentManager manager = getFragmentManager();
                CharactersFragment dialog = new CharactersFragment();
                dialog.setTargetFragment(TeamFragment.this, REQ_CODE);
                dialog.show(manager, "dialog");
            }
        });

        start = (Button) v.findViewById(R.id.team_button);
        start.setClickable(false);
        start.setTypeface(font);

        return v;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        int image = data.getIntExtra(IMAGE, R.drawable.shield);
        String name = data.getStringExtra(NAME);
        ImageView imageView = (ImageView) selectedView.findViewById(R.id.character_image);
        TextView nameView = (TextView) selectedView.findViewById(R.id.character_name_textview);
        imageView.setImageResource(image);
        nameView.setText(name);
        if (!start.isClickable())
            start.setClickable(true);
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onDestroy() {
        ArrayList<Hero> heroes = GameApplication.getApplication().getHeroes();
        for (Hero h : heroes)
            h.setEnable(true);
        super.onDestroy();
    }
}
