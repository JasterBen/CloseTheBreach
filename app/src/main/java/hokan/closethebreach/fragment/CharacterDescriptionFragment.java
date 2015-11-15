package hokan.closethebreach.fragment;

import android.support.v4.app.DialogFragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    int position;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_character_description, container, false);

        GameApplication app = GameApplication.getApplication();
        Typeface font = app.font;

        position = -1;
        Bundle bundle = getArguments();
        if (bundle != null)
            position = getBundleArg(bundle);
        else if (savedInstanceState != null)
            position = getBundleArg(savedInstanceState);

        Hero hero = position == -1 ? null : app.getHeroes().get(position);

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

        TextView passif = (TextView) v.findViewById(R.id.character_descritpion_passif);
        passif.setTypeface(font);
        passif.setText("Passif : description du passif de 2 lignes maximum. Remplissage remplissage remplissage remplissage remplissage remplissage remplissage.");

        ListView powers = (ListView) v.findViewById(R.id.character_descritpion_power_list);
        powers.setAdapter(new DialogAdapter(getActivity(), hero.getJob().getPowers()));

        return v;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CharactersFragment.POSITION, position);
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
    }

    public int getBundleArg(Bundle bundle)
    {
        return bundle.getInt(CharactersFragment.POSITION);
    }
}
