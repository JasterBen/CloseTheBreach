package hokan.closethebreach.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;

import hokan.closethebreach.R;
import hokan.closethebreach.adapter.ImageAdapter;
import hokan.closethebreach.creatures.Hero;

/**
 * Created by Utilisateur on 14/11/2015.
 */
public class CharactersFragment extends DialogFragment implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    public static final String POSITION = "position";

    protected ImageAdapter imageAdapter;
    protected GridView gridView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_characters, container, false);

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        gridView = (GridView) v.findViewById(R.id.characters_grid);
        imageAdapter = new ImageAdapter(getActivity());
        gridView.setAdapter(imageAdapter);
        gridView.setOnItemClickListener(this);
        gridView.setOnItemLongClickListener(this);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        onItemSelected(false, position);
    }


    private void notifyToTarget(int code, int pos)
    {
        Fragment target = getTargetFragment();
        if (target != null)
        {
            Intent intent = new Intent();
            intent.putExtra(POSITION, pos);
            target.onActivityResult(getTargetRequestCode(), code, intent);
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        onItemSelected(true, position);
        return true;
    }


    public void onItemSelected(boolean isLong, int position)
    {
        Hero hero = imageAdapter.getItem(position);
        if (!isLong)
        {
            hero.setEnable(false);
            notifyToTarget(Activity.RESULT_OK, position);
            CharactersFragment.this.dismiss();
        }
        else
        {
            //String prout = "prout";
//            ArrayList<Power> powers = new ArrayList<>();
//            powers.add(new Power("Attaque prudente"));
//            powers.add(new Power("Tir de chasseur", new Attack(6, 1)));
//            powers.add(new Power("Double tir"));
//            powers.add(new Power("Course d'attaque"));
//            powers.add(new Power("Aide cruciale"));
//            powers.add(new Power("Saut d'attaque"));
//            hero.setJob(new Job("Ranger humain", powers));
//            Job job = hero.getJob();
//            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//            builder.setIcon(hero.getImage());
//            builder.setTitle(hero.getName() + " - " + job.getName());
//            builder.setAdapter(new DialogAdapter(getActivity(), job.getPowers()), null);
//            builder.setPositiveButton(android.R.string.ok, null);
//            builder.create().show();

            Bundle bundle = new Bundle();
            bundle.putInt(POSITION, position);
            FragmentManager manager = getFragmentManager();
            CharacterDescriptionFragment dialog = new CharacterDescriptionFragment();
            dialog.setArguments(bundle);
            dialog.show(manager, "dialog");
        }
    }
}
