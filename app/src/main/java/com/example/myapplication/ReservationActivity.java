package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.transform.Source;

public class ReservationActivity extends AppCompatActivity {

    ///Calendar c;
    //CalendarView calendarView = new CalendarView(this);
    CalendarView calendarView;
    Spinner spinnerMese, spinnerOre;
    List<Integer> vect = new ArrayList<Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        Restaurant r1 = (Restaurant) getIntent().getParcelableExtra("restaurant");

        calendarView = (CalendarView) findViewById(R.id.calendarView);
        spinnerMese = (Spinner) findViewById(R.id.spinnerMese);
        spinnerOre = (Spinner) findViewById(R.id.spinnerOre);


        vect.add(1);
        vect.add(2);
        vect.add(3);

        ArrayAdapter aa = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,vect);


        spinnerMese.setAdapter(aa);
        spinnerOre.setAdapter(aa);

        spinnerMese.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerOre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        calendarView.setMinDate(Calendar.getInstance().getTimeInMillis() );

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {

                System.out.println(String.valueOf(i2)+String.valueOf(i1+1));

               // zi luna-1 returneaza el;

            }
        });






        //System.out.println(r1);
    }
}