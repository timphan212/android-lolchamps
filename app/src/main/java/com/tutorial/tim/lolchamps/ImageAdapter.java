package com.tutorial.tim.lolchamps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Tim on 6/11/2015.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    public ImageAdapter(Context c) {
        mContext = c;
    }

    @Override
    public int getCount() {
        //retrieve count of current champions
        return 100;
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

        if (convertView == null) {
            grid = new ImageView(mContext);
            grid = inflater.inflate(R.layout.grid_single, null);
            TextView textView = (TextView) grid.findViewById(R.id.grid_text);
            ImageView imageView = (ImageView)grid.findViewById(R.id.grid_image);
            textView.setText("Aatrox");
            String url = "http://ddragon.leagueoflegends.com/cdn/5.10.1/img/champion/Aatrox.png";
            Picasso.with(mContext)
                    .load(url)
                    .resize(120,120)
                    .placeholder(R.drawable.sample_2)
                    .error(R.drawable.sample_2)
                    .into(imageView);
        } else {
            grid = convertView;
        }

        return grid;
    }
}
