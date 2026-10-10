package src.pharmacontrol.ui;

import java.util.Scanner;

public class MenuAdministrador {

    private Scanner scanner;
    private InventarioMenu inventarioMenu;

    public MenuAdministrador(Scanner scanner, InventarioMenu inventarioMenu) {
        this.scanner = scanner;
        this.inventarioMenu = inventarioMenu;
    }

    // Muestra el menú del administrador
    public void mostrar() {

        boolean continuar = true;

        while (continuar) {

            System.out.println("\n================================");
            System.out.println("    MENU DE ADMINISTRADOR");
            System.out.println("================================");
            System.out.println("1. Gestionar inventario");
            System.out.println("2. Gestionar ventas y caja");
            System.out.println("3. Consultar asistencia");
            System.out.println("4. Controlar temperaturas");
            System.out.println("5. Gestionar lotes y vencimientos");
            System.out.println("0. Cerrar sesion");
            System.out.print("Seleccione una opcion: ");

            String opcion = scanner.nextLine();

            if (opcion.equals("1")) {
                inventarioMenu.mostrar();

            } else if (opcion.equals("2")) {
                System.out.println("Modulo de ventas y caja pendiente.");

            } else if (opcion.equals("3")) {
                System.out.println("Modulo de asistencia pendiente.");

            } else if (opcion.equals("4")) {
                System.out.println("Modulo de temperatura pendiente.");

            } else if (opcion.equals("5")) {
                System.out.println("Modulo de caducidad pendiente.");

            } else if (opcion.equals("0")) {
                continuar = false;

            } else {
                System.out.println("Opcion no valida.");
            }
        }

        System.out.println("Sesion de administrador cerrada.");
    }
}