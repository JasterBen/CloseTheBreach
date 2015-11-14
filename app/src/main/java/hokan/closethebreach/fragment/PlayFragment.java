package hokan.closethebreach.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import hokan.closethebreach.GameApplication;
import hokan.closethebreach.R;

/**
 * Created by Utilisateur on 14/11/2015.
 */
public class PlayFragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_play, container, false);

        Typeface font = GameApplication.getApplication().font;

        Button new_game = (Button) v.findViewById(R.id.button_new);
        new_game.setOnClickListener(this);
        new_game.setTypeface(font);

        Button load = (Button) v.findViewById(R.id.button_load);
        load.setOnClickListener(this);
        new_game.setTypeface(font);

        return v;
    }

    @Override
    public void onClick(View v) {

        FragmentManager mgr = getFragmentManager();
        FragmentTransaction transaction = mgr.beginTransaction();

        switch (v.getId()) {
            case R.id.button_new :
                TeamFragment t_frag = new TeamFragment();
                transaction.replace(R.id.main_container, t_frag);
                break;
            case R.id.button_load :
                CharactersFragment c_frag = new CharactersFragment();
                transaction.replace(R.id.main_container, c_frag);
                break;
            default:
                break;
        }

        transaction.addToBackStack("Play");
        transaction.commit();
    }
}

