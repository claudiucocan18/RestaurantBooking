package com.example.myapplication;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class DaoUtilizator {

    private DatabaseReference databaseReference;

    public DaoUtilizator() {

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Utilizator.class.getSimpleName());
    }

    public Task<Void> add(Utilizator utilizator)
    {

        return databaseReference.push().setValue(utilizator);

    }

    public Task<Void> remove(String key)
    {

        return databaseReference.child(key).removeValue();

    }

}
