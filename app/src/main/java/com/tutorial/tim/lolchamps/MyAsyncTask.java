package com.tutorial.tim.lolchamps;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tim on 6/16/2015.
 */
public class MyAsyncTask extends AsyncTask<String, Void, String> {

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
        Gson gson = new Gson();
        Champions.ChampionList champObj = gson.fromJson(str, Champions.ChampionList.class);
        List<Long> list = new ArrayList<>();
        for(Champions.ChampionInfo champ : champObj.champions) {
            list.add(champ.id);
        }

        for(int i = 0; i < list.size(); i++) {
            Log.w("champid", String.valueOf(list.get(i)));
        }
    }
}
