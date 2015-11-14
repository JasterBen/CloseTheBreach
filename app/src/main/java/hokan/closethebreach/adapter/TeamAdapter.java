package hokan.closethebreach.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import hokan.closethebreach.GameApplication;
import hokan.closethebreach.R;

/**
 * Created by Utilisateur on 14/11/2015.
 */
public class TeamAdapter extends BaseAdapter {

    private final static int COUNT = 5;

    protected Context context;

    public TeamAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View character;
        if (convertView == null)
            character = LayoutInflater.from(context).inflate(R.layout.character_card, parent, false);
        else
            character = convertView;

        Typeface font = GameApplication.getApplication().font;

        TextView character_number = (TextView) character.findViewById(R.id.character_number_textview);
        String number = context.getString(R.string.team_character_number);
        String num = " " + String.valueOf(position + 1);
        character_number.setText(number + num);
        character_number.setTypeface(font);
        ImageView character_image = (ImageView) character.findViewById(R.id.character_image);
        character_image.setImageResource(R.drawable.shield);

        return character;
    }
}
