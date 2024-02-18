package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;

import javax.naming.OperationNotSupportedException;
import java.util.Arrays;

public class Habitaciones {
    private int capacidad;
    private int tamano;
    private Habitacion[] coleccionHabitaciones;

    public Habitaciones(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser un n�mero mayor que 0.");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        this.coleccionHabitaciones = new Habitacion[capacidad];
    }

    public Habitacion[] get() {
        return copiaProfundaHabitaciones();
    }

    public Habitacion[] get(TipoHabitacion tipo) {
        Habitacion[] copia = new Habitacion[tamano];
        int indiceCopia = 0;
        for (int i = 0; i < tamano; i++) {
            if (coleccionHabitaciones[i].getTipoHabitacion() == tipo) {
                copia[indiceCopia++] = new Habitacion(coleccionHabitaciones[i]);
            }
        }
        return Arrays.copyOf(copia, indiceCopia);
    }

    private Habitacion[] copiaProfundaHabitaciones() {
        Habitacion[] copia = new Habitacion[tamano];
        for (int i = 0; i < tamano; i++) {
            copia[i] = new Habitacion(coleccionHabitaciones[i]);
        }
        return copia;
    }

    public int getTamano() {
        return tamano;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void insertar(Habitacion habitacion) throws OperationNotSupportedException {
        if (habitacion == null) {
            throw new IllegalArgumentException("ERROR: No se puede insertar una habitaci�n nula.");
        }
        if (tamanoSuperado()) {
            throw new OperationNotSupportedException("ERROR: No se pueden insertar m�s habitaciones.");
        }
        if (buscarIndice(habitacion) != -1) {
            throw new OperationNotSupportedException("ERROR: Ya existe una habitaci�n con ese identificador.");
        }
        coleccionHabitaciones[tamano++] = new Habitacion(habitacion);
    }

    private int buscarIndice(Habitacion habitacion) {
        if (habitacion == null) {
            throw new IllegalArgumentException("ERROR: No se puede buscar un �ndice de habitaci�n nula.");
        }
        for (int i = 0; i < tamano; i++) {
            if (coleccionHabitaciones[i].equals(habitacion)) {
                return i;
            }
        }
        return -1;
    }

    private boolean tamanoSuperado() {
        return tamano >= capacidad;
    }

    private boolean capacidadSuperada(int indice) {
        return indice >= capacidad;
    }

    public Habitacion buscar(Habitacion habitacion) {
        int indice = buscarIndice(habitacion);
        if (indice != -1) {
            return new Habitacion(coleccionHabitaciones[indice]);
        } else {
            return null;
        }
    }

    public void borrar(Habitacion habitacion) throws OperationNotSupportedException {
        int indice = buscarIndice(habitacion);
        if (indice == -1) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna habitaci�n con ese identificador.");
        }
        desplazarUnaPosicionHaciaIzquierda(indice);
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        for (int i = indice; i < tamano - 1; i++) {
            coleccionHabitaciones[i] = coleccionHabitaciones[i + 1];
        }
        coleccionHabitaciones[--tamano] = null;
    }
}
