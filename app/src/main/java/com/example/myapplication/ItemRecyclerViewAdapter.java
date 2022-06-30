package com.example.myapplication;

import android.content.Context;

import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class ItemRecyclerViewAdapter extends RecyclerView.Adapter<ItemRecyclerViewAdapter.MyViewHolder> {


    private static final String TAG ="ItemRecyclerViewAdapter";
    Context context;
    public List<Restaurant> listaRestaurante;

    public List<Restaurant> getListaRestaurante() {
        return listaRestaurante;
    }

    FirebaseStorage storage = FirebaseStorage.getInstance();
   // StorageReference storageRef = storage.getReferenceFromUrl("gs://restaurantappusers-adf69.appspot.com");


    public ItemRecyclerViewAdapter(Context context, ArrayList<Restaurant> listaRestaurante) {
        this.context = context;
        this.listaRestaurante = listaRestaurante;

    }

    @NonNull
    @Override
    public ItemRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row,parent,false);
        return new ItemRecyclerViewAdapter.MyViewHolder(view,listaRestaurante);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemRecyclerViewAdapter.MyViewHolder holder, int position) {


        holder.textNume.setText(listaRestaurante.get(position).getNume());

        holder.textAdresa.setText(listaRestaurante.get(position).getAdresa());
       // holder.textNrLocuri.setText(String.valueOf(listaRestaurante.get(position).getNr_locuri()));
        holder.textOrar.setText(listaRestaurante.get(position).getOrar());
        //holder.textZona.setText(listaRestaurante.get(position).getZona());


        StorageReference storageRef;//image storage
       // storageRef = storage.getReferenceFromUrl("gs://restaurantappusers-adf69.appspot.com")
        Picasso.get().load(listaRestaurante.get(position).getImgURL())
                .placeholder(R.drawable.default_vector_placeholder)
                .into(holder.imageView3);

    }

    @Override
    public int getItemCount() {

        return listaRestaurante.size();

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textNrLocuri, textNume, textOrar, textZona, textAdresa;
        ImageView imageView3;
        Button buttonRezerva;
        //////////////////////
        List<Restaurant> lrvh;

        public List<Restaurant> getLrvh() {
            return lrvh;
        }

        public MyViewHolder(@NonNull View itemView, @NonNull List<Restaurant> listaRestaurante) {
            super(itemView);

            lrvh = listaRestaurante;
            //textNrLocuri = itemView.findViewById(R.id.textNrLocuri) ;
            textNume = itemView.findViewById(R.id.textNume) ;
            textOrar= itemView.findViewById(R.id.textOrar) ;
            //textZona = itemView.findViewById(R.id.textZona) ;
            textAdresa = itemView.findViewById(R.id.textAdresa) ;
            imageView3 = itemView.findViewById(R.id.imageView3);
            buttonRezerva= itemView.findViewById(R.id.butonSpreRezervare);




buttonRezerva.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        Restaurant r = listaRestaurante.get(MyViewHolder.this.getLayoutPosition());

        Log.e("reservation Activity",r.nume+r.imgURL);
        //Log.e("reservation Activity",r.getMese().get(0).toString());

        Intent intent3 = new Intent(view.getContext(),ReservationActivity.class);
        intent3.putExtra("restaurant",(Parcelable) r );

        //intent3.putParcelableArrayListExtra("test", (ArrayList<Masa>) listaMese);
        view.getContext().startActivity(intent3);
    }
});
            }

    }

    }

