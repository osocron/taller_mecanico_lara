package entityControlers;

import entities.ServicioRefaccionEntity;

import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * Created by ValdemarRamos on 27/10/2015.
 */
public class ControladorServicioRefaccion {
    public static List<ServicioRefaccionEntity> getServicioRefaccion(){
        return ConexionBD.getEm().createQuery("SELECT c FROM UsuarioEntity c")
                .getResultList();
    }
    public static void guardarServicioRefaccion(ServicioRefaccionEntity servicioRefaccion){
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        ConexionBD.getEm().persist(servicioRefaccion);
        entityTransaction.commit();
    }

    public static ServicioRefaccionEntity crearServicioRefaccion(int idServicioRefaccion, int IDServicios, int IDRefacciones){
        ServicioRefaccionEntity servicioRefaccion = new ServicioRefaccionEntity();
        servicioRefaccion.setIdServicioRefaccion(idServicioRefaccion);
        servicioRefaccion.setIdServicios(IDServicios);
        servicioRefaccion.setIdRefacciones(IDRefacciones);
        ConexionBD.getEm().persist(servicioRefaccion);
        return servicioRefaccion;
    }

    public static void modificarServicioRefaccion(int idServicioRefaccion, int IDServicios, int IDRefacciones){
        ServicioRefaccionEntity servicioRefaccion = getServicioRefaccionID(idServicioRefaccion);
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        servicioRefaccion.setIdServicios(IDServicios);
        servicioRefaccion.setIdRefacciones(IDRefacciones);
        entityTransaction.commit();
    }
    public static void eliminarServicioRefaccion(int idServicioRefaccion){
        ServicioRefaccionEntity servicioRefaccion = getServicioRefaccionID(idServicioRefaccion);
        if(idServicioRefaccion != 0){
            EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
            entityTransaction.begin();
            ConexionBD.getEm().remove(servicioRefaccion);
            entityTransaction.commit();
        }
    }

    public static ServicioRefaccionEntity getServicioRefaccionID(int idServicioRefaccion){
        return ConexionBD.getEm().find(ServicioRefaccionEntity.class, idServicioRefaccion);
    }
}
