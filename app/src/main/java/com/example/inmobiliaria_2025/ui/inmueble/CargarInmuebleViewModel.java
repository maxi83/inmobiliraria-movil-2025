package com.example.inmobiliaria_2025.ui.inmueble;

import static android.app.Activity.RESULT_OK;

import android.app.Application;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.example.inmobiliaria_2025.model.Inmueble;
import com.example.inmobiliaria_2025.request.ApiClient;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CargarInmuebleViewModel extends AndroidViewModel {

    private MutableLiveData<Uri> mUri = new MutableLiveData<>();

    public CargarInmuebleViewModel(@NonNull Application application) {
        super(application);
    }

    // Getter para LiveData
    public LiveData<Uri> getMuri() {
        return mUri;
    }

    // Setter para LiveData (para actualizar la foto)
    public void setMuri(Uri uri) {
        mUri.setValue(uri);
    }

    // Recibir la foto desde el fragmento
    public void recibirFoto(ActivityResult result) {
        if (result.getResultCode() == RESULT_OK) {
            Intent data = result.getData();
            if (data != null && data.getData() != null) {
                Uri uri = data.getData();
                Log.d("CargarInmuebleVM", "Foto seleccionada: " + uri);
                setMuri(uri); // ahora usamos el setter
            }
        }
    }

    // Cargar inmueble al servidor
    public void cargarInmueble(String direccion, String valor, String tipo, String uso,
                               String ambientes, String superficie, boolean disponible) {
        int superficiePars, ambientesPars;
        double precio;

        try {
            // Validación de números
            precio = Double.parseDouble(valor);
            superficiePars = Integer.parseInt(superficie);
            ambientesPars = Integer.parseInt(ambientes);

            // Validación campos vacíos
            if(direccion.isEmpty() || tipo.isEmpty() || uso.isEmpty() || ambientes.isEmpty() || superficie.isEmpty() || valor.isEmpty()) {
                Toast.makeText(getApplication(), "Debe ingresar todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            // Validación foto
            if(mUri.getValue() == null) {
                Toast.makeText(getApplication(), "Debe seleccionar una foto", Toast.LENGTH_SHORT).show();
                return;
            }

            // Crear objeto Inmueble
            Inmueble inmueble = new Inmueble();
            inmueble.setDireccion(direccion);
            inmueble.setValor(precio);
            inmueble.setTipo(tipo);
            inmueble.setUso(uso);
            inmueble.setAmbientes(ambientesPars);
            inmueble.setSuperficie(superficiePars);
            inmueble.setDisponible(disponible);

            // Convertir imagen a byte[]
            byte[] imagen = transformarImagen();
            String inmuebleJson = new Gson().toJson(inmueble);

            RequestBody inmuebleBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), inmuebleJson);
            RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), imagen);

            MultipartBody.Part imagenPart = MultipartBody.Part.createFormData("imagen", "imagen.jpg", requestFile);

            // Llamada a API
            ApiClient.InmoService inmoService = ApiClient.getInmoService();
            String token = ApiClient.leerToken(getApplication());
            Call<Inmueble> call = inmoService.CargarInmueble("Bearer "+ token, imagenPart, inmuebleBody);

            call.enqueue(new Callback<Inmueble>() {
                @Override
                public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                    if(response.isSuccessful()) {
                        Toast.makeText(getApplication(), "Inmueble cargado exitosamente", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplication(), "Error al cargar inmueble", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Inmueble> call, Throwable t) {
                    Toast.makeText(getApplication(), "Error en la llamada", Toast.LENGTH_SHORT).show();
                }
            });

        } catch(NumberFormatException nfe) {
            Toast.makeText(getApplication(), "Debe ingresar números en los campos de valor, superficie y ambientes", Toast.LENGTH_SHORT).show();
        }
    }

    // Transformar URI de imagen a byte[]
    private byte[] transformarImagen() {
        try {
            Uri uri = mUri.getValue();
            InputStream inputStream = getApplication().getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (FileNotFoundException e) {
            Toast.makeText(getApplication(), "No ha seleccionado una foto", Toast.LENGTH_SHORT).show();
            return new byte[]{};
        }
    }
}
