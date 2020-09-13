package com.example.android.weatherapp;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class NetworkUtils {
    private static final String BASE_URL1 = "https://api.openweathermap.org/data/2.5/weather";
    private static final String APP_ID = "APPID";
    private static final String CITY_NAME = "q", CITY_ID = "id";
    private static final String UNITS = "units";

    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();
    private static final String SUCESS = "SANIDHYA";


    static String currentWeather(String queryCity) {
        String appid = "6d1fe6a76e348085510bdc74e0a34255";
        String unit = "metric";
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String currentJSONString = null;
        try {
            Uri builtUri = Uri.parse(BASE_URL1).buildUpon().appendQueryParameter(CITY_NAME, queryCity)
                    .appendQueryParameter(APP_ID, appid).appendQueryParameter(UNITS, unit).build();
            URL requesturl = new URL(builtUri.toString());

            urlConnection = (HttpURLConnection) requesturl.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            Log.d("data", "CONNECTED");
            InputStream inputStream = urlConnection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder builder = new StringBuilder();

            String line;

            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
                Log.d(SUCESS,"WINS");
            }

            if (builder.length() == 0)
                return null;

            currentJSONString = builder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }
        Log.d(LOG_TAG, currentJSONString);
        return currentJSONString;
    }
}
