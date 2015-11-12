package entityControlers;

import entidades.AutomovilesEntity;

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
    public static AutomovilesEntity crearAutomovil(String Matricula, String Marca, String Modelo, String Color, String IDClientes){
        AutomovilesEntity automovil = new AutomovilesEntity();
        automovil.setMatricula(Matricula);
        automovil.setMarca(Marca);
        automovil.setModelo(Modelo);
        automovil.setColor(Color);
        automovil.setIdClientes(IDClientes);
        ConexionBD.getEm().persist(automovil);
        return automovil;
    }
    public static void modificarMarca(String Matricula,String Marca){
        AutomovilesEntity auto = getAutomovilByMatricula(Matricula);
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        auto.setMarca(Marca);
        entityTransaction.commit();
    }
    public static void modificarModelo(String Matricula, String Modelo){
        AutomovilesEntity auto = getAutomovilByMatricula(Matricula);
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        auto.setModelo(Modelo);
        entityTransaction.commit();
    }
    public static void modificarColor(String Matricula, String Color){
        AutomovilesEntity auto = getAutomovilByMatricula(Matricula);
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        auto.setColor(Color);
        entityTransaction.commit();
    }

    public static void eliminarAutomovil(String IDClientes){
        AutomovilesEntity auto = getAutomovilByMatricula(IDClientes);
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        ConexionBD.getEm().remove(IDClientes);
        entityTransaction.commit();
    }
    public static AutomovilesEntity getAutomovilByMatricula(String matricula){
        return ConexionBD.getEm().find(AutomovilesEntity.class, matricula);
    }
}
