package entityControlers;

import entities.EmpleadoEntity;

import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * Created by ValdemarRamos on 27/10/2015.
 */
public class ControladorEmpleado {
    public static List<EmpleadoEntity> getEmpleados(){
        return ConexionBD.getEm().createQuery("SELECT c FROM EmpleadoEntity c")
                .getResultList();
    }
    public static void guardarEmpleadp(EmpleadoEntity empleado){
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        ConexionBD.getEm().persist(empleado);
        entityTransaction.commit();
    }

    public static EmpleadoEntity crearEmpleado(int IDEmpleado, String Nombre, String Puesto){
        EmpleadoEntity empleado = new EmpleadoEntity();
        empleado.setIdEmpleado(IDEmpleado);
        empleado.setNombre(Nombre);
        empleado.setPuesto(Puesto);
        ConexionBD.getEm().persist(empleado);
        return empleado;
    }
    public static void modificarEmpleado(String Nombre, String Puesto){
        EmpleadoEntity empleado = getEmpleadoPorNombre(Nombre);
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        empleado.setNombre(Nombre);
        empleado.setPuesto(Puesto);
        entityTransaction.commit();
    }
    public static void eliminarEmpleado(String Nombre){
        EmpleadoEntity empleado = getEmpleadoPorNombre(Nombre);
        if(Nombre != null){
            EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
            entityTransaction.begin();
            ConexionBD.getEm().remove(empleado);
            entityTransaction.commit();
        }
    }

    public static EmpleadoEntity getEmpleadoPorNombre(String Nombre){
        return ConexionBD.getEm().find(EmpleadoEntity.class, Nombre);
    }
}

