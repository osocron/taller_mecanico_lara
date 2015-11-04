package entityControlers;

import entities.ClienteEntity;

import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * Created by osocron on 30/10/15.
 */
public class ControladorCliente {

    public static List<ClienteEntity> getCliente(){
        return ConexionBD.getEm().createQuery("SELECT c FROM ClienteEntity c")
                .getResultList();
    }

    public static void guardarCliente(ClienteEntity cliente){
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        ConexionBD.getEm().persist(cliente);
        entityTransaction.commit();
    }

    public static ClienteEntity crearCliente(String idCliente, String nombre, String direccion, String telefono){
        ClienteEntity cliente = new ClienteEntity();
        cliente.setIdCliente(idCliente);
        cliente.setNombre(nombre);
        cliente.setDireccion(direccion);
        cliente.setTelefono(telefono);
        ConexionBD.getEm().persist(cliente);
        return cliente;
    }

    public static void modificarCliente(String idCliente, String nombre, String direccion, String telefono){
        ClienteEntity cliente = getClienteID(idCliente);
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        cliente.setIdCliente(idCliente);
        cliente.setNombre(nombre);
        cliente.setDireccion(direccion);
        cliente.setTelefono(telefono);
        entityTransaction.commit();
    }
    public static void eliminarCliente(String idCliente){
        ClienteEntity cliente = getClienteID(idCliente);
        if(cliente != null){
            EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
            entityTransaction.begin();
            ConexionBD.getEm().remove(cliente);
            entityTransaction.commit();
        }
    }

    public static ClienteEntity getClienteID(String idCliente){
        return ConexionBD.getEm().find(ClienteEntity.class, idCliente);
    }

}
