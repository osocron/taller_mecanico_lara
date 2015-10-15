package entidades;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by osocron on 14/10/15.
 */
@Entity
@Table(name = "Empleado", schema = "", catalog = "taller_mecanico_lara")
public class EmpleadoEntity {
    private int idEmpleado;
    private String nombre;
    private String puesto;
    private Collection<ServicioEntity> serviciosByIdEmpleado;

    @Id
    @Column(name = "IDEmpleado", nullable = false, insertable = true, updatable = true)
    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    @Basic
    @Column(name = "Nombre", nullable = false, insertable = true, updatable = true, length = 45)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "Puesto", nullable = false, insertable = true, updatable = true, length = 45)
    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmpleadoEntity that = (EmpleadoEntity) o;

        if (idEmpleado != that.idEmpleado) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (puesto != null ? !puesto.equals(that.puesto) : that.puesto != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idEmpleado;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (puesto != null ? puesto.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "empleadoByIdEmpleados")
    public Collection<ServicioEntity> getServiciosByIdEmpleado() {
        return serviciosByIdEmpleado;
    }

    public void setServiciosByIdEmpleado(Collection<ServicioEntity> serviciosByIdEmpleado) {
        this.serviciosByIdEmpleado = serviciosByIdEmpleado;
    }
}
