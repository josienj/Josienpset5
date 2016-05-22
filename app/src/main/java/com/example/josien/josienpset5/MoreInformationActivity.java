package com.example.josien.josienpset5;

/* Josien Jansen
*  11162295
*  Universiteit van Amsterdam
*/

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/*
This Activity handles the 'More information' when clicking on a single item.
It shows the already shown information Title and Producer, but also the Longtitle and the
ProductionPlace.
 */

public class MoreInformationActivity extends Activity {

    // JSON node keys
    private static final String TAG_TITLE = "Title";
    private static final String TAG_PRODUCER = "Producer";
    private static final String TAG_LONGTITLE = "Long-title";
    private static final String TAG_PLACE = "Productionplace";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.row_layout2);

        // getting intent data
        Intent in = getIntent();

        // Get JSON values from previous intent
        String title = in.getStringExtra(TAG_TITLE);
        String producer = in.getStringExtra(TAG_PRODUCER);
        String long_title = in.getStringExtra(TAG_LONGTITLE);
        String production_place = in.getStringExtra(TAG_PLACE);

        // Displaying all values on the screen
        TextView title_art = (TextView) findViewById(R.id.title);
        TextView producer_art = (TextView) findViewById(R.id.producer);
        TextView longtitle_art= (TextView) findViewById(R.id.long_title);
        TextView productionplace_art= (TextView) findViewById(R.id.production_place);

        title_art.setText(title);
        producer_art.setText(producer);
        longtitle_art.setText(long_title);
        productionplace_art.setText(production_place);

    }
    /*
    This method goes to the beginscreen when clicking on the button
     */
    public void new_century(View view) {

        Intent backtoscreen = new Intent(this, MainActivity.class);

        backtoscreen.putExtra("backto_screen", 500);
        startActivity(backtoscreen);
    }
}
