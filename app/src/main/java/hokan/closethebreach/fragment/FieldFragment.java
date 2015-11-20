package hokan.closethebreach.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hokan.closethebreach.FieldView;
import hokan.closethebreach.SlideView;

/**
 * Created by bmeunier on 18/11/15.
 */
public class FieldFragment extends Fragment {
    

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return new SlideView(getActivity());
    }
}
