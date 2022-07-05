package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ReservationActivity extends AppCompatActivity {

    String data;
    String ora;
    String nrPersoane;
    String user;
    List<Integer> vect = new ArrayList<Integer>();

    TextView textViewNume, textViewAdresa, textViewOrar;
    ImageView imageView4;
    CalendarView calendarView;
    Spinner spinnerMese, spinnerOre;
    Button butonRezerva;


    Rezervare rezervare = new Rezervare();
    DaoRezervare daoRezervare = new DaoRezervare();
    Rezervare rez = new Rezervare();

    private FirebaseAuth mAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Rezervare");




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
        mAuth = FirebaseAuth.getInstance();

        user = mAuth.getCurrentUser().getEmail();
        if(user==null) {user="Anonymous";}

        textViewNume.setText(restaurant.nume);
        textViewAdresa.setText(restaurant.adresa);
        textViewOrar.setText(restaurant.orar);

        Picasso.get().load(restaurant.getImgURL())
                .placeholder(R.drawable.default_vector_placeholder)
                .into(imageView4);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        data = sdf.format(new Date(calendarView.getDate()));


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
                nrPersoane =  adapterView.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerOre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    ora =  adapterView.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        calendarView.setMinDate(Calendar.getInstance().getTimeInMillis() );

        calendarView.setDate(Calendar.getInstance().getTimeInMillis());
        //int zi = Calendar.getInstance().getTime().getDay();




        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {

                data = String.valueOf(i2)+"/"+String.valueOf(i1+1)+"/"+String.valueOf(i);
               // zi luna-1 returneaza el;

            }

        });



        butonRezerva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Rezervare rezervare = new Rezervare(restaurant.nume, data, restaurant.adresa, ora, nrPersoane, user);
                daoRezervare.add(rezervare).addOnSuccessListener(suc->
                {
                    Toast.makeText(ReservationActivity.this, "Rezervare inregistrata cu succes!", Toast.LENGTH_SHORT).show();
                    Intent ConfirmationIntent = new Intent(view.getContext(),ConfirmationActivity.class);
                    startActivity(ConfirmationIntent);
                });
            }
        });


/*

        myRef.getRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    for(DataSnapshot ds : postSnapshot.getChildren())
                    {
                         switch (ds.getKey()) {
                        case "adresaRestaurant": System.out.println(ds.getValue()); //rez.setAdresaRestaurant(ds.getValue().toString());

                         }


                          //merge
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("tag", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });

*/




    }
}