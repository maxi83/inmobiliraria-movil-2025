package com.example.inmobiliaria_2025.ui.inquilino;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliaria_2025.model.Contrato;
import com.example.inmobiliaria_2025.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InquilinoViewModel extends ViewModel {

    private MutableLiveData<List<Contrato>> contratosLive = new MutableLiveData<>();

    public LiveData<List<Contrato>> getContratos() {
        return contratosLive;
    }

    // Pedimos los contratos a la API
    public void fetchContratos() {
        ApiClient.getInmoService().obtenerContratos("todos").enqueue(new Callback<List<Contrato>>() {
            @Override
            public void onResponse(Call<List<Contrato>> call, Response<List<Contrato>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    contratosLive.setValue(response.body());
                } else {
                    contratosLive.setValue(new ArrayList<>());
                }
            }

            @Override
            public void onFailure(Call<List<Contrato>> call, Throwable t) {
                contratosLive.setValue(new ArrayList<>());
            }
        });
    }
}
