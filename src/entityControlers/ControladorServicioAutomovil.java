package entityControlers;

import entidades.ServicioAutomovilEntity;

import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * Created by ValdemarRamos on 27/10/2015.
 */
public class ControladorServicioAutomovil {
    public static List<ServicioAutomovilEntity> getServicioAuto(){
        return ConexionBD.getEm().createQuery("SELECT sa FROM ServicioAutomovilEntity sa").getResultList();
    }
    public static void guardarServicioAutomovil(ServicioAutomovilEntity servicioAutomovil){
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        ConexionBD.getEm().persist(servicioAutomovil);
        entityTransaction.commit();
    }

    public static ServicioAutomovilEntity crearServicioAutomovil(int idServicioAutomovil, int IDServicios, int Matriculas){
        ServicioAutomovilEntity servicioAutomovil = new ServicioAutomovilEntity();
        servicioAutomovil.setIdServicioAutomovil(idServicioAutomovil);
        servicioAutomovil.setIdServicios(IDServicios);
        servicioAutomovil.setMatriculas(Matriculas);
        ConexionBD.getEm().persist(servicioAutomovil);
        return servicioAutomovil;
    }

    public static void modificarServicioAutomovil(int idServicioAutovil, int IDServicios, int Matriculas){
        ServicioAutomovilEntity servicioAutomovil = getServicioAuto(idServicioAutovil);
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        servicioAutomovil.setIdServicioAutomovil(idServicioAutovil);
        servicioAutomovil.setIdServicios(IDServicios);
        servicioAutomovil.setMatriculas(Matriculas);
        entityTransaction.commit();
    }
    public static void eliminarServicioAutomovil(int idServicioAutomovil, int IDServicios, int Matriculas){
        ServicioAutomovilEntity servicioAutomovil = getServicioAuto(idServicioAutomovil);
        if(servicioAutomovil != null){
            EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
            entityTransaction.begin();
            ConexionBD.getEm().remove(servicioAutomovil);
            entityTransaction.commit();
        }
    }

    public static ServicioAutomovilEntity getServicioAuto(int idServicioAutomovil){
        return ConexionBD.getEm().find(ServicioAutomovilEntity.class, idServicioAutomovil);
    }
}
