package src.pharmacontrol;

import java.util.Scanner;

import src.pharmacontrol.model.Usuario;
import src.pharmacontrol.service.AutenticacionService;
import src.pharmacontrol.service.InventarioService;
import src.pharmacontrol.ui.InventarioMenu;
import src.pharmacontrol.ui.LoginMenu;
import src.pharmacontrol.ui.MenuAdministrador;
import src.pharmacontrol.ui.MenuEmpleado;
import src.pharmacontrol.service.VentaService;
import src.pharmacontrol.ui.VentaMenu;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        AutenticacionService autenticacion = new AutenticacionService();
        LoginMenu login = new LoginMenu(scanner, autenticacion);
        InventarioService inventarioService = new InventarioService();
        VentaService ventaService = new VentaService(inventarioService);
        InventarioMenu inventarioMenu = new InventarioMenu(scanner, inventarioService);
        VentaMenu ventaMenu = new VentaMenu(scanner, ventaService, inventarioService);
        MenuAdministrador menuAdmin = new MenuAdministrador(scanner, inventarioMenu, ventaMenu);
        MenuEmpleado menuEmpleado = new MenuEmpleado(scanner, inventarioService, ventaMenu);

        boolean sistemaActivo = true;

        while (sistemaActivo) {

            // Solicitamos las credenciales de usuario.
            Usuario usuario = login.mostrarLogin();

            // Verificamos si el inicio de sesión fue correcto.
            if (usuario != null) {

                // Mostramos el menú según el rol de la cuenta.
                if (usuario.getRol().equals("ADMINISTRADOR")) {

                    menuAdmin.mostrar();

                } else if (usuario.getRol().equals("EMPLEADO")) {

                    menuEmpleado.mostrar();

                } else {

                    System.out.println("Rol no reconocido.");
                }

            } else {

                System.out.println("No se pudo iniciar sesion.");
            }

            // Preguntamos si se desea volver a iniciar sesión.
            System.out.println("\n¿Desea volver a iniciar sesión?");
            System.out.println("1. Si");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");

            String opcion = scanner.nextLine();

            if (!opcion.equals("1")) {
                sistemaActivo = false;
            }
        }

        System.out.println("Gracias por utilizar PharmaControl OS.");

        scanner.close();
    }
}