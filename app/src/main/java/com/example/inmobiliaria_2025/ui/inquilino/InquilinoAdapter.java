package com.example.inmobiliaria_2025.ui.inquilino;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.inmobiliaria_2025.R;
import com.example.inmobiliaria_2025.model.Inmueble;

import java.util.ArrayList;
import java.util.List;

public class InquilinoAdapter extends RecyclerView.Adapter<InquilinoAdapter.InmuebleViewHolder> {

    public interface OnItemClickListener {
        void onVerClick(Inmueble inmueble);
    }

    private List<Inmueble> inmuebles;
    private OnItemClickListener listener;

    public InquilinoAdapter(OnItemClickListener listener) {
        this.inmuebles = new ArrayList<>();
        this.listener = listener;
    }

    public void setInmuebles(List<Inmueble> inmuebles) {
        this.inmuebles = inmuebles != null ? inmuebles : new ArrayList<>();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public InmuebleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_inmueble, parent, false);
        return new InmuebleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InmuebleViewHolder holder, int position) {
        Inmueble inmueble = inmuebles.get(position);

        holder.tvDireccion.setText(inmueble.getDireccion());

        if (inmueble.getImagen() != null && !inmueble.getImagen().isEmpty()) {
            Glide.with(holder.itemView.getContext())
                    .load("https://inmobiliariaulp-amb5hwfqaraweyga.canadacentral-01.azurewebsites.net/" + inmueble.getImagen())
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(holder.ivFoto);
        } else {
            holder.ivFoto.setImageResource(R.drawable.ic_launcher_background);
        }

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onVerClick(inmueble);
        });
    }

    @Override
    public int getItemCount() {
        return inmuebles.size();
    }

    static class InmuebleViewHolder extends RecyclerView.ViewHolder {
        ImageView ivFoto;
        TextView tvDireccion;

        public InmuebleViewHolder(@NonNull View itemView) {
            super(itemView);
            ivFoto = itemView.findViewById(R.id.ivFoto);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
        }
    }
}
