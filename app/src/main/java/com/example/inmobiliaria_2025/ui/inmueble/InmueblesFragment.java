package com.example.inmobiliaria_2025.ui.inmueble;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.inmobiliaria_2025.R;
import com.example.inmobiliaria_2025.databinding.FragmentInmueblesBinding;
import com.example.inmobiliaria_2025.model.Inmueble;

public class InmueblesFragment extends Fragment {

    private FragmentInmueblesBinding binding;
    private InmuebleViewModel vm;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentInmueblesBinding.inflate(inflater, container, false);
        vm = new ViewModelProvider(this).get(InmuebleViewModel.class);

        vm.getInmuebles().observe(getViewLifecycleOwner(), inmuebles -> {

            // Adapter con click listener
            InmuebleAdapter adapter = new InmuebleAdapter(inmuebles, requireContext(), inmueble -> {
                Bundle bundle = new Bundle();
                bundle.putSerializable("inmueble", inmueble);

                // Navegación al detalle
                NavHostFragment.findNavController(InmueblesFragment.this)
                        .navigate(R.id.detalleInmuebleFragment, bundle);
            });

            GridLayoutManager glm = new GridLayoutManager(requireContext(), 2);
            binding.rvListaInmueble.setAdapter(adapter);
            binding.rvListaInmueble.setLayoutManager(glm);
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
