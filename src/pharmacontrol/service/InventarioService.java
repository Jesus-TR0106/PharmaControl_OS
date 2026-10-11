package src.pharmacontrol.service;

import src.pharmacontrol.model.Producto;

public class InventarioService {

    private Producto[] productos;
    private int cantidadProductos;

    public InventarioService() {

        productos = new Producto[100]; // registro de hasta 100 productos
        cantidadProductos = 0;
    }

    //
    public boolean registrarProducto(Producto producto) {
        if (producto == null) {
            return false;
        }

        if (cantidadProductos >= productos.length) {
            return false;
        }

        String codigo = String.format("MED%03d", cantidadProductos + 1);

        producto.setCodigo(codigo);

        productos[cantidadProductos] = producto;
        cantidadProductos++;

        return true;
    }

    // Busca un producto utilizando su código.
    public Producto buscarProducto(String codigo) {

        // Solo busca en los productos registrados.
        for (int i = 0; i < cantidadProductos; i++) {

            if (productos[i].getCodigo() != null && productos[i].getCodigo().equals(codigo)) {

                return productos[i];
            }
        }

        return null;
    }

    // Permite consultar los productos registrados.
    public Producto[] obtenerProductos() {
        return productos;
    }

    // Devuelve la cantidad de productos registrados.
    public int getCantidadProductos() {
        return cantidadProductos;
    }

    // METODO PARA REPONER STOCK - OPCION 2

    public boolean reponerStock(String codigo, int cantidad) {

        if (cantidad <= 0) {
            return false;
        }

        // Buscamos el producto mediante su codigo.
        Producto producto = buscarProducto(codigo);

        // Si el producto no existe, cancelamos la reposicion
        if (producto == null) {
            return false;
        }

        // Calculamos la nueva cantidad de productos.
        int nuevoStock = producto.getStock() + cantidad;

        producto.setStock(nuevoStock);

        return true;
    }
}