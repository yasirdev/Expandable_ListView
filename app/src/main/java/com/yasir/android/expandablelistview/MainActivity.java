package com.yasir.android.expandablelistview;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ExpandableListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private int lastExpandedPosition = -1;

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    private static final String TAG = "MainActivity";
    public static String jsonData = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    OkHttpClient okHttpClient = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://beatphile.com/webservices/charts.php")
                            .build();
                    Response response = okHttpClient.newCall(request).execute();

                    String dataRes = response.body().string();


                   //Log.d(TAG, ""+dataRes);
                    getKeyArray(dataRes,"rock",0);
                    getKeyArray(dataRes,"pop",1);
                    getKeyArray(dataRes,"jazz",2);
                    getKeyArray(dataRes,"rap",3);
                    getKeyArray(dataRes,"blues",4);
                    getKeyArray(dataRes,"top_beatphilers",5);
                    /*
                    * listDataHeader.add("rock");
        listDataHeader.add("pop");
        listDataHeader.add("jazz");
        listDataHeader.add("rap");
        listDataHeader.add("blues");
        listDataHeader.add("top_beatphilers");
                    * */

                    //jsonData = response.body().string();

                    return response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();

    }

    public void getKeyArray(String dataRes,String objtype,int index) {
        try {

            JSONObject jobey = new JSONObject(dataRes);

            System.out.println(objtype + " - - objtype");
            JSONArray getArray = jobey.getJSONArray(objtype);
            List<String> type = new ArrayList<String>();
            for (int a = 0; a < getArray.length(); a++){
                String artist = getArray.getJSONObject(a).getString("artist");
                String artwork = getArray.getJSONObject(a).getString("artwork");
                //Log.d(TAG, "getKeyArray: artist - - "+objtype + " ^ ^ ^ " + artist);
                type.add(artist);
            }
            Log.d(TAG, "getKeyArray: type = " + type);
            listDataChild.put(listDataHeader.get(index), type);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getPosition(int groupPosition){
        System.out.println(lastExpandedPosition + " - - - check");
        if (lastExpandedPosition != -1
                && groupPosition != lastExpandedPosition) {

            expListView.collapseGroup(lastExpandedPosition);
        }
        lastExpandedPosition = groupPosition;
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        //JSONArray jsonArray = new JSONArray(Arrays.asList(dataResult));
        //JSONObject Jobject=null;




        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("rock");
        listDataHeader.add("pop");
        listDataHeader.add("jazz");
        listDataHeader.add("rap");
        listDataHeader.add("blues");
        listDataHeader.add("top_beatphilers");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("The Shawshank Redemption");
        top250.add("The Godfather");
        top250.add("The Godfather: Part II");
        top250.add("Pulp Fiction");
        top250.add("The Good, the Bad and the Ugly");
        top250.add("The Dark Knight");
        top250.add("12 Angry Men");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("The Conjuring");
        nowShowing.add("Despicable Me 2");
        nowShowing.add("Turbo");
        nowShowing.add("Grown Ups 2");
        nowShowing.add("Red 2");
        nowShowing.add("The Wolverine");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("2 Guns");
        comingSoon.add("The Smurfs 2");
        comingSoon.add("The Spectacular Now");
        comingSoon.add("The Canyons");
        comingSoon.add("Europa Report");

        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
        listDataChild.put(listDataHeader.get(3), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(4), nowShowing);
        listDataChild.put(listDataHeader.get(5), comingSoon);
    }
}
