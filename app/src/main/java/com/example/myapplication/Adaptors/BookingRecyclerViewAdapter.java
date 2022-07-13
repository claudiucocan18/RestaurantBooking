package com.example.myapplication.Adaptors;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.entities.Rezervare;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BookingRecyclerViewAdapter extends RecyclerView.Adapter<BookingRecyclerViewAdapter.MyViewHolder>{

    private static final String TAG ="BookingRecyclerViewAdapter";
    Context context;
    public List<Rezervare> listaRezervari;


    public BookingRecyclerViewAdapter(Context context, ArrayList<Rezervare> listaRezervari) {
        this.context = context;
        this.listaRezervari = listaRezervari;

    }

    @NonNull
    @Override
    public BookingRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.booking_row,parent,false);
        return new BookingRecyclerViewAdapter.MyViewHolder(view,listaRezervari);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingRecyclerViewAdapter.MyViewHolder holder, int position) {


        holder.textRezNume.setText(listaRezervari.get(position).getNumeRestaurant());
        holder.textRezNrPersoane.setText(listaRezervari.get(position).getNrPersoane());
        holder.textRezOra.setText(listaRezervari.get(position).getOra());
        holder.textRezData.setText(listaRezervari.get(position).getData());
        holder.textRezAdresa.setText(listaRezervari.get(position).getAdresaRestaurant());
        holder.textStareRez.setText(listaRezervari.get(position).getStare());
        holder.textTelefonRez.setText(listaRezervari.get(position).getTelefon());


        switch (holder.textStareRez.getText().toString()){
            case "Pending approval":{
                holder.textStareRez.setTextColor(Color.parseColor("#e8b82a"));
                break;
            }
            case "Approved":{
                holder.textStareRez.setTextColor(Color.parseColor("#17ad03"));
                break;
            }
            case "Denied":{
                holder.textStareRez.setTextColor(Color.parseColor("#a10015"));
                break;
            }
        }

        if(listaRezervari.get(position).getStare().equals("Denied")) {
            holder.butonAnulareRezervare.setVisibility(View.GONE);

        }

    }

    @Override
    public int getItemCount() {

        return listaRezervari.size();

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textRezNume, textRezNrPersoane, textRezOra, textRezData,
                textRezAdresa,textStareRez, textTelefonRez;
        Button butonAnulareRezervare;


        public MyViewHolder(@NonNull View itemView, @NonNull List<Rezervare> listaRezervari) {
            super(itemView);

            textRezNrPersoane= itemView.findViewById(R.id.textRezNrPersoane) ;
            textRezNume = itemView.findViewById(R.id.textRezNume) ;
            textRezOra= itemView.findViewById(R.id.textRezOra) ;
            textRezData = itemView.findViewById(R.id.textRezData) ;
            textRezAdresa = itemView.findViewById(R.id.textRezAdresa);
            textStareRez = itemView.findViewById(R.id.textStareRez);
            textTelefonRez = itemView.findViewById(R.id.textTelefonRez);

            butonAnulareRezervare = itemView.findViewById(R.id.butonAnulareRezervare);


            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("Rezervare");

            butonAnulareRezervare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Rezervare r = listaRezervari.get(BookingRecyclerViewAdapter.MyViewHolder.this.getLayoutPosition());

                    myRef.getRef().addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                                if(postSnapshot.getKey() == r.getKey())//puteam pune if null
                                {
                                    postSnapshot.getRef().removeValue();
                                    listaRezervari.removeAll(listaRezervari);

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



}
