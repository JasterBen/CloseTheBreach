package hokan.closethebreach.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import hokan.closethebreach.R;
import hokan.closethebreach.SlideView;
import hokan.closethebreach.utils.OnCursorValueChangeListener;

/**
 * Created by bmeunier on 18/11/15.
 */
public class SliderFragment extends Fragment implements View.OnClickListener, OnCursorValueChangeListener {

    protected SlideView slideOne;
    protected SlideView slideTwo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_slider, container, false);

        slideOne = (SlideView) view.findViewById(R.id.slider_first_slideview);
        slideOne.setCursorValueChangeListener(this);

        slideTwo = (SlideView) view.findViewById(R.id.slider_second_slideview);
        slideTwo.setCursorValueChangeListener(this);

        Button button = (Button) view.findViewById(R.id.validate_button);
        button.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {


        int firstLeft = slideOne.getLeftCircleActualPos();
        int firstRight = slideOne.getRightCircleActualPos();

        int secondLeft = slideTwo.getLeftCircleActualPos();
        int secondRight = slideTwo.getRightCircleActualPos();

        Toast.makeText(getActivity(), "First slide :\nLeft : " + firstLeft +
                "\nRight : " + firstRight + "\nSecond slide :\nLeft : " + secondLeft +
                "\nRight : " + secondRight, Toast.LENGTH_LONG).show();
    }

    @Override
    public String onPositionChanged(boolean isLeftCircle, int marker) {
        Context c = getContext();
        String circle = isLeftCircle ? c.getString(R.string.test_left_circle)
                : c.getString(R.string.test_right_circle);
        return circle + marker;
    }

    @Override
    public void onCursorValueChangeListener(View v, boolean isInLeftCircle, int marker) {

        Context c = getContext();
        String res = null;

        switch (v.getId()) {
            case R.id.slider_first_slideview:
                res = c.getString(R.string.test_text_1) + "\n" + onPositionChanged(isInLeftCircle, marker);
                break;
            case R.id.slider_second_slideview:
                res = c.getString(R.string.test_text_2) + "\n" + onPositionChanged(isInLeftCircle, marker);
                break;
            default :
                break;
        }

        if (res != null)
            Toast.makeText(c, res, Toast.LENGTH_SHORT).show();
    }
}
