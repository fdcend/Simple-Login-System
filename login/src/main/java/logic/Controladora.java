package logic;

import java.util.List;
import persistence.ControladoraPersistencia;

public class Controladora {

    ControladoraPersistencia controlPersis;

    public Controladora() {
        controlPersis = new ControladoraPersistencia();
    }

    public Usuario validarUsuario(String usuario, String contrasena) {

        Usuario usr = null;
        List<Usuario> listaUsuario = controlPersis.traerUsuarios();

        for (Usuario usu : listaUsuario) {
            if (usu.getNombreUsuario().equals(usuario)) {
                if (usu.getContrasena().equals(contrasena)) {

                    //mensaje = "Usuario y Contrasena correcta, bienvenido/a!";
                    usr = usu;
                    return usr;
                } else {
                    //mensaje = "Contrasena incorrecta";
                    usr = null;
                    return usr;
                }
            } else {
                //mensaje = "Usuario no encontrado";
                usr = null;
                //return usr;
            }
        }
        return usr;
    }

    public List<Usuario> traerUsuarios() {

        return controlPersis.traerUsuarios();

    }

    public List<Rol> traerRoles() {

        return controlPersis.traerRoles();

    }

    public void crearUsuario(String usuario, String contra, String rolRecibido) {

        Usuario usu = new Usuario();
        usu.setNombreUsuario(usuario);
        usu.setContrasena(contra);

        Rol rolEncontrado = new Rol();
        rolEncontrado = this.traerRol(rolRecibido);

        if (rolEncontrado != null) {
            usu.setUnRol(rolEncontrado);
        }

        int id = this.obtenerMaximoId();
        usu.setId(id + 1);

        controlPersis.crearUsuario(usu);

    }

    private Rol traerRol(String rolRecibido) {
        List<Rol> listaRoles = controlPersis.traerRoles();

        for (Rol rol : listaRoles) {

            if (rol.getNombreRol().equals(rolRecibido)) {
                return rol;
            }
        }
        return null;
    }

    public int obtenerMaximoId() {
        List<Usuario> listaUsuarios = this.traerUsuarios();

        return listaUsuarios.stream()
                .mapToInt(Usuario::getId)
                .max()
                .orElse(0);
    }

    public void borrarUsuario(int id_usuario) {
        controlPersis.borrarUsuario(id_usuario);

    }

    public Usuario traerUsuario(int id_usuario) {

        return controlPersis.traerUsuario(id_usuario);

    }

    public void editarUsuario(Usuario usu, String usuario, String contra, String rolRecibido) {
        usu.setNombreUsuario(usuario);
        usu.setContrasena(contra);

        Rol rolEncontrado = new Rol();
        rolEncontrado = this.traerRol(rolRecibido);
        if (rolEncontrado != null) {
            usu.setUnRol(rolEncontrado);
        }

        controlPersis.editarUsuario(usu);
        
    }

}
