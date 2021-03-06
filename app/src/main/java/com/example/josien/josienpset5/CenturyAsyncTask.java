package com.example.josien.josienpset5;

/* Josien Jansen
*  11162295
*  Universiteit van Amsterdam
*/

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/*
This Activity handles the situations the data is going through with the four methods: onPreExecute,
doinBackground, onProgressUpdate and onPostExecute.
 */

public class CenturyAsyncTask extends AsyncTask<String, Integer, String> {

    Context context;
    MainActivity activity;

    // constructor
    public CenturyAsyncTask(MainActivity activity){
        this.activity = activity;
        this.context = this.activity.getApplicationContext();
    }

    @Override
    protected void onPreExecute(){
        // inform user
        Toast.makeText(context, "Getting data from server", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected String doInBackground(String... params){
        // fetch data
        return HttpRequestHelper.downloadFromServer(params);
    }

    @Override
    protected void onProgressUpdate(Integer...values){
        super.onProgressUpdate(values);
    }

    /*
    This method handles the Data after the request has been made, when there is no data found,
    inform the user and otherwise parse JSON to get the data the right way.
     */
    @Override
    protected void onPostExecute(String result){
        super.onPostExecute(result);

        // if nothing was found, inform user
        if (result.length() == 0){
            Toast.makeText(context, "No data was found", Toast.LENGTH_SHORT).show();
        }

        else {
            // Parse JSON
            ArrayList<CenturyData> museumData = new ArrayList<>();
            try {
                JSONObject respObj = new JSONObject(result);
                JSONArray art_objects = respObj.getJSONArray("artObjects");

                // Loop through the items till it ends
                for (int i = 0; i < art_objects.length(); i++) {
                    JSONObject tijd = art_objects.getJSONObject(i);
                    String title = tijd.getString("title");
                    String producer = tijd.getString("principalOrFirstMaker");
                    String longtitle = tijd.getString("longTitle");
                    String productionplace = tijd.getString("productionPlaces");
                    museumData.add(new CenturyData(title, producer, longtitle, productionplace));
                }
            }
            catch (JSONException e){
                e.printStackTrace();
            }

            // set the parsed data into MainActivity
            this.activity.setData(museumData);
        }
    }
}
