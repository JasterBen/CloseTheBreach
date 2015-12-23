package hokan.closethebreach.activities;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import hokan.closethebreach.R;
import hokan.closethebreach.fragment.SliderFragment;

/**
 * Created by bmeunier on 18/11/15.
 */
public class SliderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);

        SliderFragment frag = new SliderFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transac = manager.beginTransaction();
        transac.replace(R.id.field_container, frag);
        transac.commit();
    }
}
