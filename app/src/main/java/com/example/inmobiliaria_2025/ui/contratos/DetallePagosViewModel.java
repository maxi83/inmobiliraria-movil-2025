package com.example.inmobiliaria_2025.ui.contratos;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliaria_2025.model.Pago;
import com.example.inmobiliaria_2025.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetallePagosViewModel extends AndroidViewModel {

    private MutableLiveData<List<Pago>> mPagos = new MutableLiveData<>();


    public DetallePagosViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<Pago>> getmPagos() {
        return mPagos;
    }

    public void obtenerPagos(Bundle bundle){
        int idContrato = bundle.getInt("idContrato");
        ApiClient.InmobiliariaService api = ApiClient.getInmobiliariaService();
        String token = ApiClient.leerToken(getApplication());
        Call<List<Pago>> llamada = api.pagosPorContrato("Bearer " + token, idContrato);
        llamada.enqueue(new Callback<List<Pago>>() {
            @Override
            public void onResponse(Call<List<Pago>> call, Response<List<Pago>> response) {
                if (response.isSuccessful()) {
                    mPagos.setValue(response.body());
                } else {
                    Toast.makeText(getApplication(), "Error al obtener pagos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Pago>> call, Throwable t) {
                Log.e("API_ERROR", "Error al obtener pagos: " + t.getMessage(), t);
                mPagos.setValue(null);
            }
        });

    }
}
