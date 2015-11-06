package entityControlers;

import entidades.ServicioEntity;

import javax.persistence.EntityTransaction;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by ValdemarRamos on 27/10/2015.
 */
public class ControladorServicio {
    public static List<ServicioEntity> getServicios(){
        return ConexionBD.getEm().createQuery("SELECT s FROM ServicioEntity s")
                .getResultList();
    }
    public static void guardarServicio(ServicioEntity servicio){
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        ConexionBD.getEm().persist(servicio);
        entityTransaction.commit();
    }

    public static ServicioEntity crearServicio(int IDServicio, String Descripcion, BigDecimal Costo, int IDEmpleados){
        ServicioEntity servicio = new ServicioEntity();
        servicio.setIdServicio(IDServicio);
        servicio.setDescripcion(Descripcion);
        //servicio.setCosto(Costo);
        servicio.setIdEmpleados(IDEmpleados);
        ConexionBD.getEm().persist(servicio);
        return servicio;
    }

    public static void modificarServicio(int IDServicio, String Descripcion, BigDecimal Costo, int IDEmpleados){
        ServicioEntity servicios = getServicioPorID(IDServicio);
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        servicios.setDescripcion(Descripcion);
        //servicios.setCosto(Costo);
        servicios.setIdEmpleados(IDEmpleados);
        entityTransaction.commit();
    }
    public static void eliminarServicio(int IDServicio){
        ServicioEntity servicios = getServicioPorID(IDServicio);
        if(servicios != null){
            EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
            entityTransaction.begin();
            ConexionBD.getEm().remove(servicios);
            entityTransaction.commit();
        }
    }

    public static ServicioEntity getServicioPorID(int IDServicio){
        return ConexionBD.getEm().find(ServicioEntity.class, IDServicio);
    }
}
