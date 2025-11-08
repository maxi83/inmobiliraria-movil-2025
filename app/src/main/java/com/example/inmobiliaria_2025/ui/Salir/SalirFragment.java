package com.example.inmobiliaria_2025.ui.Salir;

import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inmobiliaria_2025.R;
import com.example.inmobiliaria_2025.databinding.FragmentSalirBinding;

public class SalirFragment extends Fragment {

    private SalirViewModel mViewModel;
    private FragmentSalirBinding binding;

    public static SalirFragment newInstance() {
        return new SalirFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // 1. Inflar el binding y obtener la vista raíz
        binding = FragmentSalirBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        mostrarDialogoDeSalida();


        return root;
    }
    // --- Método para encapsular la lógica del diálogo ---
    private void mostrarDialogoDeSalida() {
        new AlertDialog.Builder(requireContext())
                .setTitle("Confirmar Salida")
                .setMessage("¿Seguro que desea Salir?")
                .setPositiveButton("Sí", (dialog, which) -> {
                    // Cierra la actividad contenedora y elimina todas las tareas.
                    requireActivity().finishAndRemoveTask();
                })
                .setNegativeButton("No", (dialog, which) -> {
                    dialog.dismiss();


                    getParentFragmentManager().popBackStack();

                })
                .setCancelable(false)
                .show();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SalirViewModel.class);
        // TODO: Use the ViewModel
    }

}