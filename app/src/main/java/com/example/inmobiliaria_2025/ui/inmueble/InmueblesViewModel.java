package com.example.inmobiliaria_2025.ui.inmueble;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.inmobiliaria_2025.model.Inmueble;
import com.example.inmobiliaria_2025.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmueblesViewModel extends AndroidViewModel {

    private final MutableLiveData<List<Inmueble>> inmueblesLiveData = new MutableLiveData<>();

    public InmueblesViewModel(@NonNull Application application) {
        super(application);
        leerInmuebles();
    }

    public LiveData<List<Inmueble>> getInmueblesLiveData() {
        return inmueblesLiveData;
    }

    public void obtenerInmuebles() {
        leerInmuebles();
    }

    private void leerInmuebles() {
        String token = ApiClient.leerToken(getApplication());
        ApiClient.InmoService api = ApiClient.getInmoService();
        Call<List<Inmueble>> llamada = api.obtenerInmuebles("Bearer " + token);

        llamada.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if (response.isSuccessful()) {
                    inmueblesLiveData.postValue(response.body());
                } else {
                    Toast.makeText(getApplication(), "No hay inmuebles disponibles: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable t) {
                Toast.makeText(getApplication(), "Error en servidor: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
