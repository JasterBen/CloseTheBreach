package hokan.closethebreach.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import hokan.closethebreach.R;
import hokan.closethebreach.adapter.ImageAdapter;

/**
 * Created by Utilisateur on 14/11/2015.
 */
public class CharactersFragment extends Fragment implements AdapterView.OnItemClickListener {

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
        imageAdapter.getItem(position).setEnable(false);
        Toast.makeText(getActivity(), imageAdapter.getItem(position).getName(), Toast.LENGTH_SHORT).show();
    }
}
