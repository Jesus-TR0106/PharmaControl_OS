package src.pharmacontrol.service;

import java.time.LocalDate;
import java.time.LocalTime;

import src.pharmacontrol.model.RegistroTemperatura;

public class TemperaturaService {

    private RegistroTemperatura[] registros;
    private int cantidadRegistros;

    // Constructor
    public TemperaturaService() {
        registros = new RegistroTemperatura[100];
        cantidadRegistros = 0;
    }

    // Registrar una nueva temperatura
    public boolean registrarTemperatura(double temperatura) {

        // Validamos que sea un numero valido
        if (!Double.isFinite(temperatura)) {
            System.out.println("Temperatura no valida.");
            return false;
        }

        // Verificamos si hay espacio disponible
        if (cantidadRegistros >= registros.length) {
            System.out.println("No se pueden registrar mas temperaturas.");
            return false;
        }

        // Obtenemos la fecha y hora actual
        LocalDate fechaActual = LocalDate.now();
        LocalTime horaActual = LocalTime.now();

        // Determinamos el estado de la temperatura
        String estado;

        if (temperatura >= 2 && temperatura <= 8) {
            estado = "Correcta";
        } else {
            estado = "Fuera de rango";
        }

        // Guardamos la hora sin segundos
        String hora = horaActual.withSecond(0)
                .withNano(0)
                .toString();

        // Creamos el registro
        RegistroTemperatura registro = new RegistroTemperatura(
                fechaActual.toString(),
                hora,
                temperatura,
                estado);

        // Guardamos el registro en el arreglo
        registros[cantidadRegistros] = registro;
        cantidadRegistros++;

        System.out.println("\nTemperatura registrada correctamente.");
        registro.mostrarInformacion();

        // Mostramos una alerta cuando sea necesario
        if (estado.equals("Fuera de rango")) {
            System.out.println("ALERTA: Temperatura fuera del rango permitido.");
            System.out.println("Verificar la refrigeradora.");
        }

        return true;
    }

    // Obtener todos los registros
    public RegistroTemperatura[] obtenerRegistros() {
        return registros;
    }

    // Obtener la cantidad de registros
    public int getCantidadRegistros() {
        return cantidadRegistros;
    }
}