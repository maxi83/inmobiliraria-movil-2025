package com.example.inmobiliaria_2025.ui.contratos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inmobiliaria_2025.databinding.FragmentContratosBinding;
import com.example.inmobiliaria_2025.databinding.FragmentInmueblesBinding;
import com.example.inmobiliaria_2025.model.Alquiler;
import com.example.inmobiliaria_2025.model.Inmueble;
import com.example.inmobiliaria_2025.ui.inquilinos.InquilinoAdapter;

import java.util.List;

public class ContratosFragment extends Fragment {

    private ContratosViewModel mv;
    private FragmentInmueblesBinding binding;

    public static ContratosFragment newInstance() {
        return new ContratosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mv = new ViewModelProvider(this).get(ContratosViewModel.class);

        binding = FragmentInmueblesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mv.getListaContratos().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmuebles) {
                ContratoAdapter ia=new ContratoAdapter(inmuebles,getContext(),getLayoutInflater());
                GridLayoutManager glm=new GridLayoutManager(getContext(),1,GridLayoutManager.VERTICAL,false);
                binding.lista.setLayoutManager(glm);
                binding.lista.setAdapter(ia);
            }
        });

        mv.obtenerContratos();


        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mv = new ViewModelProvider(this).get(ContratosViewModel.class);
        // TODO: Use the ViewModel
    }

}
