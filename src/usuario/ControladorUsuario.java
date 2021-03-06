package usuario;

import entidades.EmpleadoEntity;
import entidades.UsuarioEntity;
import entityControlers.ConexionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * Created by osocron on 26/10/15.
 */
public class ControladorUsuario {

    private ObservableList<EmpleadoEntity> dataEmpleado = FXCollections.observableArrayList();

    public static List<UsuarioEntity> getUsuarios(){
        return ConexionBD.getEm().createQuery("SELECT c FROM UsuarioEntity c")
                .getResultList();
    }

    public static void guardarUsuario(UsuarioEntity usuario){
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        ConexionBD.getEm().persist(usuario);
        entityTransaction.commit();
    }

    public static UsuarioEntity crearUsuario(String nombre, String contrasena, int idEmpleado){
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setIdEmpleado(idEmpleado);
        usuario.setNombre(nombre);
        usuario.setContrasena(contrasena);
        ConexionBD.getEm().persist(usuario);
        return usuario;
    }
    public boolean verificarDatos(String nombre, String idEmpleado, ObservableList<UsuarioEntity> dataEmpleado){
        boolean isLleno=true;
        if(idEmpleado.length()!=0 && nombre.length()!=0){
            return isLleno;
        }
        final boolean[] isDuplicado={false};
        dataEmpleado.forEach(empleadoEntity -> {
            if (empleadoEntity.getNombre().equals(idEmpleado)){
                isDuplicado[0]=true;
            }
        });
        return isLleno && isDuplicado[0];
    }

    public static void modificarNombre(int id, String nombre){
        UsuarioEntity pelicula = getUsuarioPorCodigo(id);
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        pelicula.setNombre(nombre);
        entityTransaction.commit();
    }

    public static void modificarContrasena(int id, String apellidos){
        UsuarioEntity pelicula = getUsuarioPorCodigo(id);
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        pelicula.setContrasena(apellidos);
        entityTransaction.commit();
    }


    public static void eliminarUsuario(int codigo){
        UsuarioEntity usuario = getUsuarioPorCodigo(codigo);
        if(usuario != null){
            EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
            entityTransaction.begin();
            ConexionBD.getEm().remove(usuario);
            entityTransaction.commit();
        }
    }

    public static UsuarioEntity getUsuarioPorCodigo(int codigo){
        return ConexionBD.getEm().find(UsuarioEntity.class, codigo);
    }
    
}
