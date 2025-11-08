package com.example.inmobiliaria_2025;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.inmobiliaria_2025.request.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivityViewModel extends AndroidViewModel implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 800; // Umbral de agitación
    private MutableLiveData<String> mMensaje = new MutableLiveData<>();

    public LoginActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getMensaje() {
        return mMensaje;
    }

    public void login(String mail, String clave, Context context) {
        ApiClient.InmobiliariaService api = ApiClient.getInmobiliariaService();

        Call<String> llamada = api.login(mail, clave);

        llamada.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful() && response.body() != null){
                    String token = response.body();
                    ApiClient.guardarToken(context, token);

                    mMensaje.postValue("Bienvenido");

                    Intent intent = new Intent(context, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                } else {
                    mMensaje.postValue("Usuario y/o contraseña incorrecta");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                mMensaje.postValue("Error de servidor: " + t.getMessage());
            }
        });
    }
    public void iniciarDeteccionDeSacudida(Context context) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            if (accelerometer != null) {
                sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
                Log.d("LoginViewModel", "Sensor de acelerómetro registrado.");
            } else {
                Log.e("LoginViewModel", "No se encontró el sensor del acelerómetro.");
            }
        }
    }

    public void detenerDeteccionDeSacudida() {
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
            Log.d("LoginViewModel", "Sensor de acelerómetro detenido.");
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            long curTime = System.currentTimeMillis();
            // Solo actualiza cada 100ms
            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];

                float speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;

                if (speed > SHAKE_THRESHOLD) {
                    Log.d("LoginViewModel", "¡Sacudida detectada!");
                    iniciarLlamada(getApplication());
                }

                last_x = x;
                last_y = y;
                last_z = z;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // No es necesario implementarlo para este caso
    }

    private void iniciarLlamada(Context context) {
        // Cambia ACTION_CALL por ACTION_DIAL
        Intent intent = new Intent(Intent.ACTION_DIAL);

        intent.setData(Uri.parse("tel:2664332211"));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

        // Actualiza el log para que refleje la nueva acción
        Log.d("LoginViewModel", "Abriendo el marcador con el número 2664332211.");
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        detenerDeteccionDeSacudida();
    }
}
