package src.pharmacontrol.ui;

import java.util.Scanner;
import java.time.LocalDate;

import src.pharmacontrol.model.Lote;
import src.pharmacontrol.service.LoteService;

public class LoteMenu {

    private Scanner scanner;
    private LoteService loteService;

    public LoteMenu(Scanner scanner, LoteService loteService) {
        this.scanner = scanner;
        this.loteService = loteService;
    }

    // Menu disponible para el administrador.
    public void mostrar() {

        int opcion;

        do {
            System.out.println("\n===== LOTES Y VENCIMIENTOS =====");
            System.out.println("1. Registrar lote");
            System.out.println("2. Consultar todos los lotes");
            System.out.println("3. Consultar vencimientos");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opcion: ");

            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    registrarLote();
                    break;

                case 2:
                    consultarLotes();
                    break;

                case 3:
                    consultarVencimientos();
                    break;

                case 0:
                    System.out.println("Volviendo al menu...");
                    break;

                default:
                    System.out.println("Opcion inválida.");
            }

        } while (opcion != 0);
    }

    // Registra un lote de un producto existente.
    private void registrarLote() {

        System.out.println("\n===== REGISTRAR LOTE =====");

        System.out.print("Codigo del producto: ");
        String codigoProducto = scanner.nextLine();

        System.out.print("Cantidad del lote: ");
        int cantidad = Integer.parseInt(scanner.nextLine());

        System.out.print("Fecha de vencimiento (AAAA-MM-DD): ");
        LocalDate fecha = LocalDate.parse(scanner.nextLine());

        loteService.registrarLote(codigoProducto, cantidad, fecha);
    }

    // Muestra todos los lotes.
    private void consultarLotes() {

        System.out.println("\n===== LISTADO DE LOTES =====");

        int cantidad = loteService.getCantidadLotes();

        if (cantidad == 0) {
            System.out.println("No hay lotes registrados.");
            return;
        }

        Lote[] lotes = loteService.obtenerLotes();

        for (int i = 0; i < cantidad; i++) {
            System.out.println("\nLote N° " + (i + 1));
            lotes[i].mostrarInformacion();
            System.out.println("-------------------------");
        }
    }

    // Disponible para administrador y empleado.
    public void consultarVencimientos() {

        System.out.println("\n===== CONSULTAR VENCIMIENTOS =====");

        Lote[] lotes = loteService.obtenerLotes();
        int cantidad = loteService.getCantidadLotes();
        boolean encontrados = false;

        for (int i = 0; i < cantidad; i++) {

            String estado = lotes[i].getEstado();

            if (estado.equals("Vencido")
                    || estado.equals("Proximo a vencer")) {

                lotes[i].mostrarInformacion();
                System.out.println("-------------------------");

                encontrados = true;
            }
        }

        if (!encontrados) {
            System.out.println(
                    "No hay lotes vencidos ni proximos a vencer.");
        }
    }
}