package src.pharmacontrol.service;

import java.time.LocalDate;
import java.time.LocalTime;

import src.pharmacontrol.model.Asistencia;

public class AsistenciaService {

    private Asistencia[] asistencias;
    private int cantidadAsistencias;

    // Hora limite de entrada: 8:10 a. m. (10 min de tolerancia)
    private LocalTime horaLimite = LocalTime.of(8, 10);

    // Constructor
    public AsistenciaService() {
        asistencias = new Asistencia[100];
        cantidadAsistencias = 0;
    }

    // Registra la asistencia de un empleado.
    public boolean registrarAsistencia(String nombreEmpleado) {

        // Verificamos si hay espacio disponible.
        if (cantidadAsistencias >= asistencias.length) {
            System.out.println("No se pueden registrar mas asistencias.");
            return false;
        }

        // Fecha y hora actual del sistema.
        LocalDate fechaActual = LocalDate.now();
        LocalTime horaActual = LocalTime.now();

        // Verificamos si el empleado ya registro su entrada hoy.
        for (int i = 0; i < cantidadAsistencias; i++) {

            if (asistencias[i].getNombreEmpleado().equals(nombreEmpleado)
                    && asistencias[i].getFecha().equals(fechaActual.toString())) {

                System.out.println("Ya registraste tu asistencia hoy.");
                return false;
            }
        }

        // Verificar si llego puntual o tarde.
        String estado;

        if (horaActual.withSecond(0).withNano(0).isAfter(horaLimite)) {
            estado = "Tardanza";
        } else {
            estado = "Puntual";
        }

        // Guardamos la hora sin segundos.
        String horaEntrada = horaActual.withSecond(0).withNano(0).toString();

        // Creamos el objeto Asistencia.
        Asistencia asistencia = new Asistencia(nombreEmpleado, fechaActual.toString(), horaEntrada, estado);

        // Guardamos la asistencia en el arreglo.
        asistencias[cantidadAsistencias] = asistencia;
        cantidadAsistencias++;

        System.out.println("Asistencia registrada correctamente.");
        System.out.println("Empleado: " + nombreEmpleado);
        System.out.println("Fecha: " + fechaActual);
        System.out.println("Hora de entrada: " + horaEntrada);
        System.out.println("Estado: " + estado);

        return true;
    }

    // Devuelve todas las asistencias registradas.
    public Asistencia[] obtenerAsistencias() {
        return asistencias;
    }

    // Devuelve la cantidad de registros.
    public int getCantidadAsistencias() {
        return cantidadAsistencias;
    }
}