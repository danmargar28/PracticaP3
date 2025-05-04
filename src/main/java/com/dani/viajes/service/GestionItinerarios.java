package com.dani.viajes.service;

import java.util.List;
import com.dani.viajes.model.Itinerario;
import com.dani.viajes.model.Reserva;
import java.util.HashMap;
import java.util.Map;

public class GestionItinerarios {
    private Map<String, Itinerario> itinerarios = new HashMap<>();

    public void crearItinerario(Itinerario iti) {
        if (itinerarios.containsKey(iti.getId())) {
            throw new IllegalArgumentException("Itinerario ya existe");
        }
        itinerarios.put(iti.getId(), iti);
    }

    public Itinerario obtenerItinerario(String id) {
        return itinerarios.get(id);
    }

     public void agregarReserva(String idItinerario, Reserva reserva) {
        Itinerario iti = obtenerItinerario(idItinerario);
        if (iti == null) {
            throw new IllegalArgumentException("Itinerario no encontrado");
        }
        iti.agregarReserva(reserva);
    }
    /**
     * Devuelve la lista de reservas de un itinerario, o lanza IllegalArgumentException si no existe.
     */
    public List<Reserva> obtenerReservas(String idItinerario) {
        Itinerario iti = obtenerItinerario(idItinerario);
        if (iti == null) {
            throw new IllegalArgumentException("Itinerario no encontrado");
        }
        return iti.getReservas();
    }

}
