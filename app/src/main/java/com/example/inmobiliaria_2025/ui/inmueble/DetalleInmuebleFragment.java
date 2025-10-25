package com.example.inmobiliaria_2025.ui.inmueble;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.example.inmobiliaria_2025.R;
import com.example.inmobiliaria_2025.databinding.FragmentDetalleInmuebleBinding;
import com.example.inmobiliaria_2025.model.Inmueble;
import com.example.inmobiliaria_2025.request.ApiClient;

public class DetalleInmuebleFragment extends Fragment {

    private FragmentDetalleInmuebleBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentDetalleInmuebleBinding.inflate(inflater, container, false);

        // Suponiendo que te pasan un objeto Inmueble como argumento
        if (getArguments() != null) {
            Inmueble inmueble = (Inmueble) getArguments().getSerializable("inmueble");

            if (inmueble != null) {
                binding.tvDireccionI.setText(inmueble.getDireccion());
                binding.tvUsoI.setText(inmueble.getUso());
                binding.tvAmbientesI.setText(String.valueOf(inmueble.getAmbientes()));
                binding.tvValorI.setText(String.valueOf(inmueble.getValor()));
                binding.tvUsoI.setText(inmueble.getUso());
                binding.checkDisponible.setChecked(inmueble.isDisponible());

                // Cargar imagen con Glide
                Glide.with(this)
                        .load(ApiClient.BASE_URL + inmueble.getImagen())
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .error(R.drawable.ic_launcher_foreground)
                        .into(binding.imgInmueble);
            }
        }

        // Click listener para el checkbox
        binding.checkDisponible.setOnClickListener(v -> {
            // Aquí podés manejar la actualización del inmueble si querés
        });

        return binding.getRoot();
    }
}
