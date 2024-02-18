package org.iesalandalus.programacion.reservashotel.vista;

import org.iesalandalus.programacion.reservashotel.controlador.Controlador;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.Habitaciones;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.Huespedes;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.Reservas;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Vista {

    private static final int CAPACIDAD = 100;
    private static final Reservas reservas = new Reservas(CAPACIDAD);
    private static final Habitaciones habitaciones = new Habitaciones(CAPACIDAD);
    private static final Huespedes huespedes = new Huespedes(CAPACIDAD);

    private Controlador controlador;

    public Vista() {
        // Constructor que podría inicializar componentes de la vista si fuera necesario
    }

    public void setControlador(Controlador controlador) {
        if (controlador == null) {
            throw new IllegalArgumentException("El controlador no puede ser nulo.");
        }
        this.controlador = controlador;
    }

    public void comenzar() {
        Opcion opcion;
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            ejecutarOpcion(opcion);
        } while (opcion != Opcion.SALIR);
    }

    public void terminar() {
        System.out.println("¡Hasta la próxima!");
    }

    private void ejecutarOpcion(Opcion opcion) {
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
            case REALIZAR_CHECKIN:
                realizarCheckin();
                break;
            case REALIZAR_CHECKOUT:
                realizarCheckout();
                break;
            case SALIR:
                terminar();
                break;
            default:
                System.out.println("Opción no reconocida.");
                break;
        }
    }

    // Método de ejemplo para insertar un huésped
    private static void insertarHuesped() {
        try {
            Huesped nuevoHuesped = Consola.leerHuesped();
            huespedes.insertar(nuevoHuesped);
            System.out.println("Huésped insertado correctamente.");
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede insertar un huésped nulo.");
        } catch (OperationNotSupportedException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // Métodos para buscar, borrar, mostrar, etc.
    private void buscarHuesped() {
        Huesped huesped = Consola.leerHuesped();
        Huesped huespedEncontrado = controlador.buscarHuesped(huesped);
        if (huespedEncontrado != null) {
            System.out.println("Huésped encontrado: " + huespedEncontrado);
        } else {
            System.out.println("Huésped no encontrado.");
        }
    }

    private static void borrarHuesped() {
        try {
            Huesped huespedBorrar = Consola.getHuespedPorDni();
            huespedes.borrar(huespedBorrar);
            System.out.println("Huésped borrado correctamente.");
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede borrar un huésped nulo.");
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
            System.out.println("No hay huéspedes.");
        }
    }

    private static void insertarHabitacion() {
        try {
            Habitacion nuevaHabitacion = Consola.leerHabitacion();
            habitaciones.insertar(nuevaHabitacion);
            System.out.println("Habitación insertada correctamente.");
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede insertar una habitación nula.");
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void buscarHabitacion() {
        try {
            Habitacion habitacionBuscada = Consola.leerHabitacionPorIdentificador();
            Habitacion encontrada = habitaciones.buscar(habitacionBuscada);
            if (encontrada != null) {
                System.out.println("Habitación encontrada: " + encontrada);
            } else {
                System.out.println("Habitación no encontrada.");
            }
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede buscar una habitación nula.");
        }
    }

    private static void borrarHabitacion() {
        try {
            Habitacion habitacionBorrar = Consola.leerHabitacionPorIdentificador();
            habitaciones.borrar(habitacionBorrar);
            System.out.println("Habitación borrada correctamente.");
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede borrar una habitación nula.");
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
                System.out.println("No se ha encontrado la reserva o no corresponde al huésped indicado.");
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

    private static void listarReservasHuesped(Huesped huesped) {
        Reserva[] reservasHuesped = reservas.getReservas(huesped);
        mostrarListaReservas(reservasHuesped);
    }

    private static void listarReservasTipoHabitacion(TipoHabitacion tipoHabitacion) {
        Reserva[] reservasTipoHabitacion = reservas.getReservas(tipoHabitacion);
        mostrarListaReservas(reservasTipoHabitacion);
    }

    // Métodos para realizar check-in y check-out

    private void realizarCheckin() {
        Huesped huesped = Consola.getHuespedPorDni();
        Reserva[] todasReservas = controlador.getReservas();
        List<Reserva> reservasHuesped = Arrays.stream(todasReservas)
                .filter(reserva -> reserva.getHuesped().equals(huesped))
                .collect(Collectors.toList());

        if (reservasHuesped.isEmpty()) {
            System.out.println("No hay reservas para el huésped con DNI: " + huesped.getDni());
            return;
        }

        System.out.println("Selecciona la reserva para realizar el check-in:");
        for (int i = 0; i < reservasHuesped.size(); i++) {
            System.out.println((i + 1) + ". " + reservasHuesped.get(i));
        }
        int indice = Entrada.entero() - 1;
        if (indice < 0 || indice >= reservasHuesped.size()) {
            System.out.println("Selección no válida");
            return;
        }
        Reserva reservaSeleccionada = reservasHuesped.get(indice);
        LocalDateTime fechaHoraCheckin = Consola.leerFechaHora("Introduce la fecha y hora de check-in (YYYY-MM-DDTHH:MM): ");

        try {
            controlador.realizarCheckin(reservaSeleccionada, fechaHoraCheckin);
            System.out.println("Check-in realizado con éxito.");
        } catch (OperationNotSupportedException e) {
            System.out.println("No se pudo realizar el check-in: " + e.getMessage());
        }
    }

    private void realizarCheckout() {
        Huesped huesped = Consola.getHuespedPorDni();
        Reserva[] todasReservas = controlador.getReservas();
        List<Reserva> reservasHuesped = Arrays.stream(todasReservas)
                .filter(reserva -> reserva.getHuesped().equals(huesped))
                .toList();

        if (reservasHuesped.isEmpty()) {
            System.out.println("No hay reservas para el huésped con DNI: " + huesped.getDni());
            return;
        }

        System.out.println("Selecciona la reserva para realizar el check-out:");
        for (int i = 0; i < reservasHuesped.size(); i++) {
            System.out.println((i + 1) + ". " + reservasHuesped.get(i));
        }
        int indice = Entrada.entero() - 1;
        if (indice < 0 || indice >= reservasHuesped.size()) {
            System.out.println("Selección no válida");
            return;
        }
        Reserva reservaSeleccionada = reservasHuesped.get(indice);
        LocalDateTime fechaHoraCheckout = Consola.leerFechaHora("Introduce la fecha y hora de check-out (YYYY-MM-DDTHH:MM): ");

        try {
            controlador.realizarCheckout(reservaSeleccionada, fechaHoraCheckout);
            System.out.println("Check-out realizado con éxito.");
        } catch (OperationNotSupportedException e) {
            System.out.println("No se pudo realizar el check-out: " + e.getMessage());
        }
    }


}
