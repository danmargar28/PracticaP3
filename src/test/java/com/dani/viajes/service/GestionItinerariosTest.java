package com.dani.viajes.service;

import com.dani.viajes.model.Itinerario;
import com.dani.viajes.model.Reserva;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class GestionItinerariosTest {
    private GestionItinerarios gestion;

    @BeforeEach
    void setUp() {
        gestion = new GestionItinerarios();
    }

    @Test
    void testCrearYObtenerItinerario() {
        Itinerario iti = new Itinerario("1", "Paris", LocalDate.of(2025,5,10), LocalDate.of(2025,5,20));
        gestion.crearItinerario(iti);
        Itinerario obtenido = gestion.obtenerItinerario("1");
        assertNotNull(obtenido);
        assertEquals("Paris", obtenido.getDestino());
    }
    @Test
    void testObtenerItinerarioNoExistente() {
        assertNull(gestion.obtenerItinerario("noExiste"));
    }

    @Test
    void testAgregarReservaDentroDelItinerario() {
        Itinerario iti = new Itinerario("2", "Roma", LocalDate.of(2025,6,1), LocalDate.of(2025,6,10));
        gestion.crearItinerario(iti);
        Reserva reserva = new Reserva("r1", "Vuelo", LocalDate.of(2025,6,5));
        gestion.agregarReserva("2", reserva);
        assertEquals(1, gestion.obtenerItinerario("2").getReservas().size());
    }

    @Test
    void testAgregarReservaFueraDelItinerario() {
        Itinerario iti = new Itinerario("3", "Londres", LocalDate.of(2025,7,1), LocalDate.of(2025,7,10));
        gestion.crearItinerario(iti);
        Reserva reserva = new Reserva("r2", "Hotel", LocalDate.of(2025,7,15));
        assertThrows(IllegalArgumentException.class, () -> gestion.agregarReserva("3", reserva));
    }
}
