package com.example.inmobiliaria_2025.ui.inmueble;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inmobiliaria_2025.R;
import com.example.inmobiliaria_2025.databinding.FragmentCargarInmuebleBinding;

public class CargarInmuebleFragment extends Fragment {

    private CargarInmuebleViewModel mViewModel;
    private FragmentCargarInmuebleBinding binding;
    private ActivityResultLauncher<Intent> arl;
    private Intent intent;

    public static CargarInmuebleFragment newInstance() {
        return new CargarInmuebleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentCargarInmuebleBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(CargarInmuebleViewModel.class);

        // Inicializar la galería
        abrirGaleria();

        // Botón para seleccionar foto
        binding.btnFoto.setOnClickListener(v -> arl.launch(intent));

        // Observar LiveData para mostrar la imagen
        mViewModel.getMuri().observe(getViewLifecycleOwner(), uri ->
                binding.imgView.setImageURI(uri));

        // Botón para cargar inmueble
        binding.btnCargar.setOnClickListener(v -> mViewModel.cargarInmueble(
                binding.etDireccion.getText().toString(),
                binding.etValor.getText().toString(),
                binding.etTipo.getText().toString(),
                binding.etUso.getText().toString(),
                binding.etAmbientes.getText().toString(),
                binding.etSuperficie.getText().toString(),
                binding.cbDisp.isChecked()
        ));

        return binding.getRoot();
    }

    private void abrirGaleria() {
        intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);//Es para abrir la galeria
        arl = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                Log.d("AgregarInmuebleFragment", "Result: " + result);
                mViewModel.recibirFoto(result);

            }
        });
    }
}
