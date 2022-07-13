package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ManagerBookingsActivity extends AppCompatActivity {

    MaterialButtonToggleGroup btnGroupManager2;
    Button btnHomeManager2, btnBookingsManager2,btnProfileManager2;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Rezervare");
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;
    int gasit;
    Restaurant rp;
    Restaurant restaurantGasit = new Restaurant();

    ArrayList<Rezervare> listaRez = new ArrayList<Rezervare>();
    ManagerBookingRecyclerViewAdapter managerAdapterBooking = new ManagerBookingRecyclerViewAdapter(this,listaRez);

    String adresaRestaurant;
    String numeRestaurant;
    String data;
    String ora;
    String nrPersoane;
    String user;
    String stare;
    String telefon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Fooder Manager");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_bookings);

        mAuth = FirebaseAuth.getInstance();
        RecyclerView managerBookingRecycler;

        btnGroupManager2 = findViewById(R.id.btnGroupManager2);
        btnHomeManager2 = findViewById(R.id.btnHomeManager2);
        btnBookingsManager2 = findViewById(R.id.btnBookingsManager2);
        btnProfileManager2 = findViewById(R.id.btnProfileManager2);
//cautam restaurantul user-ului logat

        db.collection("restaurante")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if(error != null)
                        {
                            Log.e("Firestore error",error.getMessage());
                            return;
                        }

                        gasit=0;
                        for(DocumentChange dc : value.getDocumentChanges())
                        {
                            if(gasit!=0){
                                break;
                            }

                            if(dc.getType() == DocumentChange.Type.ADDED)
                            {
                                rp = dc.getDocument().toObject(Restaurant.class);

                                if(rp.getManager().equals(mAuth.getCurrentUser().getEmail()))
                                {
                                    gasit++;
                                    restaurantGasit=rp;
                                }
                            }
                        }
                    }
                });

//cautam rezervarea
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

                            case "stare":{
                                //rez.setUser(ds.getValue().toString());
                                stare=ds.getValue().toString();
                                break;
                            }

                            case "telefon":{
                                //rez.setUser(ds.getValue().toString());
                                telefon=ds.getValue().toString();
                                break;
                            }

                            default: break;

                        }
                    }

                    if(numeRestaurant.equals(restaurantGasit.getNume()))
                    {
                        Rezervare rezervareIdentificata =
                                new Rezervare(numeRestaurant, data, adresaRestaurant, ora, nrPersoane, user, telefon, postSnapshot.getKey());

                        rezervareIdentificata.setStare(stare);

                        listaRez.add(rezervareIdentificata);
                    }
                }

                managerAdapterBooking.notifyDataSetChanged();

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("tag", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });

        managerBookingRecycler = findViewById(R.id.bookingRecycler);
        managerBookingRecycler.setAdapter(managerAdapterBooking);
        managerBookingRecycler.setLayoutManager(new LinearLayoutManager(this));


        btnGroupManager2.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                switch(checkedId)
                {
                    case R.id.btnHomeManager2:{

                        Intent BookingsManagerIntent2 = new Intent(group.getContext(),ManagerHomeActivity.class);
                        BookingsManagerIntent2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(BookingsManagerIntent2);
                        break;
                    }
                    case R.id.btnProfileManager2: {

                        Intent ProfileManagerIntent2 = new Intent(group.getContext(),ManagerViewProfileActivity.class);
                        ProfileManagerIntent2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(ProfileManagerIntent2);
                        break;

                    }
                }
            }
        });

    }
}