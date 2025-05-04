package com.dani.viajes.steps;

import java.util.List;
import com.dani.viajes.model.Itinerario;
import com.dani.viajes.model.Reserva;
import com.dani.viajes.service.GestionItinerarios;
import io.cucumber.java.Before;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ReservasSteps {

    private GestionItinerarios gestion;
    private Exception exception;

    @Before
    public void setUp() {
        gestion = new GestionItinerarios();
        exception = null;
    }

    @Dado("un itinerario con id {string}, destino {string} y fechas desde {string} hasta {string}")
    public void dadoUnItinerario(String id, String destino, String fechaInicio, String fechaFin) {
        LocalDate inicio = LocalDate.parse(fechaInicio);
        LocalDate fin    = LocalDate.parse(fechaFin);
        gestion.crearItinerario(new Itinerario(id, destino, inicio, fin));
    }

    @Cuando("agrego una reserva con id {string}, descripción {string}, fecha {string} al itinerario {string}")
    public void cuandoAgregoReserva(String idR, String desc, String fecha, String idItinerario) {
        try {
            gestion.agregarReserva(idItinerario, new Reserva(idR, desc, LocalDate.parse(fecha)));
        } catch (Exception e) {
            exception = e;
        }
    }

    @Cuando("intento agregar una reserva con id {string}, descripción {string}, fecha {string} al itinerario {string}")
    public void cuandoIntentoAgregarFueraDeRango(String idR, String desc, String fecha, String idItinerario) {
        // Reutilizamos el mismo método para capturar la excepción
        cuandoAgregoReserva(idR, desc, fecha, idItinerario);
    }

    @Entonces("el itinerario {string} debe tener {int} reserva")
    public void entoncesItinerarioDebeTenerReserva(String idItinerario, int cantidad) {
        assertNull(exception, "No debió lanzarse ninguna excepción");
        assertEquals(cantidad, gestion.obtenerItinerario(idItinerario).getReservas().size());
    }

    @Entonces("se lanza una excepción de tipo IllegalArgumentException")
    public void entoncesSeLanzaIllegalArgumentException() {
        assertNotNull(exception, "Se esperaba una excepción");
        assertTrue(exception instanceof IllegalArgumentException,
                "La excepción debe ser IllegalArgumentException");
    }
    private List<Reserva> listaResultado;

    @Cuando("pido listar las reservas del itinerario {string}")
    public void cuandoListarReservas(String idItinerario) {
        try {
            listaResultado = gestion.obtenerReservas(idItinerario);
        } catch (Exception e) {
            exception = e;
        }
    }

    @Entonces("la lista de reservas debe tener {int} elementos")
    public void entoncesListaDebeTenerN(int size) {
        assertNull(exception, "No debe lanzarse excepción aquí");
        assertEquals(size, listaResultado.size());
    }

}
