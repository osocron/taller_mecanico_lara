package entidades;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

/**
 * Created by osocron on 25/11/15.
 */
@Entity
@Table(name = "servicio", schema = "", catalog = "taller_mecanico_lara")
public class ServicioEntity {
    private int idServicio;
    private String descripcion;
    private BigDecimal costo;
    private int idEmpleados;
    private EmpleadoEntity empleadoByIdEmpleados;
    private Collection<ServicioAutomovilEntity> servicioautomovilsByIdServicio;
    private Collection<ServicioRefaccionEntity> serviciorefaccionsByIdServicio;
    private Collection<VentaServicioEntity> ventaserviciosByIdServicio;

    @Id
    @Column(name = "IDServicio")
    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    @Basic
    @Column(name = "Descripcion")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Basic
    @Column(name = "Costo")
    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    @Basic
    @Column(name = "IDEmpleados")
    public int getIdEmpleados() {
        return idEmpleados;
    }

    public void setIdEmpleados(int idEmpleados) {
        this.idEmpleados = idEmpleados;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServicioEntity that = (ServicioEntity) o;

        if (idServicio != that.idServicio) return false;
        if (idEmpleados != that.idEmpleados) return false;
        if (descripcion != null ? !descripcion.equals(that.descripcion) : that.descripcion != null) return false;
        if (costo != null ? !costo.equals(that.costo) : that.costo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idServicio;
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        result = 31 * result + (costo != null ? costo.hashCode() : 0);
        result = 31 * result + idEmpleados;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "IDEmpleados", referencedColumnName = "IDEmpleado", nullable = false)
    public EmpleadoEntity getEmpleadoByIdEmpleados() {
        return empleadoByIdEmpleados;
    }

    public void setEmpleadoByIdEmpleados(EmpleadoEntity empleadoByIdEmpleados) {
        this.empleadoByIdEmpleados = empleadoByIdEmpleados;
    }

    @OneToMany(mappedBy = "servicioByIdServicios")
    public Collection<ServicioAutomovilEntity> getServicioautomovilsByIdServicio() {
        return servicioautomovilsByIdServicio;
    }

    public void setServicioautomovilsByIdServicio(Collection<ServicioAutomovilEntity> servicioautomovilsByIdServicio) {
        this.servicioautomovilsByIdServicio = servicioautomovilsByIdServicio;
    }

    @OneToMany(mappedBy = "servicioByIdServicios")
    public Collection<ServicioRefaccionEntity> getServiciorefaccionsByIdServicio() {
        return serviciorefaccionsByIdServicio;
    }

    public void setServiciorefaccionsByIdServicio(Collection<ServicioRefaccionEntity> serviciorefaccionsByIdServicio) {
        this.serviciorefaccionsByIdServicio = serviciorefaccionsByIdServicio;
    }

    @OneToMany(mappedBy = "servicioByIdServicios")
    public Collection<VentaServicioEntity> getVentaserviciosByIdServicio() {
        return ventaserviciosByIdServicio;
    }

    public void setVentaserviciosByIdServicio(Collection<VentaServicioEntity> ventaserviciosByIdServicio) {
        this.ventaserviciosByIdServicio = ventaserviciosByIdServicio;
    }
}
