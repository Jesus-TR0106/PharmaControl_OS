package src.pharmacontrol.model;

public class Empleado extends Persona {
    private String cargo;

    public Empleado(String nombre, String dni, String cargo) {
        super(nombre, dni);
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Empleado: " + getNombre());
        System.out.println("DNI: " + getDni());
        System.out.println("Cargo: " + getCargo());
    }
}
