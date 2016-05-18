package com.example.josien.josienpset5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText station_input;
    ListView trains_listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        station_input = (EditText) findViewById(R.id.edit_text);
        trains_listview = (ListView) findViewById(R.id.listView);
    }

    public void get_data(View view){

        String input = station_input.getText().toString();

        TrainAsyncTask asyncTask = new TrainAsyncTask(this);
        asyncTask.execute(input);
    }

    public void setData(ArrayList<TrainData> traindata){
        TrainAdapter adapter = new TrainAdapter(this, traindata);
        trains_listview.setAdapter(adapter);

    }
}
