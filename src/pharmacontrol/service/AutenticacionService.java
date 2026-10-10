package src.pharmacontrol.service;

import src.pharmacontrol.model.Usuario;

public class AutenticacionService {

    // Arreglo y creacion de usuarios
    private Usuario[] usuarios;

    public AutenticacionService() {

        usuarios = new Usuario[2];

        usuarios[0] = new Usuario("admin", "admin123", "ADMINISTRADOR");

        usuarios[1] = new Usuario("empleado1", "empleado123", "EMPLEADO");

    }

    // Validar usuario y contraseña
    public Usuario iniciarSesion(String nombreUsuario, String contrasena) {

        for (int i = 0; i < usuarios.length; i++) {

            if (usuarios[i].verificarCredenciales(nombreUsuario, contrasena)) {

                return usuarios[i];

            }
        }

        return null;
    }
}