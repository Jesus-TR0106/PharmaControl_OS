package src.pharmacontrol.ui;

import java.util.Scanner;

import src.pharmacontrol.model.Usuario;
import src.pharmacontrol.service.AutenticacionService;

public class LoginMenu {

    private Scanner scanner;
    private AutenticacionService autenticacionService;

    public LoginMenu(Scanner scanner, AutenticacionService autenticacionService) {
        this.scanner = scanner;
        this.autenticacionService = autenticacionService;
    }

    // Ingreso y verificacion de credenciales
    public Usuario mostrarLogin() {

        int intentos = 3;

        System.out.println("==================================");
        System.out.println("       PHARMACONTROL OS");
        System.out.println("==================================");
        System.out.println("INICIAR SESION");

        // Intentos de login hasta 3 veces
        while (intentos > 0) {

            System.out.print("Usuario: ");
            String nombreUsuario = scanner.nextLine();

            System.out.print("Contrasena: ");
            String contrasena = scanner.nextLine();

            // Verificar las credenciales ingresadas
            Usuario usuario = autenticacionService.iniciarSesion(nombreUsuario, contrasena);

            // Credenciales validadas
            if (usuario != null) {
                System.out.println("\n Inicio de sesion correcto.");
                System.out.println("Bienvenido, " + usuario.getNombreUsuario());

                return usuario;
            }

            // Si falla, descuenta un intento
            intentos--;

            System.out.println("Usuario o contrasena incorrectos.");

            if (intentos > 0) {
                System.out.println("Intentos restantes: " + intentos + "\n");
            }
        }

        // Si se agotan los intentos, no se permite el acceso.
        System.out.println("\n Acceso denegado.");
        return null;
    }
}