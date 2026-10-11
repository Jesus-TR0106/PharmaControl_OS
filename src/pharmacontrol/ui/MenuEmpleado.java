package src.pharmacontrol.ui;

import java.util.Scanner;
import src.pharmacontrol.model.Producto;
import src.pharmacontrol.service.InventarioService;

public class MenuEmpleado {

    private Scanner scanner;
    private InventarioService inventarioService;
    private VentaMenu ventaMenu;

    public MenuEmpleado(Scanner scanner, InventarioService inventarioService, VentaMenu ventaMenu) {
        this.scanner = scanner;
        this.inventarioService = inventarioService;
        this.ventaMenu = ventaMenu;
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
            System.out.println("3. Registrar mi asistencia");
            System.out.println("4. Registrar temperatura");
            System.out.println("5. Consultar alertas de vencimiento");
            System.out.println("0. Cerrar sesion");
            System.out.print("Seleccione una opcion: ");

            String opcion = scanner.nextLine();

            if (opcion.equals("1")) {
                consultarInventario();

            } else if (opcion.equals("2")) {
                ventaMenu.registrarVenta();

            } else if (opcion.equals("3")) {
                System.out.println("Registro de asistencia pendiente.");

            } else if (opcion.equals("4")) {
                System.out.println("Registro de temperatura pendiente.");

            } else if (opcion.equals("5")) {
                System.out.println("Consulta de alertas pendiente.");

            } else if (opcion.equals("0")) {
                continuar = false;

            } else {
                System.out.println("Opcion no valida.");
            }
        }

        System.out.println("Sesion de empleado cerrada.");
    }

    // CONSULTAR PRODUCTOS - OPCION 1

    public void consultarInventario() {

        boolean consultaActiva = true;

        while (consultaActiva) {

            System.out.println("\n===== CONSULTAR INVENTARIO =====");
            System.out.println("1. Ver todos los productos");
            System.out.println("2. Buscar producto por codigo");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opcion: ");

            String opcion = scanner.nextLine();

            if (opcion.equals("1")) {

                Producto[] productos = inventarioService.obtenerProductos();
                int cantidad = inventarioService.getCantidadProductos();

                if (cantidad == 0) {
                    System.out.println("No hay productos registrados.");
                } else {

                    // Verifica y muestra los productos
                    for (int i = 0; i < cantidad; i++) {

                        System.out.println("\nProducto N° " + (i + 1));
                        productos[i].mostrarInformacion();
                    }
                }

            } else if (opcion.equals("2")) {

                System.out.print("Ingrese el codigo: ");
                String codigo = scanner.nextLine();

                // Buscamos el producto mediante su codigo.
                Producto producto = inventarioService.buscarProducto(codigo);

                if (producto != null) {

                    System.out.println("\nProducto encontrado:");
                    producto.mostrarInformacion();

                } else {

                    System.out.println("Producto no encontrado.");
                }

            } else if (opcion.equals("0")) {

                consultaActiva = false;

            } else {

                System.out.println("Opcion no valida.");
            }
        }
    }

}