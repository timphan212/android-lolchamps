package com.tutorial.tim.lolchamps;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ChampSelectInfoSpells extends Fragment {
    private static final String ARG_PARAM1 = "param1";

    private String mParam1;

    private OnChampionSpellsListener mListener;

    public static ChampSelectInfoSpells newInstance(String param1) {
        ChampSelectInfoSpells fragment = new ChampSelectInfoSpells();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public ChampSelectInfoSpells() {
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
        List<ChampSpellItems> champSpells = parseChampSpells();
        View v = inflater.inflate(R.layout.fragment_champ_select_info_spells, container, false);
        ListView lv = (ListView) v.findViewById(R.id.listView2);
        lv.setAdapter(new ChampSelectInfoSpellsAdapter(getActivity(), champSpells));
        return v;
    }

    private List<ChampSpellItems> parseChampSpells () {
        int count = 0;
        List<ChampSpellItems> spellInfo = new ArrayList<>();
        Gson gson = new Gson();
        ChampionInfo.Champion champPassiveObj = gson.fromJson(mParam1, ChampionInfo.Champion.class);
        ChampionInfo.Champion champInfoObj = gson.fromJson(mParam1, ChampionInfo.Champion.class);

        ChampSpellItems passiveObj = new ChampSpellItems();
        passiveObj.button = "Passive";
        passiveObj.cooldown = "";
        passiveObj.cost = "";
        passiveObj.description = champPassiveObj.passive.description;
        passiveObj.name = champPassiveObj.passive.name;
        passiveObj.range = "";
        passiveObj.key = champPassiveObj.passive.image.full;
        spellInfo.add(passiveObj);

        for(ChampionInfo.ChampSpells spell : champInfoObj.spells) {
            ChampSpellItems spellObj = new ChampSpellItems();
            spellObj.cost = parseSpellDescription(spell.resource, spell);
            spellObj.description = parseSpellDescription(spell.tooltip, spell);
            spellObj.cooldown = spell.cooldownBurn;
            spellObj.name = spell.name;
            spellObj.range = spell.rangeBurn;
            spellObj.key = spell.image.full;
            switch(count) {
                case 0:
                    spellObj.button = "Q";
                    break;
                case 1:
                    spellObj.button = "W";
                    break;
                case 2:
                    spellObj.button = "E";
                    break;
                case 3:
                    spellObj.button = "R";
                    break;
                default:
                    spellObj.button = "";
                    break;
            }

            count++;
            spellInfo.add(spellObj);
        }

        return spellInfo;
    }

    private String parseSpellDescription(String str, ChampionInfo.ChampSpells spell) {
        String edittedStr = str;

        for(int i = 1; i < 11; i++) {
            if(edittedStr.contains("{{ e" + i + " }}")) {
                if(spell.key.compareTo("AatroxR") == 0 && i == 7) {
                    edittedStr = edittedStr.replaceAll("\\{\\{ e" + i + " \\}\\}", "20");
                }
                else {
                    edittedStr = edittedStr.replaceAll("\\{\\{ e" + i + " \\}\\}", spell.effectBurn.get(i));
                }
            }
        }
        for(int i = 1; i < 3; i++) {
            if(edittedStr.contains("{{ a" + i + " }}")) {
                for(ChampionInfo.ChampSpellVars var : spell.vars) {
                    if(var.key.compareTo("a" + i) == 0) {
                        List<Double> list = var.coeff;
                        for(int j = 0; j < list.size(); j++) {
                            list.set(j, list.get(j) * 100);
                        }
                        edittedStr = edittedStr.replaceAll("\\{\\{ a" + i + " \\}\\}", TextUtils.join("/", list) + "%");
                        break;
                    }
                }
            }
        }
        for(int i = 1; i < 7; i++) {
            if(edittedStr.contains("{{ f" + i + " }}")) {
                for(ChampionInfo.ChampSpellVars var : spell.vars) {
                    if(var.key.compareTo("f" + i) == 0) {
                        List<Double> list = var.coeff;
                        for(int j = 0; j < list.size(); j++) {
                            list.set(j, list.get(j) * 100);
                        }
                        edittedStr = edittedStr.replaceAll("\\{\\{ f" + i + " \\}\\}", TextUtils.join("/", list) + "%");
                        break;
                    }
                }
            }
        }
        if(edittedStr.contains("{{ cost }}")) {
            edittedStr = edittedStr.replaceAll("\\{\\{ cost \\}\\}", spell.costBurn);
        }
        if(edittedStr.contains("{{ maxammo }}")) {
            edittedStr = edittedStr.replaceAll("\\{\\{ maxammo \\}\\}", "2");
        }

        return edittedStr;
    }

    public void onButtonPressed(String text) {
        if (mListener != null) {
            mListener.onChampionSpellsInteraction(text);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnChampionSpellsListener) activity;
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
    public interface OnChampionSpellsListener {
        public void onChampionSpellsInteraction(String text);
    }

    class ChampSpellItems {
        String button;
        String name;
        String cooldown;
        String range;
        String cost;
        String description;
        String key;
    }
}

