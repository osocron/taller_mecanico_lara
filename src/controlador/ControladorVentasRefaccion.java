package controlador;

import entidades.VentaRefaccionEntity;

import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * Created by ValdemarRamos on 27/10/2015.
 */
public class ControladorVentasRefaccion {
    public static List<VentaRefaccionEntity> getVentasRefaccion(){
        return ConexionBD.getEm().createQuery("SELECT vr FROM VentaRefaccionEntity vr")
                .getResultList();
    }
    public static void guardarVentasRefaccion(VentaRefaccionEntity ventaRefaccion){
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        ConexionBD.getEm().persist(ventaRefaccion);
        entityTransaction.commit();
    }

    public static VentaRefaccionEntity crearVentaRefaccion(int idVentaRefaccion, int IDVentas, int IDRefacciones){
        VentaRefaccionEntity ventaRefaccion = new VentaRefaccionEntity();
        ventaRefaccion.setIdVentaRefaccion(idVentaRefaccion);
        ventaRefaccion.setIdVentas(IDVentas);
        ventaRefaccion.setIdRefacciones(IDRefacciones);
        ConexionBD.getEm().persist(ventaRefaccion);
        return ventaRefaccion;
    }

    public static void modificarVentaRefaccion(int idVentaRefaccion, int IDVentas, int IDRefacciones){
        VentaRefaccionEntity ventaRefaccion = getVentaRefaccionID(idVentaRefaccion);
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        ventaRefaccion.setIdVentas(IDVentas);
        ventaRefaccion.setIdRefacciones(IDRefacciones);
        entityTransaction.commit();
    }
    public static void eliminarVentaRefaccion(int idVentaRefaccion){
        VentaRefaccionEntity ventaRefaccion = getVentaRefaccionID(idVentaRefaccion);
        if(idVentaRefaccion != 0){
            EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
            entityTransaction.begin();
            ConexionBD.getEm().remove(idVentaRefaccion);
            entityTransaction.commit();
        }
    }

    public static VentaRefaccionEntity getVentaRefaccionID(int idVentaRefaccion){
        return ConexionBD.getEm().find(VentaRefaccionEntity.class, idVentaRefaccion);
    }
}
