package src.pharmacontrol.model;

public class Producto {

    private String nombre;
    private double precio;
    private int stock;
    private String codigo;
    private String categoria;

    public Producto(String nombre, double precio, int stock, String codigo, String categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.codigo = codigo;
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double calcularValorStock() {
        return precio * stock;
    }

    public void mostrarInformacion() {
        System.out.println("---------------------");
        System.out.println("Codigo: " + codigo);
        System.out.println("Producto: " + nombre);
        System.out.println("Categoria:" + categoria);
        System.out.println("Precio: S/. " + precio);
        System.out.println("Stock: " + stock);
    }
}
