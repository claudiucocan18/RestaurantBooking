package com.example.myapplication.DAO;

import com.example.myapplication.entities.Rezervare;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DaoRezervare {

private DatabaseReference databaseReference;

    public DaoRezervare() {

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Rezervare.class.getSimpleName());
    }

    public Task<Void> add(Rezervare rezervare)
    {

         return databaseReference.push().setValue(rezervare);

    }

    public Task<Void> remove(String key)
    {

        return databaseReference.child(key).removeValue();

    }



}
