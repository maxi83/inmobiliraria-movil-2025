package com.example.inmobiliaria_2025.ui.contratos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inmobiliaria_2025.R;
import com.example.inmobiliaria_2025.model.Pago; // Ajusta la ruta a tu modelo 'Pago'

import java.util.List;

// 1. Reemplazamos Inmueble por Pago y el nombre de la clase
public class PagosAdapter extends RecyclerView.Adapter<PagosAdapter.ViewHolderPago> {

    private List<Pago> listadoPagos;
    private Context context;
    private LayoutInflater li;

    public PagosAdapter(List<Pago> listadoPagos, Context context, LayoutInflater li) {
        this.context = context;
        this.listadoPagos = listadoPagos;
        this.li = li;
    }

    @NonNull
    @Override
    // 2. Usamos el nuevo layout item_pago.xml
    public ViewHolderPago onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Asegúrate de que este R.layout.item_pago apunte a tu XML adaptado
        View itemView = li.inflate(R.layout.item_pagos, parent, false);
        return new ViewHolderPago(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPago holder, int position) {

        Pago pagoActual = listadoPagos.get(position);

        // 3. Asignamos los datos del modelo Pago a los TextViews del layout
        holder.tvCodigoPago.setText("Código de pago: " + pagoActual.getIdPago());
        holder.tvNumeroPago.setText("Número de pago: " + pagoActual.getDetalle());
        holder.tvCodigoContrato.setText("Código de contrato: " + pagoActual.getIdContrato());

        // Formateo simple del importe. Considera usar NumberFormat para moneda real.
        holder.tvImportePago.setText("Importe: $" + String.format("%.2f", pagoActual.getMonto()));
        holder.tvFechaPago.setText("Fecha de pago: " + pagoActual.getFechaPago());


    }

    @Override
    public int getItemCount() {
        return listadoPagos.size();
    }

    // 4. Nuevo ViewHolder con los IDs del layout de pago
    public class ViewHolderPago extends RecyclerView.ViewHolder {
        TextView tvCodigoPago, tvNumeroPago, tvCodigoContrato, tvImportePago, tvFechaPago;
        CardView cardView;

        public ViewHolderPago(@NonNull View itemView) {
            super(itemView);
            // Mapeo de IDs del item_pago.xml
            tvCodigoPago = itemView.findViewById(R.id.tvCodigoPago);
            tvNumeroPago = itemView.findViewById(R.id.tvNumeroPago);
            tvCodigoContrato = itemView.findViewById(R.id.tvCodigoContrato);
            tvImportePago = itemView.findViewById(R.id.tvImportePago);
            tvFechaPago = itemView.findViewById(R.id.tvFechaPago);
            cardView = itemView.findViewById(R.id.idCardPago); // Usamos el ID del CardView del nuevo layout
        }
    }
}