package com.example.myapplication;

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
}
