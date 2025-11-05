package com.example.inmobiliaria_2025.model;

import java.io.Serializable;

public class Inquilino implements Serializable {

    private int id;
    private String dni;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String garante;
    private String telefonoGarante;

    // Constructor vacío
    public Inquilino() {}

    // Constructor con todos los campos
    public Inquilino(int id, String dni, String nombre, String apellido,
                     String email, String telefono, String garante, String telefonoGarante) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.garante = garante;
        this.telefonoGarante = telefonoGarante;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getGarante() { return garante; }
    public void setGarante(String garante) { this.garante = garante; }

    public String getTelefonoGarante() { return telefonoGarante; }
    public void setTelefonoGarante(String telefonoGarante) { this.telefonoGarante = telefonoGarante; }

    @Override
    public String toString() {
        return getNombre() + " " + getApellido();
    }
}
