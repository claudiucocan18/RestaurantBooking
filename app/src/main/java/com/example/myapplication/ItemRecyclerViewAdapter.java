package com.example.myapplication;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class ItemRecyclerViewAdapter extends RecyclerView.Adapter<ItemRecyclerViewAdapter.MyViewHolder> {


    private static final String TAG ="ItemRecyclerViewAdapter";
    Context context;
    List<Restaurant> listaRestaurante;

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
        return new ItemRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemRecyclerViewAdapter.MyViewHolder holder, int position) {




        holder.textNume.setText(listaRestaurante.get(position).getNume());
        Log.e("dddddddddd",holder.textNume.getText().toString());
        holder.textAdresa.setText(listaRestaurante.get(position).getAdresa());
        holder.textNrLocuri.setText(String.valueOf(listaRestaurante.get(position).getNr_locuri()));
        holder.textOrar.setText(listaRestaurante.get(position).getOrar());
        holder.textZona.setText(listaRestaurante.get(position).getZona());

        Log.e("dddddddddd",holder.textZona.getText().toString());
        StorageReference storageRef;//image storage
       // storageRef = storage.getReferenceFromUrl("gs://restaurantappusers-adf69.appspot.com")
        Picasso.get().load(listaRestaurante.get(position).getImgURL())
                .placeholder(R.drawable.default_vector_placeholder)
                .into(holder.imageView3);

        Log.e("dddddddddd",listaRestaurante.get(position).getImgURL());

    }

    @Override
    public int getItemCount() {
        return listaRestaurante.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textNrLocuri, textNume, textOrar, textZona, textAdresa;
        ImageView imageView3;
        Button buttonRezerva;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textNrLocuri = itemView.findViewById(R.id.textNrLocuri) ;
            textNume = itemView.findViewById(R.id.textNume) ;
            textOrar= itemView.findViewById(R.id.textOrar) ;
            textZona = itemView.findViewById(R.id.textZona) ;
            textAdresa = itemView.findViewById(R.id.textAdresa) ;

            imageView3 = itemView.findViewById(R.id.imageView3);

            buttonRezerva= itemView.findViewById(R.id.buttonRezerva);


        }
    }
}
