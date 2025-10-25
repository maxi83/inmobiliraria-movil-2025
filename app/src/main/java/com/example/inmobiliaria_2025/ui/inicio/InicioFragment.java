package com.example.inmobiliaria_2025.ui.inicio;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;

import com.example.inmobiliaria_2025.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class InicioFragment extends Fragment implements OnMapReadyCallback {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d("InicioFragment", "onCreateView ejecutado");

        // Inflamos el layout mínimo
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        // Buscamos el SupportMapFragment dentro del layout
        SupportMapFragment mapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.map);

        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        } else {
            Log.e("InicioFragment", "mapFragment es null");
        }

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Mostramos la ULP
        LatLng ulp = new LatLng(-33.3020, -66.3383);
        googleMap.addMarker(new MarkerOptions().position(ulp).title("Universidad de La Punta"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ulp, 15));
    }
}
