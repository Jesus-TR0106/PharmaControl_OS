package src.pharmacontrol.ui;

import java.util.Scanner;

import src.pharmacontrol.model.Producto;
import src.pharmacontrol.model.Venta;
import src.pharmacontrol.service.InventarioService;
import src.pharmacontrol.service.VentaService;

public class VentaMenu {
    private Scanner scanner;
    private VentaService ventaService;
    private InventarioService inventarioService;

    public VentaMenu(Scanner scanner, VentaService ventaService, InventarioService inventarioService) {
        this.scanner = scanner;
        this.ventaService = ventaService;
        this.inventarioService = inventarioService;
    }

    // Menu completo para el ADMINISTRADOR
    public void mostrar() {

        boolean menuActivo = true;

        while (menuActivo) {

            System.out.println("\n========= VENTAS Y CAJA =========");
            System.out.println("1. Registrar venta");
            System.out.println("2. Consultar ventas realizadas");
            System.out.println("3. Consultar ingresos totales");
            System.out.println("0. Volver al menu principal");
            System.out.print("Seleccione una opcion: ");

            String opcion = scanner.nextLine();

            if (opcion.equals("1")) {

                registrarVenta();

            } else if (opcion.equals("2")) {

                consultarVentas();

            } else if (opcion.equals("3")) {

                consultarIngresos();

            } else if (opcion.equals("0")) {

                menuActivo = false;

            } else {

                System.out.println("Opcion no valida.");
            }
        }
    }

    // REGISTRO DE VENTAS Y ACTUALIZACION DE STOCK - OPCION 1

    public void registrarVenta() {

        System.out.println("\n========= REGISTRAR VENTA =========");

        System.out.print("Codigo del producto: ");
        String codigo = scanner.nextLine();

        Producto producto = inventarioService.buscarProducto(codigo);

        if (producto == null) {
            System.out.println("El producto no existe.");
            return;
        }

        // Mostramos el producto
        producto.mostrarInformacion();

        // Solicita la cantidad
        System.out.print("Cantidad a vender: ");
        int cantidad = Integer.parseInt(scanner.nextLine());

        if (cantidad <= 0 || cantidad > producto.getStock()) {
            System.out.println("Cantidad no valida o stock insuficiente.");
            return;
        }

        // Solicita el descuento
        System.out.print("Descuento: ");
        double porcentaje = Double.parseDouble(scanner.nextLine());

        // Validamos el porcentaje de descuento
        if (!Double.isFinite(porcentaje) || porcentaje < 0 || porcentaje > 100) {
            System.out.println("Porcentaje de descuento no valido.");
            return;
        }

        double importe = producto.getPrecio() * cantidad;

        if (!Double.isFinite(importe) || importe < 0) {
            System.out.println("Importe no valido.");
            return;
        }

        double descuento = importe * porcentaje / 100;
        double total = importe - descuento;
        double subtotal = total / 1.18;
        double igv = total - subtotal;

        // MOSTRAMOS EL RESUMEN
        System.out.println("\n========= RESUMEN DE VENTA =========");
        System.out.println("Producto: " + producto.getNombre());
        System.out.println("Cantidad: " + cantidad);

        System.out.printf("Importe: S/ %.2f%n", importe);
        System.out.printf("Descuento (%.0f%%): S/ %.2f%n", porcentaje, descuento);

        System.out.printf("Subtotal sin IGV: S/ %.2f%n", subtotal);
        System.out.printf("IGV (18%%): S/ %.2f%n", igv);
        System.out.printf("Total a pagar: S/ %.2f%n", total);

        // Confirmamos la venta
        System.out.print("\nConfirmar venta? (S/N): ");
        String confirmacion = scanner.nextLine();

        if (!confirmacion.equalsIgnoreCase("S")) {
            System.out.println("Venta cancelada.");
            return;
        }

        // VentaService registra la venta y descuenta el stock.
        boolean registrada = ventaService.registrarVenta(
                codigo, cantidad, descuento);

        if (registrada) {
            System.out.println("Stock restante: " + producto.getStock());
        }
    }

    // Muestra el historial de ventas - ADMINISTRADOR
    public void consultarVentas() {

        int cantidad = ventaService.getCantidadVentas();

        if (cantidad == 0) {
            System.out.println("No hay ventas registradas.");
            return;
        }

        Venta[] ventas = ventaService.obtenerVentas();

        System.out.println("\n========= VENTAS REGISTRADAS =========");

        for (int i = 0; i < cantidad; i++) {

            Venta venta = ventas[i];

            System.out.println("\nCodigo: " + venta.getCodigoVenta());
            System.out.println("Producto: " + venta.getProducto().getNombre());
            System.out.println("Cantidad: " + venta.getCantidad());

            System.out.printf("Precio unitario: S/ %.2f%n", venta.getPrecioUnitario());

            System.out.printf("Subtotal sin IGV: S/ %.2f%n", venta.getSubtotal());

            System.out.printf("IGV: S/ %.2f%n", venta.getIgv());

            System.out.printf("Descuento: S/ %.2f%n", venta.getDescuento());

            System.out.printf("Total: S/ %.2f%n", venta.getTotal());
        }
    }

    // CALCULAR INGRESOS TOTALES - OPCION 3
    public void consultarIngresos() {

        double ingresos = ventaService.calcularIngresosTotales();

        System.out.println("\n========= INGRESOS TOTALES =========");
        System.out.println("Ventas registradas: " + ventaService.getCantidadVentas());

        System.out.printf("Ingresos totales: S/ %.2f%n", ingresos);
    }
}