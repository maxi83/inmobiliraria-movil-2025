package com.example.inmobiliaria_2025.ui.inmueble;

import android.app.Application;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliaria_2025.model.Inmueble;
import com.example.inmobiliaria_2025.model.Inmueble;
import com.example.inmobiliaria_2025.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleInmuebleViewModel extends AndroidViewModel {

    private MutableLiveData<Inmueble> inmueble = new MutableLiveData<>();

    public DetalleInmuebleViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Inmueble> getInmueble(){
        return inmueble;
    }

    public void obtenerInmueble(Bundle inmuebleBundle){
        Inmueble inmueble = (Inmueble) inmuebleBundle.getSerializable("inmueble");

        if(inmueble != null){
            this.inmueble.setValue(inmueble);
        }

    }

    public void actualizarInmueble(Boolean disponible){
        Inmueble inmueble = new Inmueble();
        inmueble.setDisponible(disponible);
        inmueble.setIdInmueble(this.inmueble.getValue().getIdInmueble());
        String token = ApiClient.leerToken(getApplication());
        Call<Inmueble> llamada = ApiClient.getInmoService().actualizarInmueble("Bearer " + token, inmueble);
        llamada.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplication(), "Inmueble actualizado correctamente", Toast.LENGTH_SHORT).show();
                    //inmueble.setValue(response.body());
                } else {
                    Toast.makeText(getApplication(), "Error al actualizar el inmueble: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Inmueble> call, Throwable t) {
                Toast.makeText(getApplication(), "Error al contactar con el servidor: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}