package com.example.josien.josienpset5;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Josien on 18-5-2016.
 */
public class TrainAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<TrainData> trains;

    public TrainAdapter(Context context, ArrayList<TrainData> data){
        this.context = context;
        this.trains = data;
    }

    @Override
    public int getCount() {
        return this.trains.size();
    }

    public Object getItem(int arg0){
        return null;
    }

    public long getItemId(int pos){
        return pos;
    }

    public View getView(int pos, View view, ViewGroup parent){
        TrainData train = trains.get(pos);
        TextView vertrektijd = (TextView) view.findViewById(R.id.vertrektijd);
        TextView eindbestemming = (TextView) view.findViewById(R.id.eindbestemming);
        vertrektijd.setText(train.getVertrektijd());
        eindbestemming.setText(train.getEindbestemming());
        return view;
    }
}

