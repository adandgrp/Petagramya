package com.example.rsc.myapplication.adapters;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rsc.myapplication.R;
import com.example.rsc.myapplication.db.ConstructorMascotas;
import com.example.rsc.myapplication.pojo.Mascota;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MascotaAdapter extends RecyclerView.Adapter<MascotaAdapter.MascotaViewHolder> {

    private ArrayList<Mascota> mascotas;
    private Activity activity;

    public static class MascotaViewHolder extends RecyclerView.ViewHolder{

        public ImageView imagen;
        public TextView nombre;
        public TextView numero;
        public ImageButton huesoLike;


        public MascotaViewHolder(View itemView) {
            super(itemView);
            imagen = (ImageView) itemView.findViewById(R.id.imagen_card);
            nombre = (TextView) itemView.findViewById(R.id.nombre_card);
            numero = (TextView) itemView.findViewById(R.id.numero_card);
            huesoLike = (ImageButton) itemView.findViewById(R.id.huesito_card);

        }
    }

    public MascotaAdapter(ArrayList<Mascota> mascotas, Activity activity) {
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mascota_card,parent,false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MascotaViewHolder holder, final int i) {
//        holder.imagen.setImageResource(mascotas.get(i).getImagen());
        Picasso.with(activity)
                .load(mascotas.get(i).getImagen())
                .placeholder(R.drawable.dog_footprint_48)
                .into(holder.imagen);
        holder.nombre.setText(mascotas.get(i).getNombre());
        holder.numero.setText(String.valueOf(mascotas.get(i).getLikes()));
        holder.huesoLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Diste Like a " + mascotas.get(i).getNombre()  , Snackbar.LENGTH_LONG)
                        .setAction(" ", null).show();
                ConstructorMascotas constructorMascotas = new ConstructorMascotas(activity);
                constructorMascotas.darLikeMascota(mascotas.get(i));
                mascotas.get(i).sumaLike();
                holder.numero.setText(String.valueOf(constructorMascotas.obtenerLikesMascota(mascotas.get(i))));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }


}
