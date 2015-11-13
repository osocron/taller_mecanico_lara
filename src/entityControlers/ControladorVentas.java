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

    public static int getLastID(){
        List<VentasEntity> ventasEntities = getVentas();
        final int[] lastID = {0};
        ventasEntities.forEach(ventasEntity -> {
            if (ventasEntity.getIdVenta() > lastID[0]){
                lastID[0] = ventasEntity.getIdVenta();
            }
        });
        return lastID[0];
    }

    public static void modificarIDventa(int IDventa){
        VentasEntity ventas = getVentaPorID(IDventa);
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        ventas.setIdVenta(IDventa);
        entityTransaction.commit();
    }

    public static void modificarCliente(int IDVenta, String IDClientes){
        VentasEntity ventas = getVentaPorID(IDVenta);
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        ventas.setIdClientes(IDClientes);
        entityTransaction.commit();
    }

    public static void modificarFecha(int IDVenta, java.sql.Date fecha){
        VentasEntity ventas = getVentaPorID(IDVenta);
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        ventas.setFecha(fecha);
        entityTransaction.commit();
    }

    public static void eliminarVenta(Integer IDVenta){
        VentasEntity venta = getVentaPorID(IDVenta);
        if(IDVenta != 0){
            EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
            entityTransaction.begin();
            ConexionBD.getEm().remove(venta);
            entityTransaction.commit();
        }
    }

    public static VentasEntity getVentaPorID(int IDVenta){
        return ConexionBD.getEm().find(VentasEntity.class, IDVenta);
    }
}
