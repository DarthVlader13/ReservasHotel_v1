package org.iesalandalus.programacion.reservashotel.dominio;

import java.util.Objects;

public class Habitacion {
    public static final double MIN_PRECIO_HABITACION = 40.0;
    public static final double MAX_PRECIO_HABITACION = 150.0;
    public static final int MIN_NUMERO_PUERTA = 0;
    public static final int MAX_NUMERO_PUERTA = 14;
    public static final int MIN_NUMERO_PLANTA = 0;
    public static final int MAX_NUMERO_PLANTA = 2;

    private String identificador;
    private int planta;
    private int puerta;
    private double precio;
    private TipoHabitacion tipoHabitacion;

    public Habitacion(int planta, int puerta, double precio, TipoHabitacion tipoHabitacion) {
        setPlanta(planta);
        setPuerta(puerta);
        setPrecio(precio);
        setTipoHabitacion(tipoHabitacion);
        setIdentificador();
    }

    public Habitacion(Habitacion habitacion) {
        if (habitacion == null) {
            throw new NullPointerException("ERROR: No es posible copiar una habitación nula.");
        }
        setPlanta(habitacion.getPlanta());
        setPuerta(habitacion.getPuerta());
        setPrecio(habitacion.getPrecio());
        setTipoHabitacion(habitacion.getTipoHabitacion());
        setIdentificador();
    }

    public Habitacion(int numeroPlanta, int numeroPuerta, TipoHabitacion tipoHabitacion) {
    }

    private void setIdentificador() {
        identificador = String.format("%d-%02d", planta, puerta);
    }

    public String getIdentificador() {
        return identificador;
    }

    public int getPlanta() {
        return planta;
    }

    public void setPlanta(int planta) {
        if (planta < MIN_NUMERO_PLANTA || planta > MAX_NUMERO_PLANTA) {
            throw new IllegalArgumentException("ERROR: El número de planta no es válido.");
        }
        this.planta = planta;
        setIdentificador();
    }

    public int getPuerta() {
        return puerta;
    }

    public void setPuerta(int puerta) {
        if (puerta < MIN_NUMERO_PUERTA || puerta > MAX_NUMERO_PUERTA) {
            throw new IllegalArgumentException("ERROR: El número de puerta no es válido.");
        }
        this.puerta = puerta;
        setIdentificador();
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        if (precio < MIN_PRECIO_HABITACION || precio > MAX_PRECIO_HABITACION) {
            throw new IllegalArgumentException("ERROR: El precio no es válido.");
        }
        this.precio = precio;
    }

    public TipoHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
        if (tipoHabitacion == null) {
            throw new NullPointerException("ERROR: El tipo de habitación no puede ser nulo.");
        }
        this.tipoHabitacion = tipoHabitacion;
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificador);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Habitacion other = (Habitacion) obj;
        return Objects.equals(identificador, other.identificador);
    }

    @Override
    public String toString() {
        return String.format("identificador=%s, planta=%d, puerta=%d, precio=%.2f, tipo habitación=%s",
                identificador, planta, puerta, precio, tipoHabitacion);
    }
}
