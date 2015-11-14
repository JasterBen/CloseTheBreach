package hokan.closethebreach.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

import hokan.closethebreach.GameApplication;
import hokan.closethebreach.creatures.Hero;

/**
 * Created by Utilisateur on 14/11/2015.
 */
public class ImageAdapter extends BaseAdapter {

    protected Context context;
    protected ArrayList<Hero> heroes;
    protected int heroesSize;

    public ImageAdapter(Context context) {
        this.context = context;
        heroes = GameApplication.getApplication().getHeroes();
        heroesSize = heroes.size();
    }

    @Override
    public boolean isEnabled(int position) {
        return getItem(position).isEnable();
    }

    @Override
    public int getCount() {
        return heroesSize;
    }

    @Override
    public Hero getItem(int position) {
        return heroes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null)
        {
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(500, 500));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        else
            imageView = (ImageView) convertView;

        imageView.setImageResource(heroes.get(position).getImage());
        return imageView;
    }
}
