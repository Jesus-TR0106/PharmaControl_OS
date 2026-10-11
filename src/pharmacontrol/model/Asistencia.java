package src.pharmacontrol.model;

public class Asistencia {

    private String nombreEmpleado;
    private String fecha;
    private String horaEntrada;
    private String estado;

    public Asistencia(String nombreEmpleado, String fecha, String horaEntrada, String estado) {
        this.nombreEmpleado = nombreEmpleado;
        this.fecha = fecha;
        this.horaEntrada = horaEntrada;
        this.estado = estado;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public String getEstado() {
        return estado;
    }

    // Muestra la informacion de una asistencia
    public void mostrarInformacion() {

        System.out.println("Empleado: " + nombreEmpleado);
        System.out.println("Fecha: " + fecha);
        System.out.println("Hora de entrada: " + horaEntrada);
        System.out.println("Estado: " + estado);
    }
}