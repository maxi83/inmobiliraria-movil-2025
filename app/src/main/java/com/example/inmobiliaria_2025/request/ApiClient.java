package com.example.inmobiliaria_2025.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.inmobiliaria_2025.model.Inmueble;
import com.example.inmobiliaria_2025.model.Propietario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public class ApiClient {
        public final static  String BASE_URL = "https://inmobiliariaulp-amb5hwfqaraweyga.canadacentral-01.azurewebsites.net/";


        public static InmoService getInmoService(){
            Gson gson = new GsonBuilder().setLenient().create();

            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            return retrofit.create(InmoService.class);
        }



        public interface InmoService{

            @FormUrlEncoded
            @POST("api/Propietarios/login")
            Call<String> loginForm(@Field("Usuario") String usuario, @Field("Clave") String clave);

            @GET("api/Propietarios")
            Call<Propietario> getPropietario(@Header("Authorization") String token);

            @PUT("api/Propietarios/actualizar")
            Call<Propietario> actualizarProp(@Header("Authorization") String token, @Body Propietario p);
            @GET("api/Inmuebles")
            Call<List<Inmueble>> obtenerInmuebles(@Header("Authorization") String token);

            @PUT("api/Inmuebles/actualizar")
            Call<Inmueble>actualizarInmueble(@Header("Authorization") String token, @Body Inmueble inmueble);

        }

        public static void guardarToken(Context context, String token) {

            SharedPreferences sp = context.getSharedPreferences("token.xml", Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = sp.edit();

            editor.putString("token", token);

            editor.apply();

        }
        public static String leerToken(Context context) {

            SharedPreferences sp = context.getSharedPreferences("token.xml", Context.MODE_PRIVATE);

            return sp.getString("token", null);

        }

    }

