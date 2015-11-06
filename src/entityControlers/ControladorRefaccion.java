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

    public static RefaccionEntity crearRefaccion(int IDRefaccion, String Marca, Float Precio, int Cantidad){
        RefaccionEntity refaccion = new RefaccionEntity();
        refaccion.setIdRefaccion(IDRefaccion);
        refaccion.setMarca(Marca);
        refaccion.setPrecio(Precio);
        refaccion.setCantidad(Cantidad);
        ConexionBD.getEm().persist(refaccion);
        return refaccion;
    }
    public static void modificarRefaccion(String Marca, float Precio, int Cantidad){
        RefaccionEntity refaccion = getRefaccionPorMarca(Marca);
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        refaccion.setMarca(Marca);
        refaccion.setPrecio(Precio);
        refaccion.setCantidad(Cantidad);
        entityTransaction.commit();
    }
    public static void eliminarRefaccion(String Marca){
        RefaccionEntity refaccion = getRefaccionPorMarca(Marca);
        if(Marca != null){
            EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
            entityTransaction.begin();
            ConexionBD.getEm().remove(refaccion);
            entityTransaction.commit();
        }
    }
    public static RefaccionEntity getRefaccionPorMarca(String Marca){
        return ConexionBD.getEm().find(RefaccionEntity.class, Marca);
    }
}
