package com.example.dolfin_expenese_tracker_app;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

public class Statistic extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Statistics");


    }

}