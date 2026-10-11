package src.pharmacontrol.ui;

import java.util.Scanner;
import src.pharmacontrol.model.Asistencia;
import src.pharmacontrol.model.RegistroTemperatura;
import src.pharmacontrol.service.AsistenciaService;
import src.pharmacontrol.service.TemperaturaService;

public class MenuAdministrador {

    private Scanner scanner;
    private InventarioMenu inventarioMenu;
    private VentaMenu ventaMenu;
    private AsistenciaService asistenciaService;
    private TemperaturaService temperaturaService;
    private LoteMenu loteMenu;

    public MenuAdministrador(Scanner scanner, InventarioMenu inventarioMenu, VentaMenu ventaMenu,
            AsistenciaService asistenciaService, TemperaturaService temperaturaService, LoteMenu loteMenu) {
        this.scanner = scanner;
        this.inventarioMenu = inventarioMenu;
        this.ventaMenu = ventaMenu;
        this.asistenciaService = asistenciaService;
        this.temperaturaService = temperaturaService;
        this.loteMenu = loteMenu;
    }

    // Muestra el menú del administrador
    public void mostrar() {

        boolean continuar = true;

        while (continuar) {

            System.out.println("\n================================");
            System.out.println("    MENU DE ADMINISTRADOR");
            System.out.println("================================");
            System.out.println("1. Gestionar inventario");
            System.out.println("2. Gestionar ventas y caja");
            System.out.println("3. Consultar asistencia");
            System.out.println("4. Controlar temperaturas");
            System.out.println("5. Gestionar lotes y vencimientos");
            System.out.println("0. Cerrar sesion");
            System.out.print("Seleccione una opcion: ");

            String opcion = scanner.nextLine();

            if (opcion.equals("1")) {
                inventarioMenu.mostrar();

            } else if (opcion.equals("2")) {
                ventaMenu.mostrar();

            } else if (opcion.equals("3")) {
                consultarAsistencias();

            } else if (opcion.equals("4")) {
                consultarTemperaturas();

            } else if (opcion.equals("5")) {
                loteMenu.mostrar();

            } else if (opcion.equals("0")) {
                continuar = false;

            } else {
                System.out.println("Opcion no valida.");
            }
        }

        System.out.println("Sesion de administrador cerrada.");
    }

    // CONSULTAR ASISTENCIA - OPCION 3

    private void consultarAsistencias() {
        System.out.println("\n===== CONTROL DE ASISTENCIAS =====");

        int cantidad = asistenciaService.getCantidadAsistencias();
        if (cantidad == 0) {
            System.out.println("No hay asistencias registradas");
            return;
        }

        Asistencia[] asistencias = asistenciaService.obtenerAsistencias();

        for (int i = 0; i < cantidad; i++) {
            System.out.println("\nRegistro N° " + (i + 1));
            asistencias[i].mostrarInformacion();

            System.out.println("------------------------------");
        }
    }

    // CONTROL DE TEMPERATURAS

    private void consultarTemperaturas() {

        System.out.println("\n===== CONTROL DE TEMPERATURAS =====");

        int cantidad = temperaturaService.getCantidadRegistros();

        // Verificamos si existen registros
        if (cantidad == 0) {
            System.out.println("No hay temperaturas registradas.");
            return;
        }

        // Obtenemos los registros
        RegistroTemperatura[] registros = temperaturaService.obtenerRegistros();

        // Mostramos todos los registros
        for (int i = 0; i < cantidad; i++) {

            System.out.println("\nRegistro N° " + (i + 1));

            registros[i].mostrarInformacion();

            System.out.println("-------------------------");
        }
    }
}