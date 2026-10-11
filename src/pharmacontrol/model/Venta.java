package src.pharmacontrol.model;

public class Venta {

    private String codigoVenta;
    private Producto producto;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;
    private double igv;
    private double descuento;
    private double total;

    public Venta(String codigoVenta, Producto producto, int cantidad, double descuento) {
        this.codigoVenta = codigoVenta;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = producto.getPrecio();
        this.descuento = descuento;

        // Calculamos el importe con IGV incluido.
        double importe = precioUnitario * cantidad;

        // Aplicamos el descuento.
        this.total = importe - descuento;

        // Separamos el IGV incluido en el total.
        this.subtotal = total / 1.18;
        this.igv = total - subtotal;
    }

    public String getCodigoVenta() {
        return codigoVenta;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getIgv() {
        return igv;
    }

    public double getDescuento() {
        return descuento;
    }

    public double getTotal() {
        return total;
    }
}