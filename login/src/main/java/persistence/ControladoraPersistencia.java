package persistence;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.Rol;
import logic.Usuario;
import persistence.exceptions.NonexistentEntityException;

public class ControladoraPersistencia {

    UsuarioJpaController usuJpa = new UsuarioJpaController();
    RolJpaController rolJpa = new RolJpaController();

    public List<Usuario> traerUsuarios() {

        return usuJpa.findUsuarioEntities();

    }

    public List<Rol> traerRoles() {
        return rolJpa.findRolEntities();

    }

    public void crearUsuario(Usuario usu) {

        usuJpa.create(usu);

    }

    public void borrarUsuario(int id_usuario) {

        try {
            usuJpa.destroy(id_usuario);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Usuario traerUsuario(int id_usuario) {
        return usuJpa.findUsuario(id_usuario);

    }

    public void editarUsuario(Usuario usu) {
        try {
            usuJpa.edit(usu);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
