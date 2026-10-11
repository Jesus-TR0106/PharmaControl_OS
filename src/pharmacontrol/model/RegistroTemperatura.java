package src.pharmacontrol.model;

public class RegistroTemperatura {

    private String fecha;
    private String hora;
    private double temperatura;
    private String estado;

    // Constructor
    public RegistroTemperatura(String fecha, String hora, double temperatura, String estado) {
        this.fecha = fecha;
        this.hora = hora;
        this.temperatura = temperatura;
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public String getEstado() {
        return estado;
    }

    // Mostrar los datos del registro
    public void mostrarInformacion() {

        System.out.println("Fecha: " + fecha);
        System.out.println("Hora: " + hora);
        System.out.println("Temperatura: " + temperatura + " °C");
        System.out.println("Estado: " + estado);
    }
}