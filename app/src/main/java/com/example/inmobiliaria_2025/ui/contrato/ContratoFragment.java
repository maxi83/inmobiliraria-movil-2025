package com.example.inmobiliaria_2025.ui.contrato;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.inmobiliaria_2025.R;
import com.example.inmobiliaria_2025.model.Inmueble;
import com.example.inmobiliaria_2025.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContratoFragment extends Fragment {

    private ImageView ivContratoFoto;
    private TextView tvContratoDireccion;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contrato, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ivContratoFoto = view.findViewById(R.id.ivContratoFoto);
        tvContratoDireccion = view.findViewById(R.id.tvContratoDireccion);

        cargarInmuebleVigente();
    }

    private void cargarInmuebleVigente() {
        String token = ApiClient.leerToken(getContext());
        if (token == null) {
            Toast.makeText(getContext(), "Token no disponible", Toast.LENGTH_SHORT).show();
            return;
        }

        ApiClient.getInmoService()
                .obtenerInmueblesConContratoVigente("Bearer " + token)
                .enqueue(new Callback<List<Inmueble>>() {
                    @Override
                    public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                        if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                            mostrarInmueble(response.body().get(0)); // mostramos solo el primero
                        } else {
                            Toast.makeText(getContext(), "No hay inmuebles con contrato vigente", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Inmueble>> call, Throwable t) {
                        Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void mostrarInmueble(Inmueble inmueble) {
        if (inmueble != null) {
            tvContratoDireccion.setText(inmueble.getDireccion());
            String url = "https://inmobiliariaulp-amb5hwfqaraweyga.canadacentral-01.azurewebsites.net/" + inmueble.getImagen();
            Glide.with(this)
                    .load(url)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(ivContratoFoto);
        } else {
            tvContratoDireccion.setText("Sin dirección");
            ivContratoFoto.setImageResource(R.drawable.ic_launcher_background);
        }
    }
}
