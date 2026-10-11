package src.pharmacontrol.service;

import src.pharmacontrol.model.Producto;
import src.pharmacontrol.model.Venta;

public class VentaService {

    private Venta[] ventas;
    private int cantidadVentas;
    private InventarioService inventarioService;

    public VentaService(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
        this.ventas = new Venta[100];
        this.cantidadVentas = 0;
    }

    // Registra una venta si el producto y la cantidad son validos.
    public boolean registrarVenta(String codigoProducto, int cantidad, double descuento) {

        // Buscamos el producto en el inventario
        Producto producto = inventarioService.buscarProducto(codigoProducto);

        if (producto == null) {
            System.out.println("El producto no existe.");
            return false;
        }

        // La cantidad debe ser mayor que cero.
        if (cantidad <= 0) {
            System.out.println("La cantidad debe ser mayor que cero.");
            return false;
        }

        // Verificamos que haya suficientes unidades.
        if (producto.getStock() < cantidad) {
            System.out.println("Stock insuficiente.");
            return false;
        }

        // Calculamos el importe antes del descuento.
        double importe = producto.getPrecio() * cantidad;

        // El descuento no puede ser negativo ni mayor al importe
        if (descuento < 0 || descuento > importe || !Double.isFinite(descuento)) {
            System.out.println("Descuento no valido.");
            return false;
        }

        // Verificamos que el importe sea valido.
        if (!Double.isFinite(importe) || importe < 0) {
            System.out.println("Importe no valido.");
            return false;
        }

        // Comprobamos que haya espacio para guardar la venta.
        if (cantidadVentas >= ventas.length) {
            System.out.println("No se pueden registrar mas ventas.");
            return false;
        }

        // Generamos un codigo sencillo para la venta.
        String codigoVenta = "V" + (cantidadVentas + 1);

        Venta venta = new Venta(codigoVenta, producto, cantidad, descuento);

        // Descontamos las unidades vendidas del stock
        int nuevoStock = producto.getStock() - cantidad;
        producto.setStock(nuevoStock);

        // Guardamos la venta en el arreglo.
        ventas[cantidadVentas] = venta;
        cantidadVentas++;

        System.out.println("Venta registrada correctamente.");
        System.out.println("Codigo de venta: " + codigoVenta);

        return true;
    }

    // Devuelve las ventas almacenadas.
    public Venta[] obtenerVentas() {
        return ventas;
    }

    // Devuelve cuantas ventas se han registrado.
    public int getCantidadVentas() {
        return cantidadVentas;
    }

    // Suma el dinero generado por las ventas.
    public double calcularIngresosTotales() {

        double ingresos = 0;

        for (int i = 0; i < cantidadVentas; i++) {
            ingresos += ventas[i].getTotal();
        }

        return ingresos;
    }
}