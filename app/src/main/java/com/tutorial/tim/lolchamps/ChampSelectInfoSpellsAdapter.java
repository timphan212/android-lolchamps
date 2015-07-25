package com.tutorial.tim.lolchamps;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tim on 7/23/2015.
 */
public class ChampSelectInfoSpellsAdapter extends BaseAdapter {
    Context mContext;
    List<ChampSelectInfoSpells.ChampSpellItems> champSpells = new ArrayList<>();

    public ChampSelectInfoSpellsAdapter(Context context, List<ChampSelectInfoSpells.ChampSpellItems> spells) {
        mContext = context;
        champSpells = spells;
    }

    @Override
    public int getCount() {
        return 5;
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
        String url;
        TextView spellName, spellCD, spellRange, spellCost, spellButton, spellDescrip;
        ImageView spellImage;

        if(champSpells.get(position).button.compareTo("Passive") == 0) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.champ_select_info_passive_list_item, null);
            url = "http://ddragon.leagueoflegends.com/cdn/5.14.1/img/passive/" + champSpells.get(position).key;
            spellName = (TextView) convertView.findViewById(R.id.passive_name);
            spellName.setText(champSpells.get(position).name);
            spellButton = (TextView) convertView.findViewById(R.id.passive_button);
            spellButton.setText(champSpells.get(position).button);
            spellDescrip = (TextView) convertView.findViewById(R.id.passive_description);
            spellDescrip.setText(Html.fromHtml(champSpells.get(position).description));
            spellImage = (ImageView) convertView.findViewById(R.id.passive_image);
        }
        else {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.champ_select_info_spells_list_item, null);
            url = "http://ddragon.leagueoflegends.com/cdn/5.14.1/img/spell/" + champSpells.get(position).key;
            spellName = (TextView) convertView.findViewById(R.id.spell_name);
            spellName.setText(champSpells.get(position).name);
            spellCD = (TextView) convertView.findViewById(R.id.spell_cd);
            spellCD.setText("Cooldown: " + champSpells.get(position).cooldown);
            spellRange = (TextView) convertView.findViewById(R.id.spell_range);
            spellRange.setText("Range: " + champSpells.get(position).range);
            spellCost = (TextView) convertView.findViewById(R.id.spell_cost);
            spellCost.setText("Cost: " + champSpells.get(position).cost);
            spellButton = (TextView) convertView.findViewById(R.id.spell_button);
            spellButton.setText(champSpells.get(position).button);
            spellDescrip = (TextView) convertView.findViewById(R.id.spell_description);
            spellDescrip.setText(Html.fromHtml(champSpells.get(position).description));
            spellImage = (ImageView) convertView.findViewById(R.id.spell_image);
        }

        Picasso.with(mContext)
                .load(url)
                .resize(120,120)
                .placeholder(R.drawable.champ_icon)
                .error(R.drawable.champ_icon)
                .into(spellImage);

        return convertView;
    }
}
