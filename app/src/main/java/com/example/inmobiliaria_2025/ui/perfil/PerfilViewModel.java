package com.example.inmobiliaria_2025.viewmodel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.inmobiliaria_2025.model.Propietario;
import com.example.inmobiliaria_2025.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {

    private MutableLiveData<Propietario> propietarioLiveData;
    private MutableLiveData<Boolean> isEditModeLiveData;

    public PerfilViewModel(@NonNull Application application) {
        super(application);
        propietarioLiveData = new MutableLiveData<>();
        isEditModeLiveData = new MutableLiveData<>(false); // empieza en modo lectura
    }

    public LiveData<Propietario> getPropietarioLiveData() {
        return propietarioLiveData;
    }

    public LiveData<Boolean> getIsEditModeLiveData() {
        return isEditModeLiveData;
    }

    public void obtenerPropietario() {
        String token = ApiClient.leerToken(getApplication());
        ApiClient.InmoService service = ApiClient.getInmoService();
        Call<Propietario> call = service.getPropietario("Bearer " + token);
        call.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if (response.isSuccessful() && response.body() != null) {
                    propietarioLiveData.postValue(response.body());
                } else {
                    Toast.makeText(getApplication(), "No se pudo obtener los datos del propietario", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Toast.makeText(getApplication(), "Error al conectarse con el servidor", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void guardar(String textoBoton, Propietario p) {
        if (textoBoton.equalsIgnoreCase("Editar")) {
            // Activar modo edición
            isEditModeLiveData.postValue(true);
        } else if (textoBoton.equalsIgnoreCase("Guardar")) {
            // Guardar cambios en el servidor
            actualizarPropietario(p);
            // Desactivar modo edición después de guardar
            isEditModeLiveData.postValue(false);
        }
    }

    private void actualizarPropietario(Propietario p) {
        p.setIdPropietario(propietarioLiveData.getValue().getIdPropietario());
        String token = ApiClient.leerToken(getApplication());
        ApiClient.InmoService service = ApiClient.getInmoService();
        Call<Propietario> call = service.actualizarProp("Bearer " + token, p);
        call.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if (response.isSuccessful() && response.body() != null) {
                    propietarioLiveData.postValue(response.body());
                    Toast.makeText(getApplication(), "Datos actualizados correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplication(), "No se pudieron actualizar los datos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Toast.makeText(getApplication(), "Error al conectarse con el servidor", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
