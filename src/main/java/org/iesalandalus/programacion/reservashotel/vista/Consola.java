package org.iesalandalus.programacion.reservashotel.vista;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Regimen;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Consola {

    // Constructor privado
    private Consola() {
    }
    public static void mostrarMenu() {
        for (Opcion opcion : Opcion.values()) {
            System.out.println(opcion);
        }
    }

    public static Opcion elegirOpcion() {
        int ordinal;
        do {
            System.out.print("Elige una opción: ");
            try {
                ordinal = Entrada.entero();
            } catch (NumberFormatException e) {
                ordinal = -1;
            }
        } while (ordinal < 0 || ordinal >= Opcion.values().length);

        return Opcion.values()[ordinal];
    }

    public static Huesped leerHuesped() {
        System.out.print("Introduce el nombre del huésped: ");
        String nombre = Entrada.cadena();
        System.out.print("Introduce los apellidos del huésped: ");
        String apellidos = Entrada.cadena();
        System.out.print("Introduce el DNI del huésped: ");
        String dni = Entrada.cadena();
        LocalDate fechaNacimiento = leerFecha("Introduce la fecha de nacimiento del huésped (YYYY-MM-DD): ");
        return new Huesped(nombre, apellidos, dni, fechaNacimiento);
    }

    public static Huesped getHuespedPorDni() {
        System.out.print("Introduce el DNI del huésped: ");
        String dni = Entrada.cadena();
        return new Huesped("NombreFicticio", "ApellidosFicticios", dni, null);
    }

    public static LocalDate leerFecha(String mensaje) {
        LocalDate fecha = null;
        do {
            try {
                System.out.print(mensaje);
                String fechaStr = Entrada.cadena();
                fecha = LocalDate.parse(fechaStr);
            } catch (DateTimeException e) {
                System.out.println("Error: Formato de fecha incorrecto.");
            }
        } while (fecha == null);
        return fecha;
    }

    //En este método, usamos un bucle do-while que seguirá pidiendo al usuario que introduzca la fecha y hora hasta que proporcione una cadena válida que pueda ser parseada a un objeto LocalDateTime.
    public static LocalDateTime leerFechaHora(String mensaje) {
        LocalDateTime fechaHora = null;
        do {
            try {
                System.out.print(mensaje);
                String fechaHoraStr = Entrada.cadena();
                fechaHora = LocalDateTime.parse(fechaHoraStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            } catch (DateTimeParseException e) {
                System.out.println("Error: Formato de fecha y hora incorrecto. El formato correcto es: YYYY-MM-DDTHH:MM");
            }
        } while (fechaHora == null);
        return fechaHora;
    }
    public static Habitacion leerHabitacion() {
        System.out.print("Introduce el número de planta de la habitación: ");
        int numeroPlanta = Entrada.entero();
        System.out.print("Introduce el número de puerta de la habitación: ");
        int numeroPuerta = Entrada.entero();
        TipoHabitacion tipoHabitacion = leerTipoHabitacion();
        return new Habitacion(numeroPlanta, numeroPuerta, tipoHabitacion);
    }

    public static Habitacion leerHabitacionPorIdentificador() {
        System.out.print("Introduce el número de planta de la habitación: ");
        int numeroPlanta = Entrada.entero();
        System.out.print("Introduce el número de puerta de la habitación: ");
        int numeroPuerta = Entrada.entero();
        TipoHabitacion tipoHabitacion = leerTipoHabitacion();
        return new Habitacion(numeroPlanta, numeroPuerta, tipoHabitacion);
    }

    public static TipoHabitacion leerTipoHabitacion() {
        System.out.println("Elige un tipo de habitación:");
        for (TipoHabitacion tipo : TipoHabitacion.values()) {
            System.out.println(tipo);
        }
        int ordinal;
        do {
            System.out.print("Introduce el número correspondiente al tipo de habitación: ");
            ordinal = Entrada.entero();
        } while (ordinal < 0 || ordinal >= TipoHabitacion.values().length);
        return TipoHabitacion.values()[ordinal];
    }

    public static Regimen leerRegimen() {
        System.out.println("Elige un régimen:");
        for (Regimen regimen : Regimen.values()) {
            System.out.println(regimen);
        }
        int ordinal;
        do {
            System.out.print("Introduce el número correspondiente al régimen: ");
            ordinal = Entrada.entero();
        } while (ordinal < 0 || ordinal >= Regimen.values().length);
        return Regimen.values()[ordinal];
    }

    public static Reserva leerReserva() {
        Huesped huesped = leerHuesped();
        Habitacion habitacion = leerHabitacion();
        LocalDate fechaInicio = leerFecha("Introduce la fecha de inicio de la reserva (YYYY-MM-DD): ");
        LocalDate fechaFin = leerFecha("Introduce la fecha de fin de la reserva (YYYY-MM-DD): ");
        Regimen regimen = leerRegimen();
        return new Reserva(huesped, habitacion, fechaInicio, fechaFin, regimen);
    }
}