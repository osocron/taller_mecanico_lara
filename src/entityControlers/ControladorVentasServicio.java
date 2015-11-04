package entityControlers;

import entities.VentaServicioEntity;

import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * Created by ValdemarRamos on 27/10/2015.
 */
public class ControladorVentasServicio {
    public static List<VentaServicioEntity> getVentaServicio(){
        return ConexionBD.getEm().createQuery("SELECT vs FROM VentaServicioEntity vs")
                .getResultList();
    }

    public static void guardarVentaServicio(VentaServicioEntity ventaServicio){
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        ConexionBD.getEm().persist(ventaServicio);
        entityTransaction.commit();
    }

    public static VentaServicioEntity crearVentaServicio(int idVentaServicio, int IDVentas, int IDServicios){
        VentaServicioEntity ventaServicio = new VentaServicioEntity();
        ventaServicio.setIdServicios(idVentaServicio);
        ventaServicio.setIdVentas(IDVentas);
        ventaServicio.setIdServicios(IDServicios);
        ConexionBD.getEm().persist(ventaServicio);
        return ventaServicio;
    }

    public static void modificarVentaServicio(int idVentaServicio, int IDVentas, int IDServicios){
        VentaServicioEntity ventaServicio = getVentaServicioID(idVentaServicio);
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        ventaServicio.setIdVentas(IDVentas);
        ventaServicio.setIdServicios(IDServicios);
        entityTransaction.commit();
    }
    public static void eliminarVentaServicio(int idVentaServicio){
        VentaServicioEntity ventaServicio = getVentaServicioID(idVentaServicio);
        if(idVentaServicio != 0){
            EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
            entityTransaction.begin();
            ConexionBD.getEm().remove(ventaServicio);
            entityTransaction.commit();
        }
    }

    public static VentaServicioEntity getVentaServicioID(int idVentaServicio){
        return ConexionBD.getEm().find(VentaServicioEntity.class, idVentaServicio);
    }
}
