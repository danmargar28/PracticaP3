package com.dani.viajes.model;

import java.time.LocalDate;

public class Reserva {
    private String id;
    private String descripcion;
    private LocalDate fecha;

    public Reserva(String id, String descripcion, LocalDate fecha) {
        this.id = id;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public String getId() { return id; }
    public String getDescripcion() { return descripcion; }
    public LocalDate getFecha() { return fecha; }
}
