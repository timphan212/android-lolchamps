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
    public AsyncResponse delegate;

    public MyAsyncTask(AsyncResponse delegate) {
        this.delegate = delegate;
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
        delegate.processFinish(str);
    }
}
