package com.example.inmobiliaria_2025.ui.contratos;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inmobiliaria_2025.R;
import com.example.inmobiliaria_2025.databinding.FragmentInfoContratoBinding;
//import com.example.inmobiliaria_2025.databinding.FragmentVerInquilinoBinding;
import com.example.inmobiliaria_2025.ui.inquilinos.VerInquilinoViewModel;

public class InfoContratoFragment extends Fragment {

    private InfoContratoViewModel mv;
    private FragmentInfoContratoBinding binding;

    public static InfoContratoFragment newInstance() {
        return new InfoContratoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mv = new ViewModelProvider(this).get(InfoContratoViewModel.class);

        binding = FragmentInfoContratoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mv.getmContrato().observe(getViewLifecycleOwner(), contrato -> {
            binding.tvCodigoContrato.setText(String.valueOf(contrato.getIdContrato()));
            binding.tvFechaInicio.setText(contrato.getFechaInicio());
            binding.tvFechaFin.setText(contrato.getFechaFinalizacion());
            binding.tvMonto.setText(String.valueOf(contrato.getMontoAlquiler()));
            binding.tvInquilino.setText(contrato.getInquilino().getNombre());
            binding.tvInmueble.setText(contrato.getInmueble().getDireccion());


        });
        mv.obtenerContrato(getArguments());

        binding.btnPagos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mv.getmIdContrato().observe(getViewLifecycleOwner(), idContrato -> {

                    Bundle bundle = new Bundle();
                    bundle.putInt("idContrato", idContrato);
                    Navigation.findNavController(v).navigate(R.id.detallePagosFragment, bundle);

                });
            }
        });
        binding.btnInquilino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mv.getmInquilino().observe(getViewLifecycleOwner(), idInmueble -> {
                   Bundle bundle = new Bundle();
                   bundle.putInt("idInmueble", idInmueble);
                   Navigation.findNavController(v).navigate(R.id.verInquilinoFragment, bundle);
               });
            }
        });




        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mv = new ViewModelProvider(this).get(InfoContratoViewModel.class);
        // TODO: Use the ViewModel
    }

}