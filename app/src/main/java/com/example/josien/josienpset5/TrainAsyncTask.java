package com.example.josien.josienpset5;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Josien on 18-5-2016.
 */
public class TrainAsyncTask extends AsyncTask<String, Integer, String> {

    Context context;
    MainActivity activity;

    // constructor
    public TrainAsyncTask(MainActivity activity){
        this.activity = activity;
        this.context = this.activity.getApplicationContext();
    }

    @Override
    protected void onPreExecute(){
        // inform user
        Toast.makeText(context, "Getting data from server", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected String doInBackground(String...params){
        // fetch data
        return HttpRequestHelper.downloadFromServer(params);
    }

    @Override
    protected void onProgressUpdate(Integer...values){
        super.onProgressUpdate(values);
        //update if necessary
    }

    @Override
    protected void onPostExecute(String result){
        super.onPostExecute(result);

        // if nothing was found, inform user
        if (result.length() == 0){
            Toast.makeText(context, "No data was found", Toast.LENGTH_SHORT).show();
        }

        else {
            ArrayList<TrainData> trainData = new ArrayList<>();
            try {
                JSONObject respObj = new JSONObject(result);
                JSONObject actuele_vertrektijd = respObj.getJSONObject("ActueleVertrekTijd");
                JSONArray vertrekkende_treinen = actuele_vertrektijd.getJSONArray("VertrekkendeTrein");

                for (int i = 0; i < vertrekkende_treinen.length(); i++) {
                    JSONObject tijd = vertrekkende_treinen.getJSONObject(i);
                    String vertrektijd = tijd.getString("VertrekTijd");
                    String eindbestemming = actuele_vertrektijd.getString("EindBestemming");
                    trainData.add(new TrainData(vertrektijd, eindbestemming));
                }
            }
            catch (JSONException e){
                e.printStackTrace();
            }
        }

        // else, parse JSON
    }
}
