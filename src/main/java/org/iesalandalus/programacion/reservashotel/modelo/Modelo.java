package org.iesalandalus.programacion.reservashotel.modelo;

// Importa las clases necesarias
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.Habitaciones;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.Huespedes;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.Reservas;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDateTime;

public class Modelo {

    private static final int CAPACIDAD = 10; // Ejemplo de capacidad, ajusta según sea necesario

    private Huespedes huespedes;
    private Habitaciones habitaciones;
    private Reservas reservas;

    public Modelo() {
        // Constructor que inicializa las colecciones
        comenzar();
    }

    public void comenzar() {
        // Crear instancias de las clases de negocio
        huespedes = new Huespedes(CAPACIDAD);
        habitaciones = new Habitaciones(CAPACIDAD);
        reservas = new Reservas(CAPACIDAD);
    }

    public void terminar() {
        // Mostrar mensaje informativo
        System.out.println("El modelo ha terminado su ejecución.");
    }

    // Métodos para insertar elementos en las colecciones
    public void insertar(Huesped huesped) throws OperationNotSupportedException {
        huespedes.insertar(huesped);
    }

    public void insertar(Habitacion habitacion) throws OperationNotSupportedException {
        habitaciones.insertar(habitacion);
    }

    public void insertar(Reserva reserva) throws OperationNotSupportedException {
        reservas.insertar(reserva);
    }

    // Métodos para buscar elementos en las colecciones
    public Huesped buscar(Huesped huesped) {
        return huespedes.buscar(huesped);
    }

    public Habitacion buscar(Habitacion habitacion) {
        return habitaciones.buscar(habitacion);
    }

    public Reserva buscar(Reserva reserva) {
        return reservas.buscar(reserva);
    }

    // Métodos para borrar elementos en las colecciones
    public void borrar(Huesped huesped) throws OperationNotSupportedException {
        huespedes.borrar(huesped);
    }

    public void borrar(Habitacion habitacion) throws OperationNotSupportedException {
        habitaciones.borrar(habitacion);
    }

    public void borrar(Reserva reserva) throws OperationNotSupportedException {
        reservas.borrar(reserva);
    }

    // Métodos para obtener elementos de las colecciones
    public Huesped[] getHuespedes() {
        return huespedes.get();
    }

    public Habitacion[] getHabitaciones() {
        return habitaciones.get();
    }

    public Reserva[] getReservas() {
        return reservas.get();
    }

    // Métodos para realizar check-in y check-out en las reservas
    public void realizarCheckin(Reserva reserva, LocalDateTime fecha) throws OperationNotSupportedException {
        reservas.realizarCheckin(reserva, fecha);
    }

    public void realizarCheckout(Reserva reserva, LocalDateTime fecha) throws OperationNotSupportedException {
        reservas.realizarCheckout(reserva, fecha);
    }
}
