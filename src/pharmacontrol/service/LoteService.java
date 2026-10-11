package src.pharmacontrol.service;

import java.time.LocalDate;

import src.pharmacontrol.model.Lote;
import src.pharmacontrol.model.Producto;

public class LoteService {

    private Lote[] lotes;
    private int cantidadLotes;
    private InventarioService inventarioService;

    public LoteService(InventarioService inventarioService) {

        this.inventarioService = inventarioService;
        this.lotes = new Lote[100];
        this.cantidadLotes = 0;
    }

    public boolean registrarLote(String codigoProducto,
            int cantidad,
            LocalDate fechaVencimiento) {

        // Comprobamos que el producto exista.
        Producto producto = inventarioService.buscarProducto(codigoProducto);

        if (producto == null) {
            System.out.println("El producto no existe.");
            return false;
        }

        if (cantidad <= 0) {
            System.out.println("La cantidad debe ser mayor que cero.");
            return false;
        }

        if (fechaVencimiento == null) {
            System.out.println("La fecha no es valida.");
            return false;
        }

        if (cantidadLotes >= lotes.length) {
            System.out.println("No se pueden registrar mas lotes.");
            return false;
        }

        // Generamos un codigo automatico para el lote
        String codigoLote = String.format("LOT%03d", cantidadLotes + 1);

        Lote lote = new Lote(
                codigoLote,
                codigoProducto,
                cantidad,
                fechaVencimiento);

        lotes[cantidadLotes] = lote;
        cantidadLotes++;

        System.out.println("Lote registrado correctamente.");
        System.out.println("Codigo generado: " + codigoLote);

        return true;
    }

    public Lote[] obtenerLotes() {
        return lotes;
    }

    public int getCantidadLotes() {
        return cantidadLotes;
    }
}