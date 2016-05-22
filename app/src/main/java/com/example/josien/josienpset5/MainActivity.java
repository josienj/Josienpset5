package com.example.josien.josienpset5;

/* Josien Jansen
*  11162295
*  Universiteit van Amsterdam
*/

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/*
MainActivity handles the listview whereby the data parsed from the Rijksmuseum will be shown
correctly. It also handles the Spinner that will make the century in the Spinner to a real string
and put it behind the http-code so that the data will be parsed correctly.
 */

public class MainActivity extends AppCompatActivity {

    // Declare variables & JSON node keys
    ListView items_listview;
    private static final String TAG_TITLE = "Title";
    private static final String TAG_PRODUCER = "Producer";
    private static final String TAG_LONGTITLE = "Long-title";
    private static final String TAG_PLACE = "Productionplace";
    private Button searchButton;
    private Spinner centurySpinner;

    private InputMethodManager inputMethodManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the right items in the MainActivity
        items_listview = (ListView) findViewById(R.id.list);
        this.centurySpinner = (Spinner) this.findViewById(R.id.centuries);
        this.searchButton = (Button)this.findViewById(R.id.get_data);
        this.inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        this.searchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Handles the CenturySpinner whereby it will converted into a String
                inputMethodManager.hideSoftInputFromWindow(searchButton.getWindowToken(), 0);
                CenturyAsyncTask centuryTask = new CenturyAsyncTask(MainActivity.this);
                try {
                    TextView txtView  =  (TextView)centurySpinner.getSelectedView();
                    String centuryTxt = txtView.getText().toString();
                    centuryTask.execute(centuryTxt);
                }
                catch (Exception e)
                {
                    centuryTask.cancel(true);
                }
            }
        });

        ListView lv = getListView();

        // Handles the more information when clicking on a single item
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // Getting values from selected ListItem
                String title = ((TextView) view.findViewById(R.id.title))
                        .getText().toString();
                String producer = ((TextView) view.findViewById(R.id.producer))
                        .getText().toString();
                String long_title = ((TextView) view.findViewById(R.id.long_title))
                        .getText().toString();
                String production_place = ((TextView) view.findViewById(R.id.production_place))
                        .getText().toString();

                // Starting the MoreInformationActivity
                Intent in = new Intent(getApplicationContext(),
                        MoreInformationActivity.class);
                in.putExtra(TAG_TITLE, title);
                in.putExtra(TAG_PRODUCER, producer);
                in.putExtra(TAG_LONGTITLE, long_title);
                in.putExtra(TAG_PLACE, production_place);
                startActivity(in);

            }
        });
    }

    private ListView getListView() {
        return items_listview;
    }

    /*
     Set the Data parsed by JSON into the Listview
     */
    public void setData(ArrayList<CenturyData> centuryDatas){
        CenturyAdapter adapter = new CenturyAdapter(this, centuryDatas);
        items_listview.setAdapter(adapter);
    }
}

