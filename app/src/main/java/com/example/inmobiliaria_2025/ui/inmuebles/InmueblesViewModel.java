package com.example.inmobiliaria_2025.ui.inmuebles;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliaria_2025.model.Inmueble;
import com.example.inmobiliaria_2025.model.Propietario;
import com.example.inmobiliaria_2025.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmueblesViewModel extends AndroidViewModel {

    private final MutableLiveData<List<Inmueble>> listaInmueble = new MutableLiveData<>();

    public InmueblesViewModel(@NonNull Application application) {
        super(application);
    }


    public LiveData<List<Inmueble>> getListaInmuebles() {
        return listaInmueble;
    }

    public void obtenerInmuebles() {
        ApiClient.InmobiliariaService api = ApiClient.getInmobiliariaService();
        String token = ApiClient.leerToken(getApplication());
        Call<List<Inmueble>> llamada = api.obtenerInmuebles("Bearer "+token);
        llamada.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if (response.isSuccessful()) {
                    listaInmueble.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable t) {
                listaInmueble.setValue(null);
            }
        });
    }
}