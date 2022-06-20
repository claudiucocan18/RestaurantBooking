package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ReservationActivity extends AppCompatActivity {

    TextView textViewNume, textViewAdresa, textViewOrar;
    ImageView imageView4;
    CalendarView calendarView;
    Spinner spinnerMese, spinnerOre;
    Button butonRezerva;
    List<Integer> vect = new ArrayList<Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        Restaurant restaurant = (Restaurant) getIntent().getParcelableExtra("restaurant");

        calendarView = (CalendarView) findViewById(R.id.calendarView);
        spinnerMese = (Spinner) findViewById(R.id.spinnerMese);
        spinnerOre = (Spinner) findViewById(R.id.spinnerOre);
        imageView4 = (ImageView) findViewById(R.id.imageView4);
        textViewNume = (TextView) findViewById(R.id.textViewNume);
        textViewAdresa = (TextView) findViewById(R.id.textViewAdresa);
        textViewOrar = (TextView) findViewById(R.id.textViewOrar);
        butonRezerva = findViewById(R.id.butonRezerva);

        StorageReference storageRef; //image storage


        textViewNume.setText(restaurant.nume);
        textViewAdresa.setText(restaurant.adresa);
        textViewOrar.setText(restaurant.orar);

        Picasso.get().load(restaurant.getImgURL())
                .placeholder(R.drawable.default_vector_placeholder)
                .into(imageView4);

        //ArrayAdapter aa = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,vect);
        ArrayAdapter<CharSequence> adapterOre = ArrayAdapter.createFromResource(this,R.array.arrayOre, android.R.layout.simple_spinner_item);
        adapterOre.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> adapterMese = ArrayAdapter.createFromResource(this,R.array.arrayMese, android.R.layout.simple_spinner_item);
        adapterMese.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinnerOre.setAdapter(adapterOre);
        spinnerMese.setAdapter(adapterMese);


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
                   String ora =  adapterView.getSelectedItem().toString();
                System.out.println("Oraaaaaaaaaaaaaaaaaaa: "+ora);
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