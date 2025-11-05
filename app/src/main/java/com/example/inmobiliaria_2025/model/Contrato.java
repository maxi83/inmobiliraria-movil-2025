package com.example.inmobiliaria_2025.model;

import java.io.Serializable;
import java.util.Objects;

public class Contrato implements Serializable {

    private int idContrato;
    private String fechaInicio;
    private String fechaFinalizacion; // coincide con el JSON
    private double montoAlquiler;
    private boolean estado;
    private int idInquilino;
    private int idInmueble;
    private Inquilino inquilino;
    private Inmueble inmueble;

    public Contrato() {}

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(String fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public double getMontoAlquiler() {
        return montoAlquiler;
    }

    public void setMontoAlquiler(double montoAlquiler) {
        this.montoAlquiler = montoAlquiler;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getIdInquilino() {
        return idInquilino;
    }

    public void setIdInquilino(int idInquilino) {
        this.idInquilino = idInquilino;
    }

    public int getIdInmueble() {
        return idInmueble;
    }

    public void setIdInmueble(int idInmueble) {
        this.idInmueble = idInmueble;
    }

    public Inquilino getInquilino() {
        return inquilino;
    }

    public void setInquilino(Inquilino inquilino) {
        this.inquilino = inquilino;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    // Formateo de fechas: dd/MM/yyyy
    public String fechaInicioOnly() {
        if (fechaInicio == null || fechaInicio.length() < 10) return "Desconocida";
        return fechaInicio.substring(8, 10) + "/" + fechaInicio.substring(5, 7) + "/" + fechaInicio.substring(0, 4);
    }

    public String fechaFinalizacionOnly() {
        if (fechaFinalizacion == null || fechaFinalizacion.length() < 10) return "Desconocida";
        return fechaFinalizacion.substring(8, 10) + "/" + fechaFinalizacion.substring(5, 7) + "/" + fechaFinalizacion.substring(0, 4);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contrato)) return false;
        Contrato contrato = (Contrato) o;
        return idContrato == contrato.idContrato;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idContrato);
    }

    @Override
    public String toString() {
        return "Contrato: " + (inmueble != null ? inmueble.getDireccion() : "Sin dirección");
    }
}
