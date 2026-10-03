package src.pharmacontrol;

import src.pharmacontrol.model.Cliente;
import src.pharmacontrol.model.Empleado;
import src.pharmacontrol.model.Persona;
import src.pharmacontrol.model.Producto;

public class Main {
    public static void main(String[] args) {

        Producto producto = new Producto("Paracetamol", 5.50, 20);
        Cliente cliente = new Cliente("Jesus", "74947673", true);
        Empleado empleado = new Empleado("María", "65214837", "Farmaceutica");

        System.out.println("=========== PRODUCTO ===========");
        producto.mostrarInformacion();

        System.out.println("=========== PERSONAS ===========");
        Persona[] personas = {
                cliente, empleado
        };

        for (Persona persona : personas) {
            persona.mostrarInformacion();
            System.out.println();
        }
    }
}
