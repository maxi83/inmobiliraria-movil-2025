package com.example.inmobiliaria_2025.ui.inquilinos;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliaria_2025.model.Inmueble;
import com.example.inmobiliaria_2025.model.Inquilino;
import com.example.inmobiliaria_2025.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InquilinosViewModel extends AndroidViewModel {
    private final MutableLiveData<List<Inmueble>> listaInquilinos = new MutableLiveData<>();

    public InquilinosViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Inmueble>> getListaInquilinos() {
        return listaInquilinos;
    }

    public void obtenerInquilinos() {
        ApiClient.InmobiliariaService api = ApiClient.getInmobiliariaService();
        String token = ApiClient.leerToken(getApplication());
        Call<List<Inmueble>> llamada = api.obtenerContratoVigente("Bearer "+token);
        llamada.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if (response.isSuccessful()) {
                    listaInquilinos.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable t) {
                listaInquilinos.setValue(null);
            }
        });
    }
}
