package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import javax.naming.OperationNotSupportedException;

public class Huespedes {
    private Huesped[] coleccionHuespedes;
    private int capacidad;
    private int tamano;

    public Huespedes(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser un número mayor que 0.");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        this.coleccionHuespedes = new Huesped[capacidad];
    }

    public Huesped[] get() {
        return copiaProfundaHuespedes();
    }

    private Huesped[] copiaProfundaHuespedes() {
        Huesped[] copia = new Huesped[tamano];
        for (int i = 0; i < tamano; i++) {
            copia[i] = new Huesped(coleccionHuespedes[i]);
        }
        return copia;
    }

    public void insertar(Huesped huesped) throws OperationNotSupportedException {
        if (huesped == null) {
            throw new IllegalArgumentException("ERROR: No se puede insertar un huésped nulo.");
        }
        if (tamanoSuperado() || buscarIndice(huesped) != -1) {
            throw new OperationNotSupportedException("ERROR: No se puede insertar el huésped.");
        }
        coleccionHuespedes[tamano++] = new Huesped(huesped);
    }

    private int buscarIndice(Huesped huesped) {
        if (huesped == null) {
            throw new IllegalArgumentException("ERROR: No se puede buscar un huésped nulo.");
        }
        for (int i = 0; i < tamano; i++) {
            if (coleccionHuespedes[i].equals(huesped)) {
                return i;
            }
        }
        return -1;
    }

    public Huesped buscar(Huesped huesped) {
        if (huesped == null) {
            throw new IllegalArgumentException("ERROR: No se puede buscar un huésped nulo.");
        }
        int indice = buscarIndice(huesped);
        return (indice == -1) ? null : new Huesped(coleccionHuespedes[indice]);
    }

    public void borrar(Huesped huesped) throws OperationNotSupportedException {
        if (huesped == null) {
            throw new IllegalArgumentException("ERROR: No se puede borrar un huésped nulo.");
        }
        int indice = buscarIndice(huesped);
        if (indice == -1) {
            throw new OperationNotSupportedException("ERROR: No existe ningún huésped con ese DNI.");
        }
        desplazarUnaPosicionHaciaIzquierda(indice);
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        for (int i = indice; i < tamano - 1; i++) {
            coleccionHuespedes[i] = coleccionHuespedes[i + 1];
        }
        coleccionHuespedes[--tamano] = null;
    }

    private boolean tamanoSuperado() {
        return tamano == capacidad;
    }

    public int getTamano() {
        return tamano;
    }

    public int getCapacidad() {
        return capacidad;
    }

    private boolean capacidadSuperada(int indice) {
        return indice >= capacidad;
    }
}
