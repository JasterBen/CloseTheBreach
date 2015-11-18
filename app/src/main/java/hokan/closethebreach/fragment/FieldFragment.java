package hokan.closethebreach.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hokan.closethebreach.R;
import hokan.closethebreach.utils.FieldView;

/**
 * Created by bmeunier on 18/11/15.
 */
public class FieldFragment extends Fragment {
    

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return new FieldView(getActivity());
    }
}
