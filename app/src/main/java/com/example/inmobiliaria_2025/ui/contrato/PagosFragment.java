package com.example.inmobiliaria_2025.ui.pago;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inmobiliaria_2025.R;
import com.example.inmobiliaria_2025.model.Contrato;
import com.example.inmobiliaria_2025.model.Pago;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class PagosFragment extends Fragment {

    private RecyclerView recyclerPagos;
    private PagoAdapter adapter;
    private Contrato contrato;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pagos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerPagos = view.findViewById(R.id.recyclerPagos);
        recyclerPagos.setLayoutManager(new LinearLayoutManager(getContext()));

        if (getArguments() != null) {
            contrato = (Contrato) getArguments().getSerializable("contrato");
        }

        // TODO: Llamar a la API para traer los pagos de este contrato
        // Por ahora simulamos con JSON
        String ejemploJson = "[{\"idPago\":1,\"fechaPago\":\"2024-04-10\",\"monto\":23.0,\"detalle\":\"Mes abril\",\"estado\":false,\"idContrato\":"
                + contrato.getIdContrato() + ",\"contrato\":null}]";

        Type listType = new TypeToken<List<Pago>>() {}.getType();
        List<Pago> pagos = new Gson().fromJson(ejemploJson, listType);

        adapter = new PagoAdapter(pagos, getContext());
        recyclerPagos.setAdapter(adapter);
    }
}
