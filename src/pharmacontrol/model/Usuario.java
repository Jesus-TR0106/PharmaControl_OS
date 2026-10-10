package src.pharmacontrol.model;

public class Usuario {

    private String nombreUsuario;
    private String contrasena;
    private String rol;
    private boolean activo;

    public Usuario(String nombreUsuario, String contrasena, String rol) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.rol = rol;
        this.activo = true;
    }

    public boolean verificarCredenciales(String usuario, String clave) {
        return activo
                && nombreUsuario.equals(usuario)
                && contrasena.equals(clave);
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getRol() {
        return rol;
    }

    public boolean isActivo() {
        return activo;
    }

    public void desactivar() {
        activo = false;
    }
}