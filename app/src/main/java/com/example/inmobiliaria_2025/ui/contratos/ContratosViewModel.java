package com.example.inmobiliaria_2025.ui.contratos;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliaria_2025.model.Alquiler;
import com.example.inmobiliaria_2025.model.Inmueble;
import com.example.inmobiliaria_2025.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContratosViewModel extends AndroidViewModel {

    private final MutableLiveData<List<Inmueble>> listaContratos = new MutableLiveData<>();
    public ContratosViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Inmueble>> getListaContratos() {
        return listaContratos;
    }


    public void obtenerContratos() {
        ApiClient.InmobiliariaService api = ApiClient.getInmobiliariaService();
        String token = ApiClient.leerToken(getApplication());
        Call<List<Inmueble>> llamada = api.obtenerContratoVigente("Bearer "+token);
        llamada.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if (response.isSuccessful()) {
                    listaContratos.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable t) {
                listaContratos.setValue(null);
            }
        });
    }
}