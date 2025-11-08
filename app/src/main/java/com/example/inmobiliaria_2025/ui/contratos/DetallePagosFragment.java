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

import com.example.inmobiliaria_2025.databinding.FragmentDetallePagosBinding;
import com.example.inmobiliaria_2025.model.Pago;

import java.util.List;

public class DetallePagosFragment extends Fragment {

    private DetallePagosViewModel mv;
    private FragmentDetallePagosBinding binding;

    public static DetallePagosFragment newInstance() {
        return new DetallePagosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentDetallePagosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mv = new ViewModelProvider(this).get(DetallePagosViewModel.class);

        mv.getmPagos().observe(getViewLifecycleOwner(), new Observer<List<Pago>>() {
            @Override
            public void onChanged(List<Pago> pagos) {
                PagosAdapter ia=new PagosAdapter(pagos,getContext(),getLayoutInflater());
                GridLayoutManager glm=new GridLayoutManager(getContext(),1, GridLayoutManager.VERTICAL,false);
                binding.lista2.setLayoutManager(glm);
                binding.lista2.setAdapter(ia);
            }
        });
        mv.obtenerPagos(getArguments());
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mv = new ViewModelProvider(this).get(DetallePagosViewModel.class);
        // TODO: Use the ViewModel
    }

}
