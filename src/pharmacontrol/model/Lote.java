package src.pharmacontrol.model;

import java.time.LocalDate;

public class Lote {
    private String codigo;
    private String codigoProducto;
    private int cantidad;
    private LocalDate fechaVencimiento;

    public Lote(String codigo, String codigoProducto,
            int cantidad, LocalDate fechaVencimiento) {

        this.codigo = codigo;
        this.codigoProducto = codigoProducto;
        this.cantidad = cantidad;
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    // Determina el estado del lote segun su vencimiento.
    public String getEstado() {

        LocalDate hoy = LocalDate.now();

        if (fechaVencimiento.isBefore(hoy)) {
            return "Vencido";
        }

        if (!fechaVencimiento.isAfter(hoy.plusDays(30))) {
            return "Proximo a vencer";
        }

        return "Vigente";
    }

    public void mostrarInformacion() {

        System.out.println("Codigo de lote: " + codigo);
        System.out.println("Codigo de producto: " + codigoProducto);
        System.out.println("Cantidad: " + cantidad);
        System.out.println("Fecha de vencimiento: " + fechaVencimiento);
        System.out.println("Estado: " + getEstado());
    }
}