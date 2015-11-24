package hokan.closethebreach.utils;

import android.view.View;

/**
 * Created by bmeunier on 24/11/15.
 */
public interface OnCursorValueChangeListener {

    String onPositionChanged(boolean isLeftCircle, int marker);

    void onCursorValueChangeListener(View v, boolean isInLeftCircle, int marker);

}
