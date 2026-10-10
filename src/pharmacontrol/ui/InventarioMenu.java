package src.pharmacontrol.ui;

import java.util.Scanner;
import src.pharmacontrol.service.InventarioService;
import src.pharmacontrol.model.Producto;

public class InventarioMenu {

    private Scanner scanner;
    private InventarioService inventarioService;

    public InventarioMenu(Scanner scanner, InventarioService inventarioService) {
        this.scanner = scanner;
        this.inventarioService = inventarioService;
    }

    public void mostrar() {

        boolean menuActivo = true;

        while (menuActivo) {

            System.out.println("\n================================");
            System.out.println("       GESTION DE INVENTARIO");
            System.out.println("================================");
            System.out.println("1. Registrar nuevo producto");
            System.out.println("2. Reponer stock");
            System.out.println("3. Consultar inventario");
            System.out.println("4. Buscar producto");
            System.out.println("0. Volver al menu principal");
            System.out.print("Seleccione una opcion: ");

            String opcion = scanner.nextLine();

            if (opcion.equals("1")) {
                registrarProducto();

            } else if (opcion.equals("2")) {
                System.out.println("Reposicion de stock pendiente.");

            } else if (opcion.equals("3")) {
                System.out.println("Consulta de inventario pendiente.");

            } else if (opcion.equals("4")) {
                System.out.println("Busqueda de producto pendiente.");

            } else if (opcion.equals("0")) {
                menuActivo = false;

            } else {
                System.out.println("Opcion no valida.");
            }
        }
    }

    // REGISTRAR PRODUCTO NUEVO - OPCION 1

    public void registrarProducto() {

        System.out.println("\n===== REGISTRAR PRODUCTO =====");

        System.out.println("Codigo: ");
        String codigo = scanner.nextLine();

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();

        System.out.print("Precio: S/. ");
        double precio = Double.parseDouble(scanner.nextLine());

        System.out.print("Stock inicial: ");
        int stock = Integer.parseInt(scanner.nextLine());

        if (precio < 0 || stock < 0) {
            System.out.println("El precio y el stock no pueden ser negativos.");
            return;
        }

        Producto producto = new Producto(nombre, precio, stock, codigo, categoria);

        boolean registrado = inventarioService.registrarProducto(producto);

        if (registrado) {
            System.out.println("Producto registrado correctamente.");
        } else {
            System.out.println("No se pudo registrar el producto");
            System.out.println("Verifique si el codigo ya existe o si el inventario está lleno.");
        }
    }
}