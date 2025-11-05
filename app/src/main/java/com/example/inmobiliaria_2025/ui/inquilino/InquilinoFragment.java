package com.example.inmobiliaria_2025.ui.inquilino;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.navigation.fragment.NavHostFragment;

import com.example.inmobiliaria_2025.R;
import com.example.inmobiliaria_2025.model.Inmueble;
import com.example.inmobiliaria_2025.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InquilinoFragment extends Fragment implements InquilinoAdapter.OnItemClickListener {

    private RecyclerView recyclerInmuebles;
    private InquilinoAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_inquilinos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerInmuebles = view.findViewById(R.id.recyclerInmuebles);
        recyclerInmuebles.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new InquilinoAdapter(this);
        recyclerInmuebles.setAdapter(adapter);

        cargarInmuebles();
    }

    private void cargarInmuebles() {
        ApiClient.getInmoService().obtenerInmueblesConContratoVigente(ApiClient.leerToken(getContext()))
                .enqueue(new Callback<List<Inmueble>>() {
                    @Override
                    public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            adapter.setInmuebles(response.body()); // pone todos los inmuebles
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Inmueble>> call, Throwable t) {
                        // no hacemos nada, solo debug
                    }
                });
    }

    @Override
    public void onVerClick(Inmueble inmueble) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("inmueble", inmueble);
        NavHostFragment.findNavController(this)
                .navigate(R.id.action_nav_inquilinos_to_detalleInmuebleFragment, bundle);
    }
}
