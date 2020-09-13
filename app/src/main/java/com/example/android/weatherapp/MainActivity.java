package com.example.android.weatherapp;

import android.app.ActionBar;
import android.app.SearchManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.provider.ContactsContract.CommonDataKinds.StructuredPostal.CITY;

public class MainActivity extends AppCompatActivity {
    private static final int GET_QUERY = 1;
    private static final String CITYName = "Present city";
    private TextView cityName;
    private static final String CURR_TEMP = "Saved Temp";
    private static final String CURR_TEMP_MIN = "Saved Min Temp";
    private static final String CURR_TEMP_MAX = "Saved Max temp";
    private static final String MAIN_DESCRIPTION = "Saved main Heading";
    private static final String MAIN_DESCRIPTION2 = "Saved Description";
    private static final String[] DETAIL_BOX = new String[5];
    private TextView currentTempMain;
    private TextView currenttempMin;
    private TextView currentTempMax;
    private String queryCity;
    private TextView[] textViewArray = new TextView[2];
    private TextView[] detailBoxArray = new TextView[5];
    private TextView date;
    private TextView wind;
    /**private SharedPreferences mainPreference;
    private String sharedPrefFile = "Current Activity Data";


    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor mainSharedprefEditor = mainPreference.edit();
        mainSharedprefEditor.putString(CITYName, cityName.getText().toString());
        mainSharedprefEditor.putString(CURR_TEMP, currentTempMain.getText().toString());
        mainSharedprefEditor.putString(CURR_TEMP_MIN, currenttempMin.getText().toString());
        mainSharedprefEditor.putString(CURR_TEMP_MAX, currentTempMax.getText().toString());
        mainSharedprefEditor.putString(MAIN_DESCRIPTION, textViewArray[0].getText().toString());
        mainSharedprefEditor.putString(MAIN_DESCRIPTION2, textViewArray[1].getText().toString());
        mainSharedprefEditor.putString(DETAIL_BOX[0], detailBoxArray[0].getText().toString());
        mainSharedprefEditor.putString(DETAIL_BOX[1], detailBoxArray[1].getText().toString());
        mainSharedprefEditor.putString(DETAIL_BOX[2], detailBoxArray[2].getText().toString());
        mainSharedprefEditor.putString(DETAIL_BOX[3], detailBoxArray[3].getText().toString());
        mainSharedprefEditor.putString(DETAIL_BOX[4], detailBoxArray[4].getText().toString());
        mainSharedprefEditor.apply();

    }**/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);


        /** mainPreference = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);**/


        currentTempMain = findViewById(R.id.textView_test_temp);
        currenttempMin = findViewById(R.id.currentMinTemp);
        currentTempMax = findViewById(R.id.currentMaxTemp);
        //textViewArray[0] = findViewById(R.id.mainHeading);
        textViewArray[1] = findViewById(R.id.currentDescription);
        detailBoxArray[0] = findViewById(R.id.currentHumidity);
        detailBoxArray[1] = findViewById(R.id.currentPressure);
        detailBoxArray[2] = findViewById(R.id.currentUvIndex);
        detailBoxArray[3] = findViewById(R.id.currentVisibility);
        detailBoxArray[4] = findViewById(R.id.currentClouds);
        date =findViewById(R.id.date);
        cityName = findViewById(R.id.city_textView);
        wind=findViewById(R.id.windSpeedTextView);
        /**{
         cityName.setText(mainPreference.getString(CITYName, " "));
         currentTempMain.setText(mainPreference.getString(CURR_TEMP, " "));
         currenttempMin.setText(mainPreference.getString(CURR_TEMP_MIN, " "));
         currentTempMax.setText(mainPreference.getString(CURR_TEMP_MAX, " "));
         textViewArray[0].setText(mainPreference.getString(MAIN_DESCRIPTION, " "));
         textViewArray[1].setText(mainPreference.getString(MAIN_DESCRIPTION2, " "));
         detailBoxArray[0].setText(mainPreference.getString(DETAIL_BOX[0], " "));
         detailBoxArray[1].setText(mainPreference.getString(DETAIL_BOX[1], " "));
         detailBoxArray[2].setText(mainPreference.getString(DETAIL_BOX[2], " "));
         detailBoxArray[3].setText(mainPreference.getString(DETAIL_BOX[3], " "));
         detailBoxArray[4].setText(mainPreference.getString(DETAIL_BOX[4], " "));
         }**/

     /*   DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    */
    }
    public void startSearch(View view) {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivityForResult(intent, GET_QUERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_QUERY) {
            if (resultCode == RESULT_OK) {
                queryCity = data.getStringExtra("queryData");
                test();
            }
        }
    }

    public void test() {

        new DataLoad(cityName, currentTempMain, currenttempMin, currentTempMax, textViewArray[1],
                detailBoxArray[0], detailBoxArray[1], detailBoxArray[2], detailBoxArray[3], detailBoxArray[4],wind)
                .execute(queryCity);

        //cityName.setVisibility(View.VISIBLE);
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        String dayOfTheWeek = sdf.format(d);

        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        date.setText(dayOfTheWeek+" - "+day+months[month]);
    }


}
