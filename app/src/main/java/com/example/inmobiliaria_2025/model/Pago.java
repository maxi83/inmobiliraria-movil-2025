package com.example.inmobiliaria_2025.model;

import java.io.Serializable;

public class Pago implements Serializable {

    private int idPago;
    private int nroPago;
    private Contrato contrato;
    private int idContrato;
    private double monto;
    private String fechaPago;
    private String detalle;
    private boolean estado;

    public Pago() {
    }

    public Pago(int idPago, int nroPago, double monto, String fechaPago, int idContrato, String detalle, boolean estado) {
        this.idPago = idPago;
        this.nroPago = nroPago;
        this.monto = monto;
        this.fechaPago = fechaPago;
        this.idContrato = idContrato;
        this.detalle = detalle;
        this.estado = estado;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public int getNroPago() {
        return nroPago;
    }

    public void setNroPago(int nroPago) {
        this.nroPago = nroPago;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
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

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    // Método para mostrar la fecha en formato dd/MM/yyyy
    public String fechaPagoFormateada() {
        if (fechaPago != null && fechaPago.length() >= 10) {
            String año = fechaPago.substring(0, 4);
            String mes = fechaPago.substring(5, 7);
            String dia = fechaPago.substring(8, 10);
            return dia + "/" + mes + "/" + año;
        }
        return fechaPago;
    }
}
