package hokan.closethebreach.fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import hokan.closethebreach.activities.SliderActivity;
import hokan.closethebreach.GameApplication;
import hokan.closethebreach.R;
import hokan.closethebreach.creatures.Hero;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        Typeface font = GameApplication.getApplication().font;

        Button play = (Button) v.findViewById(R.id.button_play);
        play.setOnClickListener(this);
        play.setTypeface(font);

        Button options = (Button) v.findViewById(R.id.button_option);
        options.setOnClickListener(this);
        options.setTypeface(font);

        Button quit = (Button) v.findViewById(R.id.button_quit);
        quit.setOnClickListener(this);
        quit.setTypeface(font);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        ArrayList<Hero> heroes = GameApplication.getApplication().getHeroes();
        for (Hero h : heroes)
            h.setEnable(true);
    }

    @Override
    public void onClick(View v) {

        final Activity activity = getActivity();

        switch (v.getId()) {
            case R.id.button_play :
                //PlayFragment frag = new PlayFragment();
                TeamFragment frag = new TeamFragment();
                FragmentManager mgr = getFragmentManager();
                FragmentTransaction transaction = mgr.beginTransaction();
                transaction.replace(R.id.main_container, frag);
                transaction.addToBackStack("Main");
                transaction.commit();
                break;
            case R.id.button_option :
                Toast.makeText(activity, R.string.button_option, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), SliderActivity.class);
                startActivity(intent);
                break;
            case R.id.button_quit :
                AlertDialog.Builder quit = new AlertDialog.Builder(activity);
                quit.setTitle(R.string.quit_alert_dialog_title);
                quit.setMessage(R.string.quit_alert_dialog_message);
                quit.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        activity.finish();
                    }
                });
                quit.setNegativeButton(android.R.string.no, null);
                quit.create().show();
                break;
            default:
                break;
        }
    }
}

