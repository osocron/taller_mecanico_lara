package entityControlers;

import entidades.ClienteAutomovilEntity;

import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * Created by ValdemarRamos on 27/10/2015.
 */
public class ControladorClienteAutomovil {
    public static List<ClienteAutomovilEntity> getAutoCliente(){
        return ConexionBD.getEm().createQuery("SELECT au FROM ClienteAutomovilEntity au")
                .getResultList();
    }
    public static void guardarAutoCliente(ClienteAutomovilEntity autoCliente){
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        ConexionBD.getEm().persist(autoCliente);
        entityTransaction.commit();
    }

    public static ClienteAutomovilEntity crearAutoCliente(int idClienteAutomovil, int IDClientes, int Matriculas){
        ClienteAutomovilEntity autoCliente = new ClienteAutomovilEntity();
        autoCliente.setIdClienteAutomovil(idClienteAutomovil);
        autoCliente.setIdClientes(IDClientes);
        autoCliente.setMatriculas(Matriculas);
        ConexionBD.getEm().persist(autoCliente);
        return autoCliente;
    }

    public static void modificarAutoCliente(int idClienteAutomovil, int IDClientes, int Matriculas){
        ClienteAutomovilEntity autoCliente = getClienteAutomovilID(idClienteAutomovil);
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        autoCliente.setIdClienteAutomovil(idClienteAutomovil);
        autoCliente.setIdClientes(IDClientes);
        autoCliente.setMatriculas(Matriculas);
        entityTransaction.commit();
    }
    public static void eliminarAutoCliente(int idClienteAutomovil){
        ClienteAutomovilEntity autoCliente = getClienteAutomovilID(idClienteAutomovil);
        if(autoCliente != null){
            EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
            entityTransaction.begin();
            ConexionBD.getEm().remove(autoCliente);
            entityTransaction.commit();
        }
    }

    public static ClienteAutomovilEntity getClienteAutomovilID(int idClienteAutomovil){
        return ConexionBD.getEm().find(ClienteAutomovilEntity.class, idClienteAutomovil);
    }


}
