package src.pharmacontrol.ui;

import java.util.Scanner;

public class MenuEmpleado {

    private Scanner scanner;

    // Recibe el Scanner para leer las opciones del menú.
    public MenuEmpleado(Scanner scanner) {
        this.scanner = scanner;
    }

    // Muestra las opciones de trabajo del empleado.
    public void mostrar() {

        boolean continuar = true;

        while (continuar) {

            System.out.println("\n================================");
            System.out.println("       MENU DE EMPLEADO");
            System.out.println("================================");
            System.out.println("1. Consultar productos");
            System.out.println("2. Registrar una venta");
            System.out.println("3. Atender clientes");
            System.out.println("4. Registrar mi asistencia");
            System.out.println("5. Registrar temperatura");
            System.out.println("6. Consultar alertas de vencimiento");
            System.out.println("0. Cerrar sesion");
            System.out.print("Seleccione una opcion: ");

            String opcion = scanner.nextLine();

            if (opcion.equals("1")) {
                System.out.println("Consulta de productos pendiente.");

            } else if (opcion.equals("2")) {
                System.out.println("Registro de ventas pendiente.");

            } else if (opcion.equals("3")) {
                System.out.println("Atencion al cliente pendiente.");

            } else if (opcion.equals("4")) {
                System.out.println("Registro de asistencia pendiente.");

            } else if (opcion.equals("5")) {
                System.out.println("Registro de temperatura pendiente.");

            } else if (opcion.equals("6")) {
                System.out.println("Consulta de alertas pendiente.");

            } else if (opcion.equals("0")) {
                continuar = false;

            } else {
                System.out.println("Opcion no valida.");
            }
        }

        System.out.println("Sesion de empleado cerrada.");
    }
}