package com.example.josien.josienpset5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Josien on 18-5-2016.
 */
public class HttpRequestHelper {

    // make string for URL
    private static final String url = "http://webservices.ns.nl/ns-api-avt?station=";

    // method to download from server
    protected static synchronized String downloadFromServer(String...params) {

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

                // if 200-300, read inputstream
                if (200 <= responseCode && responseCode >= 299){
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;
                    while ((line = bufferedReader.readLine()) != null){
                        result = result + line;
                    }
                }
                // else, read error stream
                else {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                    //communicate further
                }

            } catch (IOException e){
                e.printStackTrace();
            }
        // return result
        return result;
    }
}
