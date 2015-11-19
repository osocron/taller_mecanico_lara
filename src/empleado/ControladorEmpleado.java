package empleado;

import entidades.EmpleadoEntity;
import entityControlers.ConexionBD;

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

    public static int getNextID(){
        List<EmpleadoEntity> empleadoEntities = getEmpleados();
        final int[] id = {0};
        empleadoEntities.forEach(empleadoEntity -> {
            if(empleadoEntity.getIdEmpleado() > id[0]){
                id[0] = empleadoEntity.getIdEmpleado();
            }
        });
        return id[0];
    }

    public static void modificarNombre(int idEmpleado, String nombre){
        EmpleadoEntity empleado = getEmpleadoPorID(idEmpleado);
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        empleado.setNombre(nombre);
        entityTransaction.commit();
    }

    public static void modificarPuesto(int idEmpleado, String Puesto){
        EmpleadoEntity empleado = getEmpleadoPorID(idEmpleado);
        EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
        entityTransaction.begin();
        empleado.setPuesto(Puesto);
        entityTransaction.commit();
    }
    
    public static void eliminarEmpleado(int idEmpleado){
        EmpleadoEntity empleado = getEmpleadoPorID(idEmpleado);
        if(idEmpleado >= 0){
            EntityTransaction entityTransaction = ConexionBD.getEm().getTransaction();
            entityTransaction.begin();
            ConexionBD.getEm().remove(empleado);
            entityTransaction.commit();
        }
    }

    public static EmpleadoEntity getEmpleadoPorID(int idEmpleado){
        return ConexionBD.getEm().find(EmpleadoEntity.class, idEmpleado);
    }
}

