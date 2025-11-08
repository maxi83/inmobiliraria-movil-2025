package com.example.inmobiliaria_2025.ui.inquilinos;

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

import com.example.inmobiliaria_2025.R;
import com.example.inmobiliaria_2025.databinding.FragmentInmueblesBinding;
import com.example.inmobiliaria_2025.databinding.FragmentInquilinosBinding;
import com.example.inmobiliaria_2025.model.Inmueble;
import com.example.inmobiliaria_2025.model.Inquilino;
import com.example.inmobiliaria_2025.ui.inmuebles.InmuebleAdapter;
import com.example.inmobiliaria_2025.ui.inmuebles.InmueblesViewModel;

import java.util.List;

public class InquilinosFragment extends Fragment {

    private InquilinosViewModel mv;
    private FragmentInmueblesBinding binding;

    public static InquilinosFragment newInstance() {
        return new InquilinosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mv = new ViewModelProvider(this).get(InquilinosViewModel.class);

        binding = FragmentInmueblesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mv.getListaInquilinos().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmuebles) {
                InquilinoAdapter ia=new InquilinoAdapter(inmuebles,getContext(),getLayoutInflater());
                GridLayoutManager glm=new GridLayoutManager(getContext(),1,GridLayoutManager.VERTICAL,false);
                binding.lista.setLayoutManager(glm);
                binding.lista.setAdapter(ia);
            }
        });

        mv.obtenerInquilinos();
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mv = new ViewModelProvider(this).get(InquilinosViewModel.class);
        // TODO: Use the ViewModel
    }

}
