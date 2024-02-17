package org.iesalandalus.programacion.reservashotel;

import org.iesalandalus.programacion.reservashotel.dominio.*;
import org.iesalandalus.programacion.reservashotel.negocio.Habitaciones;
import org.iesalandalus.programacion.reservashotel.negocio.Huespedes;
import org.iesalandalus.programacion.reservashotel.negocio.Reservas;
import org.iesalandalus.programacion.reservashotel.vista.Consola;
import org.iesalandalus.programacion.reservashotel.vista.Opcion;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;

public class MainApp {

    private static final int CAPACIDAD = 100;
    private static final Reservas reservas = new Reservas(CAPACIDAD);
    private static final Habitaciones habitaciones = new Habitaciones(CAPACIDAD);
    private static final Huespedes huespedes = new Huespedes(CAPACIDAD);

    public static void main(String[] args) {
        do {
            Consola.mostrarMenu();
            Opcion opcion = Consola.elegirOpcion();
            ejecutarOpcion(opcion);
        } while (true);
    }

    private static void ejecutarOpcion(Opcion opcion) {
        switch (opcion) {
            case INSERTAR_HUESPED:
                insertarHuesped();
                break;
            case BUSCAR_HUESPED:
                buscarHuesped();
                break;
            case BORRAR_HUESPED:
                borrarHuesped();
                break;
            case MOSTRAR_HUESPEDES:
                mostrarHuespedes();
                break;
            case INSERTAR_HABITACION:
                insertarHabitacion();
                break;
            case BUSCAR_HABITACION:
                buscarHabitacion();
                break;
            case BORRAR_HABITACION:
                borrarHabitacion();
                break;
            case MOSTRAR_HABITACIONES:
                mostrarHabitaciones();
                break;
            case INSERTAR_RESERVA:
                insertarReserva();
                break;
            case MOSTRAR_RESERVAS:
                mostrarReservas();
                break;
            case ANULAR_RESERVA:
                anularReserva();
                break;
            case CONSULTAR_DISPONIBILIDAD:
                consultarDisponibilidad();
                break;
            case SALIR:
                System.out.println("Saliendo del programa.");
                System.exit(0);
                break;
            default:
                System.out.println("Opci�n no reconocida.");
                break;
        }
    }

    private static void insertarHuesped() {
        try {
            Huesped nuevoHuesped = Consola.leerHuesped();
            huespedes.insertar(nuevoHuesped);
            System.out.println("Hu�sped insertado correctamente.");
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede insertar un hu�sped nulo.");
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void buscarHuesped() {
        try {
            Huesped huespedBuscado = Consola.getHuespedPorDni();
            Huesped encontrado = huespedes.buscar(huespedBuscado);
            if (encontrado != null) {
                System.out.println("Huesped encontrado: " + encontrado);
            } else {
                System.out.println("Huesped no encontrado.");
            }
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede buscar un hu�sped nulo.");
        }
    }

    private static void borrarHuesped() {
        try {
            Huesped huespedBorrar = Consola.getHuespedPorDni();
            huespedes.borrar(huespedBorrar);
            System.out.println("Hu�sped borrado correctamente.");
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede borrar un hu�sped nulo.");
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void mostrarHuespedes() {
        Huesped[] arrayHuespedes = huespedes.get();
        if (arrayHuespedes.length > 0) {
            for (Huesped huesped : arrayHuespedes) {
                System.out.println(huesped);
            }
        } else {
            System.out.println("No hay hu�spedes.");
        }
    }

    private static void insertarHabitacion() {
        try {
            Habitacion nuevaHabitacion = Consola.leerHabitacion();
            habitaciones.insertar(nuevaHabitacion);
            System.out.println("Habitaci�n insertada correctamente.");
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede insertar una habitaci�n nula.");
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void buscarHabitacion() {
        try {
            Habitacion habitacionBuscada = Consola.leerHabitacionPorIdentificador();
            Habitacion encontrada = habitaciones.buscar(habitacionBuscada);
            if (encontrada != null) {
                System.out.println("Habitaci�n encontrada: " + encontrada);
            } else {
                System.out.println("Habitaci�n no encontrada.");
            }
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede buscar una habitaci�n nula.");
        }
    }

    private static void borrarHabitacion() {
        try {
            Habitacion habitacionBorrar = Consola.leerHabitacionPorIdentificador();
            habitaciones.borrar(habitacionBorrar);
            System.out.println("Habitaci�n borrada correctamente.");
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede borrar una habitaci�n nula.");
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void mostrarHabitaciones() {
        Habitacion[] arrayHabitaciones = habitaciones.get();
        if (arrayHabitaciones.length > 0) {
            for (Habitacion habitacion : arrayHabitaciones) {
                System.out.println(habitacion);
            }
        } else {
            System.out.println("No hay habitaciones.");
        }
    }

    private static void insertarReserva() {
        try {
            Reserva nuevaReserva = Consola.leerReserva();
            reservas.insertar(nuevaReserva);
            System.out.println("Reserva insertada correctamente.");
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede insertar una reserva nula.");
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void mostrarReservas() {
        Reserva[] arrayReservas = reservas.get();
        if (arrayReservas.length > 0) {
            for (Reserva reserva : arrayReservas) {
                System.out.println(reserva);
            }
        } else {
            System.out.println("No hay reservas.");
        }
    }

    private static void anularReserva() {
        try {
            Huesped huesped = Consola.getHuespedPorDni();
            Reserva reservaAnular = reservas.buscar(Consola.leerReserva());
            if (reservaAnular != null && reservaAnular.getHuesped().equals(huesped)) {
                reservas.borrar(reservaAnular);
                System.out.println("Reserva anulada correctamente.");
            } else {
                System.out.println("No se ha encontrado la reserva o no corresponde al hu�sped indicado.");
            }
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede anular una reserva nula.");
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void consultarDisponibilidad() {
        TipoHabitacion tipoHabitacion = Consola.leerTipoHabitacion();
        LocalDate fechaInicio = Consola.leerFecha("Introduce la fecha de inicio de la reserva (YYYY-MM-DD): ");
        LocalDate fechaFin = Consola.leerFecha("Introduce la fecha de fin de la reserva (YYYY-MM-DD): ");

        boolean disponibilidad = reservas.consultarDisponibilidad(tipoHabitacion, fechaInicio, fechaFin);
        if (disponibilidad) {
            System.out.println("Hay habitaciones disponibles del tipo " + tipoHabitacion + " en las fechas indicadas.");
        } else {
            System.out.println("No hay habitaciones disponibles del tipo " + tipoHabitacion + " en las fechas indicadas.");
        }
    }

    private static void mostrarListaReservas(Reserva[] reservas) {
        if (reservas != null && reservas.length > 0) {
            for (Reserva reserva : reservas) {
                System.out.println(reserva);
            }
        } else {
            System.out.println("No hay reservas que mostrar.");
        }
    }

    // Los m�todos para listarReservasHuesped y listarReservasTipoHabitacion se implementan de forma similar
    // a los m�todos de mostrarHuespedes y mostrarHabitaciones, pero filtrando por el criterio correspondiente.

    private static void listarReservasHuesped(Huesped huesped) {
        Reserva[] reservasHuesped = reservas.getReservas(huesped);
        mostrarListaReservas(reservasHuesped);
    }

    private static void listarReservasTipoHabitacion(TipoHabitacion tipoHabitacion) {
        Reserva[] reservasTipoHabitacion = reservas.getReservas(tipoHabitacion);
        mostrarListaReservas(reservasTipoHabitacion);
    }
}
