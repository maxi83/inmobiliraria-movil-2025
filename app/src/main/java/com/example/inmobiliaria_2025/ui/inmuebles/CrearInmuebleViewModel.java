package com.example.inmobiliaria_2025.ui.inmuebles;

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
import androidx.lifecycle.ViewModel;

import com.example.inmobiliaria_2025.model.Inmueble;
import com.example.inmobiliaria_2025.request.ApiClient;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrearInmuebleViewModel extends AndroidViewModel {

    private MutableLiveData<Uri> mUri= new MutableLiveData<>();

    public CrearInmuebleViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Uri> getmUri() {
        return mUri;
    }

    public void recibirFoto(ActivityResult result) {
        if (result.getResultCode() == RESULT_OK) {
            Intent data = result.getData();
            Uri uri = data.getData();
            Log.d("salada", uri.toString());
            mUri.setValue(uri);
        }
    }

    public void guardar(String direccion, String valor, String tipo, String uso, String ambientes, String superficie , boolean disponible ) {
        int superficieParse, ambientesParse;
        double precio;
        try {
            precio = Double.parseDouble(valor);
            superficieParse = Integer.parseInt(superficie);
            ambientesParse = Integer.parseInt(ambientes);
            if(direccion.isEmpty() || valor.isEmpty() || tipo.isEmpty() || uso.isEmpty() || ambientes.isEmpty() || superficie.isEmpty() ){
                Toast.makeText(getApplication(), "Error al guardar, debe completar todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }
            if(mUri.getValue()==null){
                Toast.makeText(getApplication(), "Error al guardar, debe seleccionar una foto", Toast.LENGTH_SHORT).show();
                return;
            }

            Inmueble inmueble = new Inmueble();
            inmueble.setDireccion(direccion);
            inmueble.setValor(precio);
            inmueble.setTipo(tipo);
            inmueble.setUso(uso);
            inmueble.setAmbientes(ambientesParse);
            inmueble.setSuperficie(superficieParse);
            inmueble.setDisponible(disponible);

            //convertir en base a la uri
            byte[] imagen = transformarImagen();
            String inmuebleJson = new Gson().toJson(inmueble);
            RequestBody inmuebleBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), inmuebleJson);
            RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), imagen);


            //armar multipart
            MultipartBody.Part imagenPart = MultipartBody.Part.createFormData("imagen", "imagen.jpg", requestFile);



            //armar la llamada
            String token = ApiClient.leerToken(getApplication());
            Call<Inmueble> llamada = ApiClient.getInmobiliariaService().crearInmueble("Bearer " + token, imagenPart, inmuebleBody);
            llamada.enqueue(new Callback<Inmueble>() {
                @Override
                public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getApplication(), "Inmueble creado correctamente", Toast.LENGTH_SHORT).show();
                        //inmueble.setValue(response.body());
                    } else {
                        Toast.makeText(getApplication(), "Error al crear el inmueble: " + response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Inmueble> call, Throwable t) {
                    Toast.makeText(getApplication(), "Error al contactar con el servidor: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } catch (NumberFormatException e) {
            Toast.makeText(getApplication(), "Error al guardar", Toast.LENGTH_SHORT).show();
            return;
        }

    }

    private byte[] transformarImagen() {
        try {
            Uri uri = mUri.getValue();  //lo puedo usar porque estoy en viewmodel
            InputStream inputStream = getApplication().getContentResolver().openInputStream(uri);//crea un flujo de bytes
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (
                FileNotFoundException er) {
            Toast.makeText(getApplication(), "No ha seleccinado una foto", Toast.LENGTH_SHORT).show();
            return new byte[]{};
        }
    }

}