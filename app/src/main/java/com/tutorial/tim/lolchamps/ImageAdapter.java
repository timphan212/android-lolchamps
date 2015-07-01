package com.tutorial.tim.lolchamps;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Tim on 6/11/2015.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> champNames;
    public ImageAdapter(Context c, List<String> champNames) {
        mContext = c;
        this.champNames = champNames;
    }

    @Override
    public int getCount() {
        //retrieve count of current champions
        return champNames.size();
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
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

       grid = new ImageView(mContext);
       grid = inflater.inflate(R.layout.grid_single, null);
       TextView textView = (TextView) grid.findViewById(R.id.grid_text);
       ImageView imageView = (ImageView)grid.findViewById(R.id.grid_image);
       textView.setText(champNames.get(position));
       String url = "http://ddragon.leagueoflegends.com/cdn/5.10.1/img/champion/" + champNames.get(position) + ".png";
       Picasso.with(mContext)
                 .load(url)
                 .resize(120,120)
                 .placeholder(R.drawable.champ_icon)
                 .error(R.drawable.champ_icon)
                 .into(imageView);

        return grid;
    }
}
