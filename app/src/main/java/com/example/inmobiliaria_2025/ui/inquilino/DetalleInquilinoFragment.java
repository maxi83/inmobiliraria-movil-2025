package com.example.inmobiliaria_2025.ui.inquilino;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.inmobiliaria_2025.R;
import com.example.inmobiliaria_2025.model.Inquilino;

public class DetalleInquilinoFragment extends Fragment {

    private TextView tvNombre, tvApellido, tvDni, tvTelefono, tvEmail;

    private Inquilino inquilino;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detalle_inquilino, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvNombre = view.findViewById(R.id.tvNombreDetalle);
        tvApellido = view.findViewById(R.id.tvApellidoDetalle);
        tvDni = view.findViewById(R.id.tvDniDetalle);
        tvTelefono = view.findViewById(R.id.tvTelefonoDetalle);
        tvEmail = view.findViewById(R.id.tvEmailDetalle);

        // Obtener inquilino del bundle
        if (getArguments() != null) {
            inquilino = (Inquilino) getArguments().getSerializable("inquilino");
        }

        if (inquilino != null) {
            tvNombre.setText(inquilino.getNombre() != null ? inquilino.getNombre() : "Desconocido");
            tvApellido.setText(inquilino.getApellido() != null ? inquilino.getApellido() : "Desconocido");
            tvDni.setText(inquilino.getDni() != null ? inquilino.getDni() : "Desconocido");
            tvTelefono.setText(inquilino.getTelefono() != null ? inquilino.getTelefono() : "Desconocido");
            tvEmail.setText(inquilino.getEmail() != null ? inquilino.getEmail() : "Desconocido");
        }
    }
}
