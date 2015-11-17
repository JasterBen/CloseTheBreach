package hokan.closethebreach.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import hokan.closethebreach.GameApplication;
import hokan.closethebreach.R;
import hokan.closethebreach.creatures.Hero;

/**
 * Created by Utilisateur on 14/11/2015.
 */
public class TeamAdapter extends BaseAdapter {

    public static final int HERONUMBER = 5;

    protected Context context;
    protected int[] heroesSelected = new int[HERONUMBER];

    public TeamAdapter(Context context) {
        this.context = context;
        for (int i = 0; i < HERONUMBER; i++)
            setHero(i, -1);
    }

    @Override
    public int getCount() {
        return HERONUMBER;
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

        TextView character_name = (TextView) character.findViewById(R.id.character_name_textview);
        character_name.setTypeface(font);

        if (heroesSelected[position] == -1)
            character_image.setImageResource(R.drawable.shield);
        else
        {
            Hero hero = GameApplication.getApplication().getHeroes().get(heroesSelected[position]);
            character_image.setImageResource(hero.getImage());
            character_name.setText(hero.getName());
        }


        return character;
    }

    public int[] getHeroesSelected() {
        return heroesSelected;
    }

    public void setHeroesSelected (int[] heroes) {
        heroesSelected = heroes;
    }

    public void setHero(int position, int hero) {
        heroesSelected[position] = hero;
    }
}
