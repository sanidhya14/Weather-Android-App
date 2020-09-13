package com.example.android.weatherapp;

import android.os.AsyncTask;
import android.text.Html;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.lang.ref.WeakReference;

public class DataLoad extends AsyncTask<String, Void, String> {
    private WeakReference<TextView> mainTemp;
    private WeakReference<TextView> curr_max_temp;
    private WeakReference<TextView> curr_min_temp;
    private WeakReference<TextView> descriptionHead;
    private WeakReference<TextView> description;
    private WeakReference<TextView> curr_humidity;
    private WeakReference<TextView> curr_pressure;
    private WeakReference<TextView> curr_uvIndex;
    private WeakReference<TextView> curr_visibility;
    private WeakReference<TextView> curr_clouds;
    private WeakReference<TextView> city_textView;
    private WeakReference<TextView> wind_textView;


    DataLoad(TextView city, TextView dataMain, TextView dataMin, TextView dataMax, TextView description2, TextView humidity,
             TextView pressure, TextView uvIndex, TextView visibility, TextView clouds,TextView wind) {
        this.city_textView = new WeakReference<>(city);
        this.mainTemp = new WeakReference<>(dataMain);
        this.curr_min_temp = new WeakReference<>(dataMin);
        this.curr_max_temp = new WeakReference<>(dataMax);
       // this.descriptionHead = new WeakReference<>(description1);
        this.description = new WeakReference<>(description2);
        this.curr_humidity = new WeakReference<>(humidity);
        this.curr_pressure = new WeakReference<>(pressure);
        this.curr_uvIndex = new WeakReference<>(uvIndex);
       // this.curr_visibility = new WeakReference<>(visibility);
        this.curr_clouds = new WeakReference<>(clouds);
        this.wind_textView = new WeakReference<>(wind);

    }

    @Override
    protected String doInBackground(String... strings) {
        return NetworkUtils.currentWeather(strings[0]);
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONObject main = jsonObject.getJSONObject("main");
            JSONArray jsonArrayweather = jsonObject.getJSONArray("weather");
            JSONObject weather = jsonArrayweather.getJSONObject(0);
            JSONObject cloudsJson = jsonObject.getJSONObject("clouds");
            JSONObject sysObj=jsonObject.getJSONObject("sys");
            JSONObject windObj=jsonObject.getJSONObject("wind");

            float visibility_in_m;
            Double tempConvert, tempConvert2, tempConvert3;
            long intTemp, intTemp2, intTemp3;
            String[] current_temp, descriptionsArray, detailsBoxArray;
            String temp = null, convert = null,wind;
            current_temp = new String[3];
            descriptionsArray = new String[7];
            detailsBoxArray = new String[5];

            tempConvert = main.getDouble("temp");
            intTemp = Math.round(tempConvert);

            current_temp[0] = Long.toString(intTemp);
            tempConvert2 = main.getDouble("temp_min");
            intTemp2 = Math.round(tempConvert2);
            current_temp[1] = Long.toString(intTemp2);
            tempConvert3 = main.getDouble("temp_max");
            intTemp3 = Math.round(tempConvert3);
            current_temp[2] = Long.toString(intTemp3);
           // descriptionsArray[0] = weather.getString("main");
            descriptionsArray[1] = weather.getString("description");
            detailsBoxArray[0] = main.getString("humidity");
            detailsBoxArray[1] = main.getString("pressure");
            detailsBoxArray[4] = cloudsJson.getString("all");
            city_textView.get().setText(jsonObject.getString("name"));
            wind_textView.get().setText(windObj.getString("speed"));
            wind_textView.get().append(" km/h");
           /* try {
                temp = jsonObject.getString("visibility");

                curr_visibility.get().setText(temp);
                curr_visibility.get().append(" m");

            } catch (JSONException e) {
                e.printStackTrace();
            }
             */

            if (current_temp[0] != null) {
                mainTemp.get().setText(current_temp[0]);
                curr_min_temp.get().setText(current_temp[1]);
                curr_max_temp.get().setText(current_temp[2]);

               // descriptionHead.get().setText(descriptionsArray[0]);
                description.get().setText(descriptionsArray[1]);

            } else
                mainTemp.get().setText("no result");


            if (detailsBoxArray[0] != null) {
                curr_humidity.get().setText(detailsBoxArray[0]);
                curr_humidity.get().append(" %");
            } else {
                curr_humidity.get().setText("No Data");
            }
            if (detailsBoxArray[1] != null) {
                curr_pressure.get().setText(detailsBoxArray[1]);
                curr_pressure.get().append(" hPa");
            } else {
                curr_pressure.get().setText("No Data");
            }
            if (detailsBoxArray[4] != null) {
                curr_clouds.get().setText(detailsBoxArray[4]);
                curr_clouds.get().append(" %");
            }

        } catch (JSONException e1) {
            e1.printStackTrace();
        }
    }
}
