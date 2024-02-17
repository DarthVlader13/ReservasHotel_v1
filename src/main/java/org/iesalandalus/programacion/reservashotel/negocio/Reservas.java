package org.iesalandalus.programacion.reservashotel.negocio;

import org.iesalandalus.programacion.reservashotel.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.dominio.TipoHabitacion;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.Arrays;

public class Reservas {

    private int capacidad;
    private int tamano;
    private Reserva[] reservas;

    public Reservas(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        this.reservas = new Reserva[capacidad];
    }

    public Reserva[] get() {
        return copiaProfundaReservas();
    }

    public int getTamano() {
        return tamano;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void insertar(Reserva reserva) throws OperationNotSupportedException {
        if (reserva == null) {
            throw new IllegalArgumentException("ERROR: No se puede insertar una reserva nula.");
        }
        if (tamanoSuperado() || buscarIndice(reserva) != -1) {
            throw new OperationNotSupportedException("ERROR: No se puede insertar la reserva.");
        }
        reservas[tamano++] = new Reserva(reserva);
    }

    public Reserva buscar(Reserva reserva) {
        if (reserva == null) {
            throw new IllegalArgumentException("ERROR: No se puede buscar una reserva nula.");
        }
        int indice = buscarIndice(reserva);
        if (indice != -1) {
            return new Reserva(reservas[indice]);
        }
        return null;
    }

    public void borrar(Reserva reserva) throws OperationNotSupportedException {
        if (reserva == null) {
            throw new IllegalArgumentException("ERROR: No se puede borrar una reserva nula.");
        }
        int indice = buscarIndice(reserva);
        if (indice == -1) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna reserva como la indicada.");
        }
        desplazarUnaPosicionHaciaIzquierda(indice);
    }

    // Método para obtener las reservas de un huésped específico
    public Reserva[] getReservas(Huesped huesped) {
        if (huesped == null) {
            throw new IllegalArgumentException("ERROR: No se pueden buscar reservas de un huésped nulo.");
        }

        Reserva[] reservasHuesped = new Reserva[tamano];
        int contador = 0;

        for (int i = 0; i < tamano; i++) {
            if (reservas[i].getHuesped().equals(huesped)) {
                reservasHuesped[contador++] = new Reserva(reservas[i]);
            }
        }

        return Arrays.copyOf(reservasHuesped, contador);
    }

    // Método para obtener las reservas de un tipo de habitación específico
    public Reserva[] getReservas(TipoHabitacion tipoHabitacion) {
        if (tipoHabitacion == null) {
            throw new IllegalArgumentException("ERROR: No se pueden buscar reservas de un tipo de habitación nula.");
        }

        Reserva[] reservasTipo = new Reserva[tamano];
        int contador = 0;

        for (int i = 0; i < tamano; i++) {
            if (reservas[i].getHabitacion().getTipoHabitacion().equals(tipoHabitacion)) {
                reservasTipo[contador++] = new Reserva(reservas[i]);
            }
        }

        return Arrays.copyOf(reservasTipo, contador);
    }

    // Método para obtener las reservas futuras de una habitación específica
    public Reserva[] getReservasFuturas(Habitacion habitacion) {
        if (habitacion == null) {
            throw new IllegalArgumentException("ERROR: No se pueden buscar reservas de una habitación nula.");
        }

        Reserva[] reservasFuturas = new Reserva[tamano];
        int contador = 0;

        for (int i = 0; i < tamano; i++) {
            if (reservas[i].getHabitacion().equals(habitacion) && reservas[i].getFechaInicioReserva().isAfter(LocalDate.now())) {
                reservasFuturas[contador++] = new Reserva(reservas[i]);
            }
        }

        return Arrays.copyOf(reservasFuturas, contador);
    }

    private Reserva[] copiaProfundaReservas() {
        Reserva[] copia = new Reserva[tamano];
        for (int i = 0; i < tamano; i++) {
            copia[i] = new Reserva(reservas[i]);
        }
        return copia;
    }

    private int buscarIndice(Reserva reserva) {
        for (int i = 0; i < tamano; i++) {
            if (reservas[i].equals(reserva)) {
                return i;
            }
        }
        return -1;
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        for (int i = indice; i < tamano - 1; i++) {
            reservas[i] = reservas[i + 1];
        }
        reservas[tamano - 1] = null;
    }

    // En la clase Reservas, cambiar la firma del método consultarDisponibilidad para aceptar un TipoHabitacion
    private boolean tamanoSuperado() {
        return tamano >= capacidad;
    }

    private boolean capacidadSuperada() {
        return tamano >= reservas.length;
    }

    // Método para consultar la disponibilidad de una habitación en un rango de fechas
    public boolean consultarDisponibilidad(TipoHabitacion habitacion, LocalDate fechaInicio, LocalDate fechaFin) {
        if (habitacion == null) {
            throw new IllegalArgumentException("ERROR: No se puede consultar la disponibilidad de una habitación nula.");
        }
        if (fechaInicio == null || fechaFin == null) {
            throw new IllegalArgumentException("ERROR: Las fechas proporcionadas no pueden ser nulas.");
        }
        if (fechaFin.isBefore(fechaInicio)) {
            throw new IllegalArgumentException("ERROR: La fecha de inicio debe ser anterior a la fecha de fin.");
        }

        for (int i = 0; i < tamano; i++) {
            boolean esMismaHabitacion = reservas[i].getHabitacion().equals(habitacion);
            boolean seSolapa = fechaInicio.isBefore(reservas[i].getFechaFinReserva()) && fechaFin.isAfter(reservas[i].getFechaInicioReserva());
            if (esMismaHabitacion && seSolapa) {
                return false; // La habitación no está disponible porque las fechas se solapan con una reserva existente
            }
        }
        return true; // La habitación está disponible si ninguna reserva existente se solapa con el rango de fechas proporcionado
    }
}
