package com.example.inmobiliaria_2025.ui.inmueble;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.inmobiliaria_2025.R;
import com.example.inmobiliaria_2025.model.Inmueble;
import com.example.inmobiliaria_2025.request.ApiClient;

import java.util.List;

public class InmuebleAdapter extends RecyclerView.Adapter<InmuebleAdapter.InmuebleViewHolder> {
    private List<Inmueble> lista;
    private Context context;
    private OnInmuebleClickListener listener; // Listener para clicks

    // Interfaz para comunicar click al Fragment
    public interface OnInmuebleClickListener {
        void onInmuebleClick(Inmueble inmueble);
    }

    public InmuebleAdapter(List<Inmueble> lista, Context context, OnInmuebleClickListener listener) {
        this.lista = lista;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public InmuebleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.inmueble_card, parent, false);
        return new InmuebleViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull InmuebleViewHolder holder, int position) {
        Inmueble i = lista.get(position);
        holder.tvDireccion.setText(i.getDireccion());
        holder.tvTipo.setText(i.getTipo());
        holder.tvPrecio.setText(String.valueOf(i.getValor()));

        Glide.with(context)
                .load(ApiClient.BASE_URL + i.getImagen())
                .placeholder(R.drawable.ic_launcher_foreground)
                .error("null")
                .into(holder.imgInmueble);

        // Click en el card llama al listener
        holder.cardView.setOnClickListener(v -> listener.onInmuebleClick(i));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class InmuebleViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDireccion, tvTipo, tvPrecio;
        private ImageView imgInmueble;
        private CardView cardView;

        public InmuebleViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
            tvTipo = itemView.findViewById(R.id.tvTipo);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            imgInmueble = itemView.findViewById(R.id.imgInmueble);
            cardView = itemView.findViewById(R.id.idCard);
        }
    }
}
