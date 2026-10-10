package src.pharmacontrol.service;

import src.pharmacontrol.model.Producto;

public class InventarioService {

    private Producto[] productos;
    private int cantidadProductos;

    // Constructor
    public InventarioService() {

        productos = new Producto[100]; // registro de hasta 100 productos
        cantidadProductos = 0;
    }

    public boolean registrarProducto(Producto producto) {

        // Validar que el producto tenga datos
        if (producto == null) {
            return false;
        }

        // Verifica que el código no esté repetido.
        if (buscarProducto(producto.getCodigo()) != null) {
            return false;
        }

        // Verifica que exista espacio en el arreglo.
        if (cantidadProductos < productos.length) {

            productos[cantidadProductos] = producto;
            cantidadProductos++;

            return true;
        }

        return false;
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
}