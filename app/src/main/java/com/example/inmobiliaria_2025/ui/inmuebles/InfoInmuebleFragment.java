package com.example.inmobiliaria_2025.ui.inmuebles;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.inmobiliaria_2025.R;
import com.example.inmobiliaria_2025.databinding.FragmentInfoInmuebleBinding;
import com.example.inmobiliaria_2025.databinding.FragmentInmueblesBinding;
import com.example.inmobiliaria_2025.request.ApiClient;

public class InfoInmuebleFragment extends Fragment {

    private InfoInmuebleViewModel mv;
    private FragmentInfoInmuebleBinding binding;

    public static InfoInmuebleFragment newInstance() {
        return new InfoInmuebleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mv = new ViewModelProvider(this).get(InfoInmuebleViewModel.class);

        binding = FragmentInfoInmuebleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mv.getInmueble().observe(getViewLifecycleOwner(),inmueble -> {
            Glide.with(this)
                    .load(ApiClient.BASE_URL + inmueble.getImagen())
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error("null")
                    .into(binding.ivPropiedad);
            binding.tvPrecio.setText(inmueble.getValor()+"");
            binding.cbDisponible.setChecked(inmueble.isDisponible());
            binding.tvCodigo.setText(inmueble.getIdInmueble()+"");
            binding.tvAmbientes.setText(inmueble.getAmbientes()+"");
            binding.tvTipo.setText(inmueble.getTipo());
            binding.tvDireccion.setText(inmueble.getDireccion());
            binding.tvUso.setText(inmueble.getUso());

        });
        mv.obtenerInmueble(getArguments());
        binding.cbDisponible.setOnClickListener(v -> {
            mv.actualizarInmueble(binding.cbDisponible.isChecked());
        });


        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mv = new ViewModelProvider(this).get(InfoInmuebleViewModel.class);
        // TODO: Use the ViewModel
    }

}