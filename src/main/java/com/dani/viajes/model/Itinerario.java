package com.dani.viajes.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Itinerario {
    private String id;
    private String destino;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private List<Reserva> reservas = new ArrayList<>();

    public Itinerario(String id, String destino, LocalDate fechaInicio, LocalDate fechaFin) {
        this.id = id;
        this.destino = destino;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }



    public String getId() { return id; }
    public String getDestino() { return destino; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public List<Reserva> getReservas() { return reservas; }

    public void agregarReserva(Reserva reserva) {
        if (reserva.getFecha().isBefore(fechaInicio) || reserva.getFecha().isAfter(fechaFin)) {
            throw new IllegalArgumentException("La fecha de reserva está fuera del rango del itinerario");
        }
        reservas.add(reserva);
    }
}
