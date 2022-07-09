package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ManagerBookingRecyclerViewAdapter extends RecyclerView.Adapter<ManagerBookingRecyclerViewAdapter.MyViewHolder>{

    private static final String TAG ="ManagerBookingRecyclerViewAdapter";
    Context context;
    public List<Rezervare> listaRezervari;


    public ManagerBookingRecyclerViewAdapter(Context context, ArrayList<Rezervare> listaRezervari) {
        this.context = context;
        this.listaRezervari = listaRezervari;

    }

    @NonNull
    @Override
    public ManagerBookingRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

//de schimbat layout
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.manager_booking_row,parent,false);
        return new ManagerBookingRecyclerViewAdapter.MyViewHolder(view,listaRezervari);
    }

    @Override
    public void onBindViewHolder(@NonNull ManagerBookingRecyclerViewAdapter.MyViewHolder holder, int position) {

//de modif view-uri
        holder.textManagerRezClient.setText(listaRezervari.get(position).getUser());
        holder.textManagerRezAdresa.setText(listaRezervari.get(position).getAdresaRestaurant());
        holder.textManagerRezData.setText(listaRezervari.get(position).getData());
        holder.textManagerRezOra.setText(listaRezervari.get(position).getOra());
        holder.textManagerRezNrPersoane.setText(listaRezervari.get(position).getNrPersoane());
        holder.textStare.setText(listaRezervari.get(position).getStare());


        switch (holder.textStare.getText().toString()){
            case "Pending":{
                holder.textStare.setTextColor(Color.parseColor("#e8b82a"));
                break;
            }
            case "Approved":{
                holder.textStare.setTextColor(Color.parseColor("#17ad03"));
                break;
            }
            case "Denied":{
                holder.textStare.setTextColor(Color.parseColor("#a10015"));
                break;
            }
        }

    }

    @Override
    public int getItemCount() {

        return listaRezervari.size();

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textManagerRezClient, textManagerRezAdresa,
                textManagerRezData, textManagerRezOra,
                textManagerRezNrPersoane, textStare;
        Button butonManagerRespingeRezervare, butonManagerAprobareRezervare;


        public MyViewHolder(@NonNull View itemView, @NonNull List<Rezervare> listaRezervari) {
            super(itemView);

            textManagerRezClient= itemView.findViewById(R.id.textManagerRezClient) ;
            textManagerRezAdresa = itemView.findViewById(R.id.textManagerRezAdresa) ;
            textManagerRezData= itemView.findViewById(R.id.textManagerRezData) ;
            textManagerRezOra = itemView.findViewById(R.id.textManagerRezOra) ;
            textManagerRezNrPersoane = itemView.findViewById(R.id.textManagerRezNrPersoane);
            textStare = itemView.findViewById(R.id.textStare);



            butonManagerRespingeRezervare = itemView.findViewById(R.id.butonManagerRespingeRezervare);
            butonManagerAprobareRezervare = itemView.findViewById(R.id.butonManagerAprobareRezervare);

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("Rezervare");

// butoanele pentru aprobarea/respingerea rezervarii de catre manager

            butonManagerRespingeRezervare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Rezervare r = listaRezervari.get(ManagerBookingRecyclerViewAdapter.MyViewHolder.this.getLayoutPosition());


                }
            });

            butonManagerAprobareRezervare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Rezervare r2 = listaRezervari.get(ManagerBookingRecyclerViewAdapter.MyViewHolder.this.getLayoutPosition());

                        myRef.addChildEventListener(new ChildEventListener() {
                                    @Override
                                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                    }

                                    @Override
                                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                    }

                                    @Override
                                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                                    }

                                    @Override
                                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                                myRef.getRef().addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                                    if(postSnapshot.getKey() == r2.getKey())//puteam pune if null
                                    {

                                        Map<String, Object> camp = new HashMap<String,Object>();
                                        camp.put("stare", "Approved");
                                        listaRezervari.removeAll(listaRezervari);
                                        postSnapshot.getRef().updateChildren(camp);
                                        goToLoginActivity(view.getContext());

                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                }
            });


        }
    }

    public static void goToLoginActivity(Context mContext) {
        Intent refreshRezStateIntent = new Intent(mContext, ManagerBookingsActivity.class);
        refreshRezStateIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        mContext.startActivity(refreshRezStateIntent);

    }

}
