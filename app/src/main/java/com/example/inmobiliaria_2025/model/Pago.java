package com.example.inmobiliaria_2025.model;

import java.io.Serializable;
import java.time.LocalDate;

import java.time.LocalDate;

public class Pago implements Serializable {
    private int idPago;
    private String detalle;
    private String fechaPago;
    private int idContrato;
    private double monto;
    private Alquiler alquiler;

    public Pago() {}

    public Pago(int idPago, String detalle, String fechaPago, int idContrato, double monto, Alquiler alquiler) {
        this.idPago = idPago;
        this.detalle = detalle;
        this.fechaPago = fechaPago;
        this.idContrato = idContrato;
        this.monto = monto;
        this.alquiler = alquiler;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Alquiler getAlquiler() {
        return alquiler;
    }

    public void setAlquiler(Alquiler alquiler) {
        this.alquiler = alquiler;
    }
}
