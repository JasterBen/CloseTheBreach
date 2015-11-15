package hokan.closethebreach.fragment;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import hokan.closethebreach.GameApplication;
import hokan.closethebreach.R;
import hokan.closethebreach.utils.Power;

/**
 * Created by Utilisateur on 15/11/2015.
 */
public class PowerDescriptionFragment extends DialogFragment {

    protected int heroPos;
    protected int powerPos;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null)
            getBundleArg(bundle);
        else if (savedInstanceState != null)
            getBundleArg(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_power_description, container, false);

        GameApplication app = GameApplication.getApplication();
        Typeface font = app.font;
        Activity activity = getActivity();

        Power power = app.getHeroes().get(heroPos).getJob().getPowers().get(powerPos);

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        TextView name = (TextView) v.findViewById(R.id.power_description_name);
        name.setTypeface(font);
        if (power != null)
            name.setText(power.getName());

        TextView description = (TextView) v.findViewById(R.id.power_description_content);
        description.setTypeface(font);
        if (power != null)
            description.setText(power.getDescription());

        TextView attack = (TextView) v.findViewById(R.id.power_description_attack);
        attack.setTypeface(font);
        if (power != null && power.getAttack() != null)
            attack.setText(activity.getString(R.string.attack) + "\n+" + power.getAttack().getModif());

        TextView damage = (TextView) v.findViewById(R.id.power_description_damage);
        damage.setTypeface(font);
        if (power != null && power.getAttack() != null)
            damage.setText(activity.getString(R.string.damage) + "\n" + power.getAttack().getDamage());

        return v;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CharacterDescriptionFragment.HERO, heroPos);
        outState.putInt(CharactersFragment.POSITION, powerPos);
    }

    public void getBundleArg(Bundle bundle)
    {
        heroPos = bundle.getInt(CharacterDescriptionFragment.HERO);
        powerPos = bundle.getInt(CharactersFragment.POSITION);
    }
}
