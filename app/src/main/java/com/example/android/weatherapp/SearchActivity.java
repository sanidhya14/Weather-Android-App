package com.example.android.weatherapp;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;

import java.util.Objects;

public class SearchActivity extends AppCompatActivity {
    private static final int RESULT_CODE = 1;
    private EditText search;
    private String query=null;

   // @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        search=findViewById(R.id.Search_bar);

    }

    public void returnQuery(View view) {
        query=search.getText().toString();
        Intent returnIntent=getIntent();
        returnIntent.putExtra("queryData",query);
        setResult(RESULT_OK,returnIntent);
        finish();
    }
}