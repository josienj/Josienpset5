package com.example.josien.josienpset5;

/* Josien Jansen
*  11162295
*  Universiteit van Amsterdam
*/

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/*
This Activity makes the HTTP request at the API of the Rijksmuseum, it reads the inputted century
and handles the ResponseCode at which the CenturyAsyncTask goes further.
 */

public class HttpRequestHelper {

    // make string for URL
    private static final String url = "https://www.rijksmuseum.nl/api/nl/collection?key=PdSdcK0U&format=json&f.dating.period=";

    // method to download from server
    protected static synchronized String downloadFromServer(String... params) {

        // declare return string result
        String result = "";

        // get chosen tag from argument
        String chosenStation = params[0];

        // complete string for URL
        String complete_URL_string = url + chosenStation;
        // turn string into URL
        URL url = null;

        try {
            url = new URL(complete_URL_string);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // make the connection
        HttpURLConnection connection;
        if (url != null)
            try {
                connection = (HttpURLConnection) url.openConnection();

                // open connection, set request method
                connection.setRequestMethod("GET");

                // get response code
                Integer responseCode = connection.getResponseCode();

                // if responseCode is between 200-300, read inputstream
                if (200 >= responseCode && responseCode <= 299) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        result = result + line;
                    }
                }
                // else, read error stream
                else {
                    Log.d(" ", "downloadFromServer() returned: " + responseCode);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        return result;
    }

}