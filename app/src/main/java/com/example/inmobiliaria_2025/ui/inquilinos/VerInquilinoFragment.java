package com.example.inmobiliaria_2025.ui.inquilinos;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inmobiliaria_2025.R;
import com.example.inmobiliaria_2025.databinding.FragmentInfoInmuebleBinding;
import com.example.inmobiliaria_2025.databinding.FragmentVerInquilinoBinding;
import com.example.inmobiliaria_2025.ui.inmuebles.InfoInmuebleViewModel;

public class VerInquilinoFragment extends Fragment {

    private VerInquilinoViewModel mv;
    private FragmentVerInquilinoBinding binding;

    public static VerInquilinoFragment newInstance() {
        return new VerInquilinoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mv = new ViewModelProvider(this).get(VerInquilinoViewModel.class);

        binding = FragmentVerInquilinoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mv.getmInquilino().observe(getViewLifecycleOwner(), inquilino -> {
            binding.tvCodigo.setText(String.valueOf(inquilino.getIdInquilino()));
            binding.tvNombreCompleto.setText(inquilino.getNombre()+" "+inquilino.getApellido());
            binding.tvDni.setText(String.valueOf(inquilino.getDni()));
            binding.tvEmail.setText(inquilino.getEmail());
            binding.tvTelefono.setText(String.valueOf(inquilino.getTelefono()));


        });
        mv.obtenerInquilino(getArguments());

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mv = new ViewModelProvider(this).get(VerInquilinoViewModel.class);
        // TODO: Use the ViewModel
    }

}
