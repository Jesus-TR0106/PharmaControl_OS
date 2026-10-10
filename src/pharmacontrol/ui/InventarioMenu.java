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
                reponerStock();

            } else if (opcion.equals("3")) {
                consultarInventario();

            } else if (opcion.equals("4")) {
                buscarProducto();

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

    // REPONER STOCK - OPCION 2

    public void reponerStock() {

        System.out.println("\n===== REPONER STOCK =====");

        System.out.print("Codigo del producto: ");
        String codigo = scanner.nextLine();

        // Verificamos que el producto exista.
        Producto producto = inventarioService.buscarProducto(codigo);

        if (producto == null) {
            System.out.println("Producto no encontrado.");
            return;
        }

        // Muestra la informacion del producto.
        System.out.println("Producto: " + producto.getNombre());
        System.out.println("Stock actual: " + producto.getStock());

        System.out.print("Cantidad a agregar: ");
        int cantidad = Integer.parseInt(scanner.nextLine());

        // Actualiza el stock
        boolean actualizado = inventarioService.reponerStock(codigo, cantidad);

        if (actualizado) {
            System.out.println("Stock actualizado correctamente.");
            System.out.println("Nuevo stock: " + producto.getStock());
        } else {
            System.out.println("No se pudo actualizar el stock.");
            System.out.println("La cantidad debe ser mayor que cero.");
        }
    }

    // CONSULTAR INVENTARIO - OPCION 3

    public void consultarInventario() {

        System.out.println("\n===== INVENTARIO GENERAL =====");

        int cantidad = inventarioService.getCantidadProductos();

        if (cantidad == 0) {
            System.out.println("No hay productos registrados.");
            return;
        }

        // Arreglo de productos
        Producto[] productos = inventarioService.obtenerProductos();

        for (int i = 0; i < cantidad; i++) {
            System.out.println("\nProducto N° " + (i + 1));

            productos[i].mostrarInformacion();
        }
    }

    // BUSCAR PRODUCTO - OPCION 4

    public void buscarProducto() {

        System.out.println("\n===== BUSCAR PRODUCTO =====");

        // Solicitamos el codigo del producto
        System.out.print("Ingrese el codigo: ");
        String codigo = scanner.nextLine();

        // Buscamos el producto en el inventario.
        Producto producto = inventarioService.buscarProducto(codigo);

        // Verificamos si encontramos el producto.
        if (producto != null) {

            System.out.println("\nProducto encontrado:");
            producto.mostrarInformacion();

        } else {

            System.out.println("No se encontro un producto con ese codigo.");
        }
    }

}