package com.tutorial.tim.lolchamps;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ChampSelect extends ActionBarActivity implements AsyncResponse {
    List<String> champNames = new ArrayList<>();
    List<String> champLores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champ_select);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        retrieveJSON("https://global.api.pvp.net/api/lol/static-data/na/v1.2/champion?locale=en_US&champData=all&api_key=f23647de-97c3-4cf7-b8d3-f35956a574d9");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if(id == R.id.action_search) {
            //call some sort of search function here
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void retrieveJSON(String url) {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()) {
            MyAsyncTask asyncTask = new MyAsyncTask(this);
            asyncTask.execute(url);
        }
        else {
            //User has no internet
        }
    }

    @Override
    public void processFinish(String output) {
        Gson gson = new Gson();
        final ChampionInfo.ChampionList champListObj = gson.fromJson(output, ChampionInfo.ChampionList.class);
        parseChampNames(champListObj);
        parseChampLore(champListObj);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this, champNames));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String selectedChampJSON = getSelectedChampInfo(champListObj, champNames.get(position));
                Intent intent = new Intent(ChampSelect.this, ChampSelectInfoTab.class);
                intent.putExtra("com.tutorial.tim.lolchamps.NAME", champNames.get(position));
                intent.putExtra("com.tutorial.tim.lolchamps.LORE", champLores.get(position));
                intent.putExtra("com.tutorial.tim.lolchamps.CHAMPINFO", selectedChampJSON);
                startActivity(intent);
            }
        });
    }

    private void parseChampNames(ChampionInfo.ChampionList champListObj) {
        for(Map.Entry<String, String> entry : champListObj.keys.entrySet()) {
            champNames.add(entry.getValue());
        }

        Collections.sort(champNames);
    }

    private void parseChampLore(ChampionInfo.ChampionList champListObj) {
        for(String champ : champNames) {
            champLores.add(champListObj.data.get(champ).lore);
        }
    }

    private String getSelectedChampInfo(ChampionInfo.ChampionList champListObj, String name) {
        ChampionInfo.Champion selectedChamp = champListObj.data.get(name);
        Gson gson = new Gson();

        return gson.toJson(selectedChamp);
    }
}
