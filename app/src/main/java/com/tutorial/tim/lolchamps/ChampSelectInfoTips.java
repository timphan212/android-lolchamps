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

public class ChampSelectInfoTips extends Fragment {
    private static final String ARG_PARAM1 = "param1";

    private String mParam1;

    private OnChampionTipsListener mListener;

    public static ChampSelectInfoTips newInstance(String param1) {
        ChampSelectInfoTips fragment = new ChampSelectInfoTips();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public ChampSelectInfoTips() {
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
        String tips = parseChampTips();
        View v = inflater.inflate(R.layout.fragment_champ_select_info_tips, container, false);
        TextView tv = (TextView) v.findViewById(R.id.tipsText);
        tv.setMovementMethod(new ScrollingMovementMethod());
        tv.setText(Html.fromHtml(tips));

        return v;
    }

    private String parseChampTips() {
        Gson gson = new Gson();
        ChampionInfo.Champion champInfo = gson.fromJson(mParam1, ChampionInfo.Champion.class);

        return "Ally Tips: " + champInfo.allytips + "<BR><BR>Enemy Tips: " + champInfo.enemytips;
    }

    public void onButtonPressed(String text) {
        if (mListener != null) {
            mListener.onChampionTipsInteraction(text);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnChampionTipsListener) activity;
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
    public interface OnChampionTipsListener {
        public void onChampionTipsInteraction(String text);
    }

}
