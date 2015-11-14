package hokan.closethebreach.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import hokan.closethebreach.R;
import hokan.closethebreach.adapter.ImageAdapter;
import hokan.closethebreach.creatures.Hero;

/**
 * Created by Utilisateur on 14/11/2015.
 */
public class CharactersFragment extends DialogFragment implements AdapterView.OnItemClickListener {

    protected ImageAdapter imageAdapter;
    protected GridView gridView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_characters, container, false);

        gridView = (GridView) v.findViewById(R.id.characters_grid);
        imageAdapter = new ImageAdapter(getActivity());
        gridView.setAdapter(imageAdapter);
        gridView.setOnItemClickListener(this);

        return v;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Hero hero = imageAdapter.getItem(position);
        hero.setEnable(false);
        notifyToTarget(Activity.RESULT_OK, hero.getImage(), hero.getName());
        CharactersFragment.this.dismiss();
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
    }

    private void notifyToTarget(int code, int image, String name)
    {
        Fragment target = getTargetFragment();
        if (target != null)
        {
            Intent intent = new Intent();
            intent.putExtra(TeamFragment.IMAGE, image);
            intent.putExtra(TeamFragment.NAME, name);
            target.onActivityResult(getTargetRequestCode(), code, intent);
        }
    }
}
