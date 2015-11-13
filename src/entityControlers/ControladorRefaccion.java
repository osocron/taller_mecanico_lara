package entityControlers;

import entidades.RefaccionEntity;

import javax.persistence.EntityTransaction;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by ValdemarRamos on 27/10/2015.
 */
public class ControladorRefaccion {
    public static List<RefaccionEntity> getRefacciones(){
        return ConexionBD.getEm().createQuery("SELECT r FROM RefaccionEntity r")
                .getResultList();
    }
    public static void guardarRefaccion(RefaccionEntity refaccion){
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        ConexionBD.getEm().persist(refaccion);
        entityTransaction.commit();
    }

    public static RefaccionEntity crearRefaccion(int IDRefaccion, String Marca, BigDecimal Precio, int Cantidad){
        RefaccionEntity refaccion = new RefaccionEntity();
        refaccion.setIdRefaccion(IDRefaccion);
        refaccion.setMarca(Marca);
        refaccion.setPrecio(Precio);
        refaccion.setCantidad(Cantidad);
        ConexionBD.getEm().persist(refaccion);
        return refaccion;
    }

    public static int getLastID(){
        List<RefaccionEntity> refaccionEntityList = getRefacciones();
        final int[] lastID = {0};
        refaccionEntityList.forEach(refaccionEntity -> {
            if(refaccionEntity.getIdRefaccion() > lastID[0]){
                lastID[0] = refaccionEntity.getIdRefaccion();
            }
        });
        return lastID[0];
    }

    public static void modificarMarca(int idRefaccion,String marca){
        RefaccionEntity refaccion = getRefaccionPorID(idRefaccion);
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        refaccion.setIdRefaccion(idRefaccion);
        refaccion.setMarca(marca);
        entityTransaction.commit();
    }

    public static void modificarPrecio(int idRefaccion, BigDecimal Precio){
        RefaccionEntity refaccion = getRefaccionPorID(idRefaccion);
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        refaccion.setIdRefaccion(idRefaccion);
        refaccion.setPrecio(Precio);
        entityTransaction.commit();
    }

    public static void modificarCantidad(int idRefaccion, int Cantidad){
        RefaccionEntity refaccion = getRefaccionPorID(idRefaccion);
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        refaccion.setIdRefaccion(idRefaccion);
        refaccion.setCantidad(Cantidad);
        entityTransaction.commit();
    }

    public static void eliminarRefaccion(int idRefaccion){
        RefaccionEntity refaccion = getRefaccionPorID(idRefaccion);
        if(idRefaccion >= 0){
            EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
            entityTransaction.begin();
            ConexionBD.getEm().remove(refaccion);
            entityTransaction.commit();
        }
    }

    public static RefaccionEntity getRefaccionPorID(int idRefaccion){
        return ConexionBD.getEm().find(RefaccionEntity.class, idRefaccion);
    }

}
