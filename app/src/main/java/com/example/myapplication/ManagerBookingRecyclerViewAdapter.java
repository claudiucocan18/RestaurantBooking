package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

        if(listaRezervari.get(position).getStare().equals("Approved")
                ||listaRezervari.get(position).getStare().equals("Denied")){
            holder.butonManagerAprobareRezervare.setVisibility(View.GONE);
            holder.butonManagerRespingeRezervare.setVisibility(View.GONE);

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
            DatabaseReference myRef2 = database.getReference("Rezervare");
// butoanele pentru aprobarea/respingerea rezervarii de catre manager


            butonManagerAprobareRezervare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Rezervare r2 = listaRezervari.get(ManagerBookingRecyclerViewAdapter.MyViewHolder.this.getLayoutPosition());

                    myRef.getRef().addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                                    if(postSnapshot.getKey() == r2.getKey())//puteam pune if !null
                                    {

                                        Map<String, Object> camp = new HashMap<String,Object>();
                                        camp.put("stare", "Approved");
                                        listaRezervari.removeAll(listaRezervari);
                                        postSnapshot.getRef().updateChildren(camp);
                                        refreshBookingActivity(view.getContext());
                                        /*butonManagerAprobareRezervare.setVisibility(View.INVISIBLE);
                                        butonManagerRespingeRezervare.setVisibility(View.INVISIBLE);*/

                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                }
            });

            butonManagerRespingeRezervare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {

                    Rezervare r3 = listaRezervari.get(ManagerBookingRecyclerViewAdapter.MyViewHolder.this.getLayoutPosition());

                    myRef2.getRef().addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                                if(postSnapshot.getKey() == r3.getKey())//puteam pune if null
                                {

                                    Map<String, Object> camp2 = new HashMap<String,Object>();
                                    camp2.put("stare", "Denied");
                                    listaRezervari.removeAll(listaRezervari);
                                    postSnapshot.getRef().updateChildren(camp2);
                                    refreshBookingActivity(view2.getContext());
                                   // new RefreshTask.execute(Void, Void, false);


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

    public static void refreshBookingActivity(Context mContext) {
        Intent refreshRezStateIntent = new Intent(mContext, ManagerBookingsActivity.class);
        refreshRezStateIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        mContext.startActivity(refreshRezStateIntent);


    }

    public class RefreshTask extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected Void doInBackground(Void... params)
        {
            while (!isCancelled())
            {
                //Getting data from server
                SystemClock.sleep(1000);
            }
            return null;
        }

       /* @Override
        protected void onPostExecute(Void result)
        {
            super.onPostExecute(result);

            Intent refreshRezStateIntent = new Intent(context, ManagerBookingsActivity.class);
            refreshRezStateIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            context.startActivity(refreshRezStateIntent);
        }*/
    }


}
