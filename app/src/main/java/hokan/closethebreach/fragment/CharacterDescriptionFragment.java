package hokan.closethebreach.fragment;

import android.app.Activity;
import android.support.v4.app.DialogFragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import hokan.closethebreach.GameApplication;
import hokan.closethebreach.R;
import hokan.closethebreach.adapter.DialogAdapter;
import hokan.closethebreach.creatures.Hero;

/**
 * Created by Utilisateur on 15/11/2015.
 */
public class CharacterDescriptionFragment extends DialogFragment {

    public static final String HERO = "heros";

    protected int heroPosition;
    protected Hero hero;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        heroPosition = -1;
        Bundle bundle = getArguments();
        if (bundle != null)
            heroPosition = getBundleArg(bundle);
        else if (savedInstanceState != null)
            heroPosition = getBundleArg(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_character_description, container, false);

        GameApplication app = GameApplication.getApplication();
        Typeface font = app.font;
        Activity activity = getActivity();

        Hero hero = heroPosition == -1 ? null : app.getHeroes().get(heroPosition);

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        ImageView image = (ImageView) v.findViewById(R.id.character_descritpion_image);
        if (hero == null)
            image.setImageResource(R.drawable.shield);
        else
            image.setImageResource(hero.getImage());

        TextView name = (TextView) v.findViewById(R.id.character_descritpion_name);
        name.setTypeface(font);
        if (hero != null)
            name.setText(hero.getName());

        TextView job = (TextView) v.findViewById(R.id.character_descritpion_job);
        job.setTypeface(font);
        if (hero != null)
            job.setText(hero.getJob().getName());

        TextView armor = (TextView) v.findViewById(R.id.character_descritpion_armor_class);
        armor.setTypeface(font);
        if (hero != null)
            armor.setText(activity.getString(R.string.armor) + "\n" + hero.getArmor());

        TextView hp = (TextView) v.findViewById(R.id.character_descritpion_hp);
        hp.setTypeface(font);
        if (hero != null)
            hp.setText(activity.getString(R.string.hp) + "\n" + hero.getHp());

        TextView speed = (TextView) v.findViewById(R.id.character_descritpion_speed);
        speed.setTypeface(font);
        if (hero != null)
            speed.setText(activity.getString(R.string.speed) + "\n" + hero.getSpeed());

        TextView respawn = (TextView) v.findViewById(R.id.character_descritpion_respawn);
        respawn.setTypeface(font);
        if (hero != null)
            respawn.setText(activity.getString(R.string.respawn) + "\n" + hero.getRespawn());

        TextView passif = (TextView) v.findViewById(R.id.character_descritpion_passif);
        passif.setTypeface(font);
        passif.setText("Passif : description du passif de 2 lignes maximum. Remplissage remplissage remplissage remplissage remplissage remplissage remplissage.");

        ListView powers = (ListView) v.findViewById(R.id.character_descritpion_power_list);
        powers.setAdapter(new DialogAdapter(getActivity(), hero.getJob().getPowers()));
        powers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int powerPos, long id) {
                Bundle bundle = new Bundle();
                bundle.putInt(CharactersFragment.POSITION, powerPos);
                bundle.putInt(HERO, heroPosition);
                FragmentManager manager = getFragmentManager();
                PowerDescriptionFragment frag = new PowerDescriptionFragment();
                frag.setArguments(bundle);
                frag.show(manager, "dialog");
            }
        });

        return v;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CharactersFragment.POSITION, heroPosition);
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
    }

    protected int getBundleArg(Bundle bundle)
    {
        return bundle.getInt(CharactersFragment.POSITION);
    }
}
