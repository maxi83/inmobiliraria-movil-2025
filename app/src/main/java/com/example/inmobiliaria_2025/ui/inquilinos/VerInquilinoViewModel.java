package com.example.inmobiliaria_2025.ui.inquilinos;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliaria_2025.model.Alquiler;
import com.example.inmobiliaria_2025.model.Inquilino;
import com.example.inmobiliaria_2025.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerInquilinoViewModel extends AndroidViewModel {
    private MutableLiveData<Inquilino> mInquilino = new MutableLiveData<>();

    public VerInquilinoViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<Inquilino> getmInquilino() {
        return mInquilino;
    }

    public void obtenerInquilino(Bundle idInmuebleBundle){
        int idInmueble = idInmuebleBundle.getInt("idInmueble");
        ApiClient.InmobiliariaService api = ApiClient.getInmobiliariaService();
        String token = ApiClient.leerToken(getApplication());
        Call<Alquiler> llamada = api.contratoPorInmueble("Bearer "+token, idInmueble);
        llamada.enqueue(new Callback<Alquiler>() {
            @Override
            public void onResponse(Call<Alquiler> call, Response<Alquiler> response) {
                if (response.isSuccessful()) {
                    mInquilino.setValue(response.body().getInquilino());
                }else {
                    Toast.makeText(getApplication(), "Error al obtener inquilino", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Alquiler> call, Throwable t) {
                Log.e("API_ERROR", "Error al obtener inquilino: " + t.getMessage(), t);
                mInquilino.setValue(null);
            }
        });




    }
}