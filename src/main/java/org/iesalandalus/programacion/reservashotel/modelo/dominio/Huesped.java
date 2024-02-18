package org.iesalandalus.programacion.reservashotel.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Huesped {
    private static final String ER_TELEFONO = "\\d{9}";
    private static final String ER_CORREO = "^[\\w-\\.]+@[\\w-]+(\\.[\\w-]+)*(\\.[a-zA-Z]{2,})$";
    private static final String ER_DNI = "\\d{8}[A-HJ-NP-TV-Z]";
    private static final String FORMATO_FECHA = "dd/MM/yyyy";

    private String nombre;
    private String telefono;
    private String correo;
    private String dni;
    private LocalDate fechaNacimiento;

    public Huesped(String nombre, String dni, String correo, String telefono, LocalDate fechaNacimiento) {
        setNombre(nombre);
        setDni(dni);
        setCorreo(correo);
        setTelefono(telefono);
        setFechaNacimiento(fechaNacimiento);
    }

    public Huesped(Huesped huesped) {
        if (huesped == null) {
            throw new NullPointerException("ERROR: No es posible copiar un huésped nulo.");
        }
        setNombre(huesped.getNombre());
        setDni(huesped.getDni());
        setCorreo(huesped.getCorreo());
        setTelefono(huesped.getTelefono());
        this.fechaNacimiento = huesped.getFechaNacimiento();
    }

    public Huesped(String nombre, String apellidos, String dni, LocalDate fechaNacimiento) {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null) {
            throw new NullPointerException("ERROR: El nombre no puede ser nulo.");
        }
        if (nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("ERROR: El nombre no puede estar vacío.");
        }
        this.nombre = formateaNombre(nombre);
    }

    private String formateaNombre(String nombre) {
        String[] palabras = nombre.trim().split("\\s+");
        StringBuilder nombreFormateado = new StringBuilder();
        for (String palabra : palabras) {
            nombreFormateado.append(palabra.substring(0, 1).toUpperCase()).append(palabra.substring(1).toLowerCase()).append(" ");
        }
        return nombreFormateado.toString().trim();
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (telefono == null) {
            throw new NullPointerException("ERROR: El teléfono no puede ser nulo.");
        }
        if (!telefono.matches(ER_TELEFONO)) {
            throw new IllegalArgumentException("ERROR: El teléfono no tiene un formato válido.");
        }
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        if (correo == null) {
            throw new NullPointerException("ERROR: El correo no puede ser nulo.");
        }
        if (!correo.matches(ER_CORREO)) {
            throw new IllegalArgumentException("ERROR: El correo no tiene un formato válido.");
        }
        this.correo = correo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        if (dni == null) {
            throw new NullPointerException("ERROR: El DNI no puede ser nulo.");
        }
        if (!dni.matches(ER_DNI) || !comprobarLetraDni(dni)) {
            throw new IllegalArgumentException("ERROR: El DNI no tiene un formato válido o la letra no es correcta.");
        }
        this.dni = dni;
    }

    private boolean comprobarLetraDni(String dni) {
        Pattern pattern = Pattern.compile(ER_DNI);
        Matcher matcher = pattern.matcher(dni);
        if (!matcher.matches()) {
            return false;
        }
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        int numeroDni = Integer.parseInt(dni.substring(0, 8));
        char letraCalculada = letras.charAt(numeroDni % 23);
        return dni.charAt(8) == letraCalculada;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) {
            throw new NullPointerException("ERROR: La fecha de nacimiento no puede ser nula.");
        }
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getIniciales() {
        String[] palabras = nombre.split("\\s+");
        StringBuilder iniciales = new StringBuilder();
        for (String palabra : palabras) {
            iniciales.append(palabra.charAt(0));
        }
        return iniciales.toString().toUpperCase();
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Huesped other = (Huesped) obj;
        return Objects.equals(dni, other.dni);
    }

    @Override
    public String toString() {
        String iniciales = getIniciales();
        return String.format("nombre=%s (%s), DNI=%s, correo=%s, teléfono=%s, fecha nacimiento=%s",
                formateaNombre(nombre), iniciales, dni, correo, telefono, fechaNacimiento.format(DateTimeFormatter.ofPattern(FORMATO_FECHA)));
    }
}