package com.example.inmobiliaria_2025.ui.pago;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inmobiliaria_2025.R;
import com.example.inmobiliaria_2025.model.Pago;

import java.util.ArrayList;
import java.util.List;

public class PagoAdapter extends RecyclerView.Adapter<PagoAdapter.PagoViewHolder> {

    private List<Pago> pagos;
    private Context context;

    public PagoAdapter(List<Pago> pagos, Context context) {
        this.pagos = pagos != null ? pagos : new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public PagoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pago, parent, false);
        return new PagoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PagoViewHolder holder, int position) {
        Pago pago = pagos.get(position);
        holder.tvNroPago.setText("Pago #" + pago.getNroPago());
        holder.tvMonto.setText("Monto: $" + pago.getMonto());
        holder.tvFechaPago.setText("Fecha: " + pago.fechaPagoFormateada());
    }

    @Override
    public int getItemCount() {
        return pagos.size();
    }

    static class PagoViewHolder extends RecyclerView.ViewHolder {
        TextView tvNroPago, tvMonto, tvFechaPago;

        public PagoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNroPago = itemView.findViewById(R.id.tvNroPago);
            tvMonto = itemView.findViewById(R.id.tvMonto);
            tvFechaPago = itemView.findViewById(R.id.tvFechaPago);
        }
    }
}
