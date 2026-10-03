package src.pharmacontrol.model;

public class Cliente extends Persona {

    private boolean tieneTarjeta;

    public Cliente(String nombre, String dni, boolean tieneTarjeta) {
        super(nombre, dni);
        this.tieneTarjeta = tieneTarjeta;
    }

    public boolean isTieneTarjeta() {
        return tieneTarjeta;
    }

    public void setTieneTarjeta(boolean tieneTarjeta) {
        this.tieneTarjeta = tieneTarjeta;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Cliente: " + getNombre());
        System.out.println("DNI: " + getDni());
        System.out.println("Tarjeta: " + (tieneTarjeta ? "Sí" : "No"));
    }
}
