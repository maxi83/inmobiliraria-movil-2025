package com.example.inmobiliaria_2025.ui.inmuebles;

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
import com.example.inmobiliaria_2025.databinding.FragmentCrearInmuebleBinding;

public class CrearInmuebleFragment extends Fragment {

    private CrearInmuebleViewModel mv;
    private FragmentCrearInmuebleBinding binding;
    private ActivityResultLauncher<Intent> arl;
    private Intent intent;


    public static CrearInmuebleFragment newInstance() {
        return new CrearInmuebleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mv= new ViewModelProvider(this).get(CrearInmuebleViewModel.class);
        binding= FragmentCrearInmuebleBinding.inflate(getLayoutInflater());
        abrirGaleria();
        binding.btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arl.launch(intent);
            }
        });
        mv.getmUri().observe(getViewLifecycleOwner(), new Observer<Uri>() {
            @Override
            public void onChanged(Uri uri) {
                binding.imgView.setImageURI(uri);
            }
        });

        binding.btnCargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mv.guardar(binding.etDireccion.getText().toString(),
                        binding.etValor.getText().toString(),
                        binding.etTipo.getText().toString(),
                        binding.etUso.getText().toString(),
                        binding.etAmbientes.getText().toString(),
                        binding.etSuperficie.getText().toString(),
                        binding.cbDisp.isChecked());
            }
        });
        return binding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mv = new ViewModelProvider(this).get(CrearInmuebleViewModel.class);
        // TODO: Use the ViewModel
    }

    private void abrirGaleria() {
        intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);//abre la galeria
        arl = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                Log.d("AgregarInmuebleFragment", "Result: " + result);
                mv.recibirFoto(result);

            }
        });
    }
}