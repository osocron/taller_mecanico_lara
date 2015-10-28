package controlador;

import entidades.AutomovilesEntity;
import entidades.UsuarioEntity;

import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * Created by ValdemarRamos on 27/10/2015.
 */
public class ControladorAutomovil {
    public static List<AutomovilesEntity> getAutomoviles(){
        return ConexionBD.getEm().createQuery("SELECT c FROM AutomovilesEntity c ").getResultList();
    }
    public static void guardarAutomovil(AutomovilesEntity automovil){
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        ConexionBD.getEm().persist(automovil);
        entityTransaction.commit();
    }
    public static AutomovilesEntity crearUsuario(int Matricula, String Marca, String Modelo, String Color, int IDClientes){
        AutomovilesEntity automovil = new AutomovilesEntity();
        automovil.setMatricula(Matricula);
        automovil.setMarca(Marca);
        automovil.setModelo(Modelo);
        automovil.setColor(Color);
        automovil.setIdClientes(IDClientes);
        ConexionBD.getEm().persist(automovil);
        return automovil;
    }
    public static void modificarCliente(int IDClientes){
        AutomovilesEntity auto = getAutomovilIDcliente(IDClientes);
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        auto.setIdClientes(IDClientes);
        entityTransaction.commit();
    }

    public static void eliminarAutomovil(int IDClientes){
        AutomovilesEntity auto = getAutomovilIDcliente(IDClientes);
        if(IDClientes != 0){
            EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
            entityTransaction.begin();
            ConexionBD.getEm().remove(IDClientes);
            entityTransaction.commit();
        }
    }
    public static AutomovilesEntity getAutomovilIDcliente(int IDClientes){
        return ConexionBD.getEm().find(AutomovilesEntity.class, IDClientes);
    }
}