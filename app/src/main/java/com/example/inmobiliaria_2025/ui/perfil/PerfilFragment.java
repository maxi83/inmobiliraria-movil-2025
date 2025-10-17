package com.example.inmobiliaria_2025.ui.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

        // Asegurar que los campos arranquen en modo solo lectura
        setModoLectura();

        return binding.getRoot();
    }

    public void onChanged(Propietario propietario) {
        if (propietario != null) {
            binding.etNombre.setText(propietario.getNombre());
            binding.etApellido.setText(propietario.getApellido());
            binding.etDni.setText(propietario.getDni());
            binding.etTelefono.setText(propietario.getTelefono());
            binding.etEmail.setText(propietario.getEmail());
        }
    }

    public void onClick(View v) {
        String textoBoton = binding.btnEditarGuardar.getText().toString().trim();

        if (textoBoton.equalsIgnoreCase("Editar")) {
            // Cambiar a modo edición
            setModoEdicion();
            binding.btnEditarGuardar.setText("Guardar");
        } else {
            // Tomar valores
            String nombre = binding.etNombre.getText().toString().trim();
            String apellido = binding.etApellido.getText().toString().trim();
            String dniStr = binding.etDni.getText().toString().trim();
            String telefono = binding.etTelefono.getText().toString().trim();
            String email = binding.etEmail.getText().toString().trim();

            // Validación: campos no vacíos
            if (nombre.isEmpty() || apellido.isEmpty() || dniStr.isEmpty()
                    || telefono.isEmpty() || email.isEmpty()) {
                Toast.makeText(getContext(), "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                return;
            }

            // Validación: DNI entero positivo
            int dni;
            try {
                dni = Integer.parseInt(dniStr);
                if (dni <= 0) {
                    Toast.makeText(getContext(), "El DNI debe ser un número positivo", Toast.LENGTH_SHORT).show();
                    return;
                }
            } catch (NumberFormatException e) {
                Toast.makeText(getContext(), "El DNI debe contener solo números", Toast.LENGTH_SHORT).show();
                return;
            }

            // Crear el propietario
            Propietario p = new Propietario();
            p.setNombre(nombre);
            p.setApellido(apellido);
            p.setDni(String.valueOf(dni));
            p.setTelefono(telefono);
            p.setEmail(email);

            // Guardar los cambios
            viewModel.guardar("Guardar", p);

            // Volver a modo solo lectura
            setModoLectura();
            binding.btnEditarGuardar.setText("Editar");

            Toast.makeText(getContext(), "Perfil actualizado correctamente", Toast.LENGTH_SHORT).show();
        }
    }

    private void setModoEdicion() {
        binding.etNombre.setEnabled(true);
        binding.etApellido.setEnabled(true);
        binding.etDni.setEnabled(true);
        binding.etTelefono.setEnabled(true);
        binding.etEmail.setEnabled(true);
    }

    private void setModoLectura() {
        binding.etNombre.setEnabled(false);
        binding.etApellido.setEnabled(false);
        binding.etDni.setEnabled(false);
        binding.etTelefono.setEnabled(false);
        binding.etEmail.setEnabled(false);
    }
}
