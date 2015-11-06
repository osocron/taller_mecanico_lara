package entityControlers;

import entidades.VentasEntity;

import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * Created by ValdemarRamos on 27/10/2015.
 */
public class ControladorVentas {
    public static List<VentasEntity> getVentas(){
        return ConexionBD.getEm().createQuery("SELECT v FROM VentasEntity v")
                .getResultList();
    }
    public static void guardarVenta(VentasEntity ventas){
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        ConexionBD.getEm().persist(ventas);
        entityTransaction.commit();
    }

    public static VentasEntity crearVenta(int IDVenta, java.sql.Date Fecha, String IDClientes){
        VentasEntity ventas = new VentasEntity();
        ventas.setIdVenta(IDVenta);
        ventas.setFecha(Fecha);
        ventas.setIdClientes(IDClientes);
        ConexionBD.getEm().persist(ventas);
        return ventas;
    }

    public static void modificarVenta(int IDVenta, java.sql.Date Fecha, String IDClientes){
        VentasEntity ventas = getVentaPorID(IDVenta);
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        ventas.setFecha(Fecha);
        ventas.setIdClientes(IDClientes);
        entityTransaction.commit();
    }
    public static void eliminarVenta(int IDVenta){
        VentasEntity venta = getVentaPorID(IDVenta);
        if(IDVenta != 0){
            EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
            entityTransaction.begin();
            ConexionBD.getEm().remove(IDVenta);
            entityTransaction.commit();
        }
    }

    public static VentasEntity getVentaPorID(int IDVenta){
        return ConexionBD.getEm().find(VentasEntity.class, IDVenta);
    }
}
