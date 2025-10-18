package com.example.inmobiliaria_2025.ui.inmueble;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inmobiliaria_2025.databinding.FragmentInmueblesBinding;

public class InmueblesFragment extends Fragment {

    private FragmentInmueblesBinding binding;
    private InmueblesViewModel mViewModel;
    private InmuebleAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentInmueblesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inicializar ViewModel
        mViewModel = new ViewModelProvider(this).get(InmueblesViewModel.class);

        // Inicializar Adapter
        adapter = new InmuebleAdapter(getContext());

        // GridLayoutManager para 2 columnas
        binding.rvListaInmueble.setLayoutManager(new GridLayoutManager(getContext(), 2));

        // Asignar Adapter
        binding.rvListaInmueble.setAdapter(adapter);

        // Observer para actualizar la lista
        mViewModel.getInmueblesLiveData().observe(getViewLifecycleOwner(), inmuebles -> {
            adapter.setInmuebles(inmuebles);
        });

        // Cargar datos
        mViewModel.obtenerInmuebles();

        // FloatingActionButton para agregar un inmueble
        binding.fabAgregarInmueble.setOnClickListener(v -> {
            // Abrir la actividad para agregar inmueble
            Intent intent = new Intent(getContext(), AgregarInmuebleActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // evitar fugas de memoria
    }
}
