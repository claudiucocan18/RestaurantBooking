package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
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
    List<Masa> listaMese = new ArrayList<Masa>();

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


      /*  db.collection("Mese")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error != null)
                        {
                            Log.e("Firestore error",error.getMessage());
                            return;
                        }

                        for(DocumentChange dc2 : value.getDocumentChanges())
                        {
                            if(dc2.getType() == DocumentChange.Type.ADDED)
                            {
                                //dc2.getDocument().getData();
                                listaMese.add(dc2.getDocument().toObject(Masa.class));
                            }

                        }
                    }
                });
*/
/*

        db.collection("Mese")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                Log.d(TAG, document.getId() + " => " + document.getData());
                                Masa m = document.toObject(Masa.class);
                                listaMese.add(m);
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
*/


        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adaugareRestaurant();

    }


    private void adaugareRestaurant()
    {
        List<Masa> listaMese2 = new ArrayList<Masa>();
        Masa m1 = new Masa(true,2);
        Masa m2 = new Masa(true,3);
        Masa m3 = new Masa(true,4);
        Masa m4 = new Masa(true,5);

        listaMese2.add(m1);
        listaMese2.add(m2);
        listaMese2.add(m3);
        listaMese2.add(m4);

        Restaurant restaurant2= new Restaurant("https://firebasestorage.googleapis.com/v0/b/restaurantappusers-adf69.appspot.com/o/default.png?alt=media&token=c37046ed-cb42-4c0a-8674-4269ff5034f5"
                ,"w",3,"e","d",listaMese2);
        listaRestaurante.add(restaurant2);

        Restaurant restaurant3= new Restaurant("https://firebasestorage.googleapis.com/v0/b/restaurantappusers-adf69.appspot.com/o/restaurant-and-bar-logo-and-element_173667-original%20(1).jpg?alt=media&token=4d6d7893-b26c-484a-a279-652bd6c93d0c"
                ,"west",3,"e","d",listaMese2);
        listaRestaurante.add(restaurant3);
        Restaurant restaurant4= new Restaurant("https://firebasestorage.googleapis.com/v0/b/restaurantappusers-adf69.appspot.com/o/default.png?alt=media&token=c37046ed-cb42-4c0a-8674-4269ff5034f5"
                ,"w",3,"e","d",listaMese2);
        listaRestaurante.add(restaurant4);

        Restaurant restaurant5= new Restaurant("https://firebasestorage.googleapis.com/v0/b/restaurantappusers-adf69.appspot.com/o/restaurant-and-bar-logo-and-element_173667-original%20(1).jpg?alt=media&token=4d6d7893-b26c-484a-a279-652bd6c93d0c"
                ,"west",5,"e","d",listaMese2);
        listaRestaurante.add(restaurant3);


    }


}