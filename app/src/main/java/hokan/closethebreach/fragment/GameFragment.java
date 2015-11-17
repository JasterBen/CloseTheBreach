package hokan.closethebreach.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import hokan.closethebreach.GameActivity;
import hokan.closethebreach.GameApplication;
import hokan.closethebreach.R;
import hokan.closethebreach.adapter.GameAdapter;

/**
 * Created by bmeunier on 17/11/15.
 */
public class GameFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_game, container, false);

        if (savedInstanceState == null)
        {
            GameActivity activity = (GameActivity) getActivity();

            ListView listview = (ListView) v.findViewById(R.id.game_heroes_list);
            listview.setAdapter(new GameAdapter(activity));

            TextView life = (TextView) v.findViewById(R.id.life_number_text);
            life.setTypeface(GameApplication.getApplication().font);
            life.setText(activity.getString(R.string.lifes) + "\n" + activity.getString(R.string.base_life));
        }

        return v;
    }
}
