package com.example.josien.josienpset5;

/* Josien Jansen
*  11162295
*  Universiteit van Amsterdam
*/

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/*
This Activity handles the Adapter of the Century-information.
 */

public class CenturyAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<CenturyData> museumdata;

    public CenturyAdapter(Context context, ArrayList<CenturyData> data){
        this.context = context;
        this.museumdata = data;
    }

    @Override
    public int getCount() {
        return this.museumdata.size();
    }

    public Object getItem(int arg0){
        return null;
    }

    public long getItemId(int pos){
        return pos;
    }

    public View getView(int pos, View view, ViewGroup parent){
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.row_layout, parent, false);
        }
        CenturyData century = museumdata.get(pos);

        // get the position of the TextViews by row_layout
        TextView title = (TextView) view.findViewById(R.id.title);
        TextView producer = (TextView) view.findViewById(R.id.producer);
        TextView longtitle = (TextView) view.findViewById(R.id.long_title);
        TextView production_place = (TextView) view.findViewById(R.id.production_place);

        title.setText(century.getTitle());
        producer.setText(century.getProducer());
        longtitle.setText(century.getLongtitle());
        production_place.setText(century.getProductionplace());

        return view;
        }
    }


