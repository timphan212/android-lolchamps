package com.tutorial.tim.lolchamps;

import android.content.Context;
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

import java.util.List;

public class ChampSelect extends ActionBarActivity implements AsyncResponse {
    String[] champNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champ_select);
        retrieveJSON("https://global.api.pvp.net/api/lol/static-data/na/v1.2/champion?locale=en_US&champData=all&api_key=f23647de-97c3-4cf7-b8d3-f35956a574d9", "champInfo");
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

    private String[] setupChampNames() {
        return null;
    }

    public void retrieveJSON(String url, String jsonType) {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()) {
            MyAsyncTask asyncTask = new MyAsyncTask(this, jsonType);
            asyncTask.execute(url);
        }
        else {
            //User has no internet
        }
    }

    @Override
    public void processFinish(List<String> output) {
        for(String entry : output) {
            Log.w("champName", entry);
        }

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this, output));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(ChampSelect.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
