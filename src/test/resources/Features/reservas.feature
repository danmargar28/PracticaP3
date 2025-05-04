Feature: Gestión de reservas

  Scenario: Agregar una reserva válida a un itinerario existente
    Given un itinerario con id "X1", destino "Paris" y fechas desde "2025-05-10" hasta "2025-05-20"
    When agrego una reserva con id "R1", descripción "Vuelo", fecha "2025-05-15" al itinerario "X1"
    Then el itinerario "X1" debe tener 1 reserva

  Scenario: Intentar agregar una reserva fuera de rango
    Given un itinerario con id "X2", destino "Roma" y fechas desde "2025-06-01" hasta "2025-06-10"
    When intento agregar una reserva con id "R2", descripción "Hotel", fecha "2025-06-15" al itinerario "X2"
    Then se lanza una excepción de tipo IllegalArgumentException

Feature: Listar reservas de un itinerario

  Scenario: Itinerario con varias reservas
    Given un itinerario con id "L1", destino "Londres" y fechas desde "2025-10-01" hasta "2025-10-10"
    And agrego una reserva con id "A1", descripción "Tour", fecha "2025-10-03" al itinerario "L1"
    And agrego una reserva con id "A2", descripción "Hotel", fecha "2025-10-05" al itinerario "L1"
    When pido listar las reservas del itinerario "L1"
    Then la lista de reservas debe tener 2 elementos

  Scenario: Itinerario no existente
    When pido listar las reservas del itinerario "NoExiste"
    Then se lanza una excepción de tipo IllegalArgumentException
