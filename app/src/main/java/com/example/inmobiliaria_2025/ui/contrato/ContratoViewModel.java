package com.example.inmobiliaria_2025.ui.contrato;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliaria_2025.model.Contrato;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class ContratoViewModel extends ViewModel {

    private MutableLiveData<List<Contrato>> contratosLiveData = new MutableLiveData<>();

    private static final String BASE_URL = "https://inmobiliariaulp-amb5hwfqaraweyga.canadacentral01.azurewebsites.net/";

    interface ContratoService {
        @GET("contratos") // reemplaza con tu endpoint real
        Call<List<Contrato>> getContratos();
    }

    public LiveData<List<Contrato>> getContratos() {
        loadContratos();
        return contratosLiveData;
    }

    private void loadContratos() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ContratoService service = retrofit.create(ContratoService.class);
        service.getContratos().enqueue(new Callback<List<Contrato>>() {
            @Override
            public void onResponse(Call<List<Contrato>> call, Response<List<Contrato>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    contratosLiveData.setValue(response.body());
                } else {
                    contratosLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<Contrato>> call, Throwable t) {
                contratosLiveData.setValue(null);
            }
        });
    }
}
