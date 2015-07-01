package com.tutorial.tim.lolchamps;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Tim on 6/16/2015.
 */

public class MyAsyncTask extends AsyncTask<String, Void, String> {
    private String jsonType;
    public AsyncResponse delegate;

    public MyAsyncTask(AsyncResponse delegate, String str) {
        this.delegate = delegate;
        this.jsonType = str;
    }

    @Override
    protected String doInBackground(String... params) {
        BufferedReader reader;
        String jsonText = "";
        try {
            URL url = new URL(params[0]);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = reader.readLine();

            while (line != null)
            {
                jsonText += line;
                line = reader.readLine();
            }
        } catch (Exception e) {
            return "Invalid URL.";
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonText;
    }

    @Override
    protected void onPostExecute(String str) {
        if(this.jsonType.compareTo("champInfo") == 0) {
            parseChampInfo(str);
        }
    }

    private void parseChampInfo(String str) {
        Gson gson = new Gson();
        ChampionInfo.ChampionList champListObj = gson.fromJson(str, ChampionInfo.ChampionList.class);
        List<String> list = new ArrayList<>();

        for(Map.Entry<String, String> entry : champListObj.keys.entrySet()) {
            list.add(entry.getValue());
        }

        Collections.sort(list);
        delegate.processFinish(list);
    }
}
