package com.example.inmobiliaria_2025.ui.contrato;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;
import com.example.inmobiliaria_2025.R;
import com.example.inmobiliaria_2025.model.Contrato;
import com.example.inmobiliaria_2025.model.Inquilino;

public class DetalleContratoFragment extends Fragment {

    private TextView tvDireccion, tvEstado, tvValor, tvFechas;
    private ImageView ivFotoDetalle;
    private Button btnVerPagos, btnVerInquilino;
    private Contrato contrato;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detalle_contrato, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Referencias a los elementos
        tvDireccion = view.findViewById(R.id.tvDireccionDetalle);
        tvEstado = view.findViewById(R.id.tvEstadoDetalle);
        tvValor = view.findViewById(R.id.tvValorDetalle);
        tvFechas = view.findViewById(R.id.tvFechasDetalle);
        ivFotoDetalle = view.findViewById(R.id.ivFoto);
        btnVerPagos = view.findViewById(R.id.btnVerPagos);
        btnVerInquilino = view.findViewById(R.id.btnVerInquilino);

        // Recuperar contrato desde argumentos
        if (getArguments() != null) {
            contrato = (Contrato) getArguments().getSerializable("contrato");
        }

        // Mostrar datos del contrato
        if (contrato != null) {
            tvDireccion.setText(
                    contrato.getInmueble() != null && contrato.getInmueble().getDireccion() != null
                            ? contrato.getInmueble().getDireccion()
                            : "Sin dirección"
            );

            tvEstado.setText(contrato.isEstado() ? "Vigente" : "No vigente");
            tvValor.setText(String.valueOf(contrato.getMontoAlquiler()));

            String inicio = contrato.getFechaInicio() != null ? contrato.getFechaInicio() : "Desconocida";
            String fin = contrato.getFechaFinalizacion() != null ? contrato.getFechaFinalizacion() : "Desconocida";
            tvFechas.setText("Inicio: " + inicio + " | Fin: " + fin);

            // Cargar imagen con Glide
            if (contrato.getInmueble() != null && contrato.getInmueble().getImagen() != null) {
                Glide.with(this)
                        .load(contrato.getInmueble().getImagen())
                        .placeholder(R.drawable.ic_launcher_background)
                        .into(ivFotoDetalle);
            } else {
                ivFotoDetalle.setImageResource(R.drawable.ic_launcher_background);
            }
        }

        // Botón Ver Pagos
        btnVerPagos.setOnClickListener(v -> {
            if (contrato != null) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("contrato", contrato);
                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_detalleContratoFragment_to_pagosFragment, bundle);
            }
        });

        // Botón Ver Inquilino
        btnVerInquilino.setOnClickListener(v -> {
            if (contrato != null && contrato.getInquilino() != null) {
                Inquilino inquilino = contrato.getInquilino();
                Bundle bundle = new Bundle();
                bundle.putSerializable("inquilino", inquilino);
                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_detalleContratoFragment_to_detalleInquilinoFragment, bundle);
            } else {
                Log.d("DETALLE_CONTRATO", "⚠️ No hay inquilino asignado a este contrato.");
            }
        });
    }
}
