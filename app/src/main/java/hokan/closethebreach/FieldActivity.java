package hokan.closethebreach;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import hokan.closethebreach.fragment.FieldFragment;

/**
 * Created by bmeunier on 18/11/15.
 */
public class FieldActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);

        FieldFragment frag = new FieldFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transac = manager.beginTransaction();
        transac.replace(R.id.field_container, frag);
        transac.commit();
    }
}
