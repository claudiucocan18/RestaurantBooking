package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewBookingsActivity extends AppCompatActivity {
    String adresaRestaurant;
    String numeRestaurant;
    String data;
    String ora;
    String nrPersoane;
    String user;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bookings);


        MaterialButtonToggleGroup btnGroup2;
        Button btnHome2,btnBookings2, btnProfile2;

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Rezervare");

        ArrayList<Rezervare> listaRez = new ArrayList<Rezervare>();

        BookingRecyclerViewAdapter adapterBooking = new BookingRecyclerViewAdapter(this,listaRez);

        Rezervare rezervareRemove = (Rezervare) getIntent().getParcelableExtra("rezervareRemove");

        btnGroup2 = findViewById(R.id.btnGroup2);
        btnHome2 = findViewById(R.id.btnHome2);
        btnBookings2 = findViewById(R.id.btnBookings2);
        btnProfile2 = findViewById(R.id.btnProfile2);
        RecyclerView bookingRecycler;


        myRef.getRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    for(DataSnapshot ds : postSnapshot.getChildren())
                    {
                        switch (ds.getKey()) {
                            case "adresaRestaurant":{
                                //rez.setAdresaRestaurant(ds.getValue().toString());
                                adresaRestaurant=ds.getValue().toString();
                                break;
                            }
                            case "data":{
                               // rez.setData(ds.getValue().toString());
                                data=ds.getValue().toString();
                                break;
                            }
                            case "nrPersoane":{
                                //rez.setNrPersoane(ds.getValue().toString());
                                nrPersoane=ds.getValue().toString();
                                break;
                            }
                            case "numeRestaurant":{
                                //rez.setNumeRestaurant(ds.getValue().toString());
                                numeRestaurant=ds.getValue().toString();
                                break;
                            }
                            case "ora":{
                               // rez.setOra(ds.getValue().toString());
                                ora=ds.getValue().toString();
                                break;
                            }
                            case "user":{
                                //rez.setUser(ds.getValue().toString());
                                user=ds.getValue().toString();
                                break;
                            }

                            default: break;

                        }
                    }
                    //Rezervare rez = new Rezervare(numeRestaurant, data, adresaRestaurant, ora, nrPersoane,user);

                     if(mAuth.getCurrentUser().getEmail().equals(user))
                        {
                            listaRez.add(new Rezervare(numeRestaurant, data, adresaRestaurant, ora, nrPersoane, user, postSnapshot.getKey()));
                        }
                }

                adapterBooking.notifyDataSetChanged();
                System.out.println(listaRez);

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("tag", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });



        btnGroup2.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                switch(checkedId)
                {
                    case R.id.btnHome2:{

                        Intent HomeIntent2 = new Intent(group.getContext(),ListActivity.class);
                        HomeIntent2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(HomeIntent2);
                        break;
                    }
                    case R.id.btnProfile2: {

                        Intent ProfileIntent2 = new Intent(group.getContext(),ViewProfileActivity.class);
                        ProfileIntent2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(ProfileIntent2);
                        break;

                    }

                }
            }
        });

        bookingRecycler = findViewById(R.id.bookingRecycler);
        bookingRecycler.setAdapter(adapterBooking);
        bookingRecycler.setLayoutManager(new LinearLayoutManager(this));



    }

    @Override
    public void onBackPressed() {
        // Do nothing
    }

}