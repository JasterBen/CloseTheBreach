package hokan.closethebreach.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import hokan.closethebreach.GameActivity;
import hokan.closethebreach.GameApplication;
import hokan.closethebreach.R;
import hokan.closethebreach.creatures.Hero;
import hokan.closethebreach.fragment.TeamFragment;

/**
 * Created by bmeunier on 17/11/15.
 */
public class GameAdapter extends BaseAdapter {

    protected GameActivity activity;
    protected Hero[] heroes;

    public GameAdapter(GameActivity gameActivity) {
        this.activity = gameActivity;
        int[] heroesPos = activity.getHeroes();
        int length = heroesPos.length;
        heroes = new Hero[TeamAdapter.HERONUMBER];
        int j = 0;
        for (int i = 0; i < length; i++)
        {
            if (heroesPos[i] != -1)
            {
                heroes[j] = GameApplication.getApplication().getHeroes().get(heroesPos[i]);
                j++;
            }
        }
        for (; j < TeamAdapter.HERONUMBER; j++)
            heroes[j] = null;
    }

    @Override
    public int getCount() {
        return heroes.length;
    }

    @Override
    public Hero getItem(int position) {
        return heroes[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View heroView;
        if (convertView == null)
            heroView = LayoutInflater.from(activity).inflate(R.layout.game_hero_card, parent, false);
        else
            heroView = convertView;

        Typeface font = GameApplication.getApplication().font;
        Hero hero = getItem(position);

        ImageView heroImage = (ImageView) heroView.findViewById(R.id.game_hero_card_image);
        if (hero != null)
            heroImage.setImageResource(hero.getImage());

        TextView heroHp = (TextView) heroView.findViewById(R.id.game_hero_card_hp);
        heroHp.setTypeface(font);
        if (hero != null)
            heroHp.setText(hero.getCurrentHp() + "/" + hero.getHp());

        return heroView;
    }
}
