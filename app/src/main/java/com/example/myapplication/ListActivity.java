package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {


    private static final String TAG = "ListActivity" ;

    FirebaseFirestore db = FirebaseFirestore.getInstance(); //Cloud Firestore

    //DatabaseReference myRef = database.getReference("https://restaurantappusers-adf69-default-rtdb.europe-west1.firebasedatabase.app/");

    RecyclerView recyclerView;
    ArrayList<Restaurant> listaRestaurante = new ArrayList<Restaurant>();
    ItemRecyclerViewAdapter adapter = new ItemRecyclerViewAdapter(this,listaRestaurante);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Restaurant restaurant = new Restaurant();


        db.collection("restaurante")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
 
                        if(error != null)
                        {
                            Log.e("Firestore error",error.getMessage());
                            return;
                        }

                        for(DocumentChange dc : value.getDocumentChanges())
                        {
                            if(dc.getType() == DocumentChange.Type.ADDED)
                            {
                                listaRestaurante.add(dc.getDocument().toObject(Restaurant.class));
                            }
                            adapter.notifyDataSetChanged();

                        }
                    }
                });


        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adaugareRestaurant();

    }
    private void adaugareRestaurant()
    {
        Restaurant restaurant2= new Restaurant("https://firebasestorage.googleapis.com/v0/b/restaurantappusers-adf69.appspot.com/o/default.png?alt=media&token=c37046ed-cb42-4c0a-8674-4269ff5034f5"
                ,"w",3,"e","e","d");
        listaRestaurante.add(restaurant2);

        Restaurant restaurant3= new Restaurant("https://firebasestorage.googleapis.com/v0/b/restaurantappusers-adf69.appspot.com/o/restaurant-and-bar-logo-and-element_173667-original%20(1).jpg?alt=media&token=4d6d7893-b26c-484a-a279-652bd6c93d0c"
                ,"west",3,"e","e","d");
        listaRestaurante.add(restaurant3);
    }

    private void navigareRezervare()
    {
       listaRestaurante.get(0);
    }
}