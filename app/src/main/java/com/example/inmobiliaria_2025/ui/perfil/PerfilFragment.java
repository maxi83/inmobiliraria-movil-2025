package com.example.inmobiliaria_2025.ui.perfil;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.inmobiliaria_2025.databinding.FragmentPerfilBinding;
import com.example.inmobiliaria_2025.model.Propietario;
import com.example.inmobiliaria_2025.viewmodel.PerfilViewModel;

public class PerfilFragment extends Fragment {

    private FragmentPerfilBinding binding;
    private PerfilViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Inicializar binding
        binding = FragmentPerfilBinding.inflate(inflater, container, false);

        // Inicializar ViewModel
        viewModel = new ViewModelProvider(this).get(PerfilViewModel.class);

        // Observer de propietario
        viewModel.getPropietarioLiveData().observe(getViewLifecycleOwner(), this::onChanged);

        // Listener del botón Editar/Guardar
        binding.btnEditarGuardar.setOnClickListener(v -> onClick(v));

        // Obtener los datos del propietario al iniciar
        viewModel.obtenerPropietario();

        return binding.getRoot();
    }

    // ---------------- Método onChanged ----------------
    public void onChanged(Propietario propietario) {
        if (propietario != null) {
            binding.etNombre.setText(propietario.getNombre());
            binding.etApellido.setText(propietario.getApellido());
            binding.etDni.setText(propietario.getDni());
            binding.etTelefono.setText(propietario.getTelefono());
            binding.etEmail.setText(propietario.getEmail());
        }
    }

    // ---------------- Método onClick ----------------
    public void onClick(View v) {
        if (binding.btnEditarGuardar.getText().toString().equalsIgnoreCase("Editar")) {
            // Cambiar a modo edición
            binding.etNombre.setEnabled(true);
            binding.etApellido.setEnabled(true);
            binding.etDni.setEnabled(true);
            binding.etTelefono.setEnabled(true);
            binding.etEmail.setEnabled(true);
            binding.btnEditarGuardar.setText("Guardar");
        } else {
            // Guardar cambios
            Propietario p = new Propietario();
            p.setNombre(binding.etNombre.getText().toString());
            p.setApellido(binding.etApellido.getText().toString());
            p.setDni(binding.etDni.getText().toString());
            p.setTelefono(binding.etTelefono.getText().toString());
            p.setEmail(binding.etEmail.getText().toString());

            viewModel.guardar("Guardar", p);

            // Volver a modo solo lectura
            binding.etNombre.setEnabled(false);
            binding.etApellido.setEnabled(false);
            binding.etDni.setEnabled(false);
            binding.etTelefono.setEnabled(false);
            binding.etEmail.setEnabled(false);
            binding.btnEditarGuardar.setText("Editar");
        }
    }
}
