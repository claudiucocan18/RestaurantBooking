package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import javax.xml.transform.Source;

public class ReservationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        Restaurant r1 = (Restaurant) getIntent().getParcelableExtra("restaurant");

        System.out.println(r1);
    }
}