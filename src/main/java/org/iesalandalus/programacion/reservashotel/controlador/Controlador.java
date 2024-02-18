package org.iesalandalus.programacion.reservashotel.controlador;

import org.iesalandalus.programacion.reservashotel.modelo.Modelo;
import org.iesalandalus.programacion.reservashotel.vista.Vista;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.naming.OperationNotSupportedException;

public class Controlador {

    private Modelo modelo;
    private Vista vista;

    public Controlador(Modelo modelo, Vista vista) {
        this.modelo = Objects.requireNonNull(modelo, "El modelo no puede ser nulo.");
        this.vista = Objects.requireNonNull(vista, "La vista no puede ser nula.");
        this.vista.setControlador(this);
    }

    public void comenzar() {
        modelo.comenzar();
        vista.comenzar();
    }

    public void terminar() {
        modelo.terminar();
        vista.terminar();
    }

    public void insertarHuesped(Huesped huesped) throws OperationNotSupportedException {
        modelo.insertar(huesped);
    }

    public Huesped buscarHuesped(Huesped huesped) {
        return modelo.buscar(huesped);
    }

    public void borrarHuesped(Huesped huesped) throws OperationNotSupportedException {
        modelo.borrar(huesped);
    }

    public Huesped[] getHuespedes() {
        return modelo.getHuespedes();
    }

    public void insertarHabitacion(Habitacion habitacion) throws OperationNotSupportedException {
        modelo.insertar(habitacion);
    }

    public Habitacion buscarHabitacion(Habitacion habitacion) {
        return modelo.buscar(habitacion);
    }

    public void borrarHabitacion(Habitacion habitacion) throws OperationNotSupportedException {
        modelo.borrar(habitacion);
    }

    public Habitacion[] getHabitaciones() {
        return modelo.getHabitaciones();
    }

    public void insertarReserva(Reserva reserva) throws OperationNotSupportedException {
        modelo.insertar(reserva);
    }

    public Reserva buscarReserva(Reserva reserva) {
        return modelo.buscar(reserva);
    }

    public void borrarReserva(Reserva reserva) throws OperationNotSupportedException {
        modelo.borrar(reserva);
    }

    public Reserva[] getReservas() {
        return modelo.getReservas();
    }

    public void realizarCheckin(Reserva reserva, LocalDateTime fecha) throws OperationNotSupportedException {
        modelo.realizarCheckin(reserva, fecha);
    }

    public void realizarCheckout(Reserva reserva, LocalDateTime fecha) throws OperationNotSupportedException {
        modelo.realizarCheckout(reserva, fecha);
    }
}
