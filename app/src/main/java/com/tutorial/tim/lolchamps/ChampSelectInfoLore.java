package com.tutorial.tim.lolchamps;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

public class ChampSelectInfoLore extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;

    private OnChampionLoreListener mListener;

    public static ChampSelectInfoLore newInstance(String param1) {
        ChampSelectInfoLore fragment = new ChampSelectInfoLore();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public ChampSelectInfoLore() {
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
        String text = parseChampLoreText();
        View v = inflater.inflate(R.layout.fragment_champ_select_info_lore, container, false);
        TextView tv = (TextView) v.findViewById(R.id.loreText);
        tv.setMovementMethod(new ScrollingMovementMethod());
        tv.setText(Html.fromHtml(text));

        return v;
    }

    private String parseChampLoreText() {
        Gson gson = new Gson();
        ChampionInfo.Champion champInfo = gson.fromJson(mParam1, ChampionInfo.Champion.class);

        return champInfo.lore;
    }

    public void onButtonPressed(String text) {
        if (mListener != null) {
            mListener.onChampionLoreInteraction(text);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnChampionLoreListener) activity;
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnChampionLoreListener {
        public void onChampionLoreInteraction(String text);
    }

}
