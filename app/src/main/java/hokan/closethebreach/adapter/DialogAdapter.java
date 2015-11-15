package hokan.closethebreach.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import hokan.closethebreach.GameApplication;
import hokan.closethebreach.utils.Power;

/**
 * Created by Utilisateur on 15/11/2015.
 */
public class DialogAdapter extends BaseAdapter {

    protected Context context;
    protected ArrayList<Power> powers;
    protected int powersSize;

    public DialogAdapter(Context c, ArrayList<Power> powers) {
        context = c;
        this.powers = powers;
        powersSize = powers == null ? 0 : powers.size();
    }

    @Override
    public int getCount() {
        return powersSize;
    }

    @Override
    public Object getItem(int position) {
        return powers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(context);
        textView.setText(powers.get(position).getName());
        textView.setTypeface(GameApplication.getApplication().font);
        textView.setPadding(4, 4, 4, 4);
        return textView;
    }
}
