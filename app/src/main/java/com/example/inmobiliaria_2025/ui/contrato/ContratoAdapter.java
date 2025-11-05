package com.example.inmobiliaria_2025.ui.contrato;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.inmobiliaria_2025.R;
import com.example.inmobiliaria_2025.model.Contrato;

import java.util.ArrayList;
import java.util.List;

public class ContratoAdapter extends RecyclerView.Adapter<ContratoAdapter.ContratoViewHolder> {

    public interface OnContratoClickListener {
        void onContratoClick(Contrato contrato);
    }

    private List<Contrato> contratos;
    private Context context;
    private OnContratoClickListener listener;

    public ContratoAdapter(List<Contrato> contratos, Context context, OnContratoClickListener listener) {
        this.contratos = contratos != null ? contratos : new ArrayList<>();
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ContratoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_contrato, parent, false);
        return new ContratoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContratoViewHolder holder, int position) {
        Contrato contrato = contratos.get(position);

        if (contrato.getInmueble() != null && contrato.getInmueble().getImagen() != null) {
            String url = "https://inmobiliariaulp-amb5hwfqaraweyga.canadacentral-01.azurewebsites.net/"
                    + contrato.getInmueble().getImagen();
            Glide.with(context)
                    .load(url)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(holder.ivFoto);
        } else {
            holder.ivFoto.setImageResource(R.drawable.ic_launcher_background);
        }

        holder.tvDireccion.setText(
                contrato.getInmueble() != null ? contrato.getInmueble().getDireccion() : "Sin dirección"
        );

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onContratoClick(contrato);
        });
    }

    @Override
    public int getItemCount() {
        return contratos != null ? contratos.size() : 0;
    }

    static class ContratoViewHolder extends RecyclerView.ViewHolder {
        ImageView ivFoto;
        TextView tvDireccion;

        public ContratoViewHolder(@NonNull View itemView) {
            super(itemView);
            ivFoto = itemView.findViewById(R.id.ivFoto);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
        }
    }
}
