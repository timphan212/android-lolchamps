package com.tutorial.tim.lolchamps;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChampSelectInfoStats extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;

    private OnChampStatsListener mListener;

    // TODO: Rename and change types and number of parameters
    public static ChampSelectInfoStats newInstance(String param1) {
        ChampSelectInfoStats fragment = new ChampSelectInfoStats();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public ChampSelectInfoStats() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_champ_select_info_stats, container, false);
        List<String> statsArray = parseChampStats();
        ListView lv = (ListView) v.findViewById(R.id.listView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, statsArray);
        lv.setAdapter(arrayAdapter);

        return v;
    }

    private List<String> parseChampStats() {
        List<String> statsArray = new ArrayList<>();
        Gson gson = new Gson();
        ChampionInfo.Champion champInfo = gson.fromJson(mParam1, ChampionInfo.Champion.class);

        statsArray.add("Armor: " + champInfo.stats.armor + " (+" + champInfo.stats.armorperlevel + " per level)");
        statsArray.add("Attack Damage: " + champInfo.stats.attackdamage + " (+" + champInfo.stats.attackdamageperlevel + " per level)");
        statsArray.add("Attack Range: " + champInfo.stats.attackrange);
        statsArray.add("Attackspeed: " + champInfo.stats.attackspeedoffset + " (+" + champInfo.stats.attackspeedperlevel + "% per level)");
        statsArray.add("Critical Strike: " + champInfo.stats.crit + " (+" + champInfo.stats.critperlevel + " per level)");
        statsArray.add("Health: " + champInfo.stats.hp  + " (+" + champInfo.stats.hpperlevel + " per level)");
        statsArray.add("Health Regen: " + champInfo.stats.hpregen + " (+" + champInfo.stats.hpregenperlevel + " per level)");
        statsArray.add("Move Speed: " + champInfo.stats.movespeed);
        statsArray.add("Mana: " + champInfo.stats.mp + " (+" + champInfo.stats.mpperlevel + " per level)");
        statsArray.add("Mana Regen: " + champInfo.stats.mpregen + " (+" + champInfo.stats.mpregenperlevel + " per level)");
        statsArray.add("Magic Resist: " + champInfo.stats.spellblock + " (+" + champInfo.stats.spellblockperlevel + " per level)");

        return statsArray;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(String text) {
        if (mListener != null) {
            mListener.onChampStatsInteraction(text);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnChampStatsListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnChampStatsListener {
        // TODO: Update argument type and name
        public void onChampStatsInteraction(String text);
    }

}
