package hokan.closethebreach.fragment;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import hokan.closethebreach.GameApplication;
import hokan.closethebreach.R;
import hokan.closethebreach.adapter.TeamAdapter;

/**
 * Created by Utilisateur on 14/11/2015.
 */
public class TeamFragment extends Fragment {

    GridView grid;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_team, container, false);

        final Activity activity = getActivity();

        Typeface font = GameApplication.getApplication().font;

        TextView title = (TextView) v.findViewById(R.id.team_title);
        title.setTypeface(font);

        grid = (GridView) v.findViewById(R.id.team_grid);
        grid.setAdapter(new TeamAdapter(activity));
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager mgr = getFragmentManager();
                FragmentTransaction transaction = mgr.beginTransaction();
                CharactersFragment c_frag = new CharactersFragment();
                transaction.replace(R.id.main_container, c_frag);
                transaction.addToBackStack("Team");
                transaction.commit();
            }
        });

        Button start = (Button) v.findViewById(R.id.team_button);
        start.setTypeface(font);

        return v;
    }
}
