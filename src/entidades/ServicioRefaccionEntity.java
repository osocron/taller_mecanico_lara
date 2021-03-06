package entidades;

import javax.persistence.*;

/**
 * Created by osocron on 25/11/15.
 */
@Entity
@Table(name = "serviciorefaccion", schema = "", catalog = "taller_mecanico_lara")
public class ServicioRefaccionEntity {
    private int idServicioRefaccion;
    private int idServicios;
    private int idRefacciones;
    private int cantidad;
    private ServicioEntity servicioByIdServicios;
    private RefaccionEntity refaccionByIdRefacciones;

    @Id
    @Column(name = "idServicioRefaccion")
    public int getIdServicioRefaccion() {
        return idServicioRefaccion;
    }

    public void setIdServicioRefaccion(int idServicioRefaccion) {
        this.idServicioRefaccion = idServicioRefaccion;
    }

    @Basic
    @Column(name = "IDServicios")
    public int getIdServicios() {
        return idServicios;
    }

    public void setIdServicios(int idServicios) {
        this.idServicios = idServicios;
    }

    @Basic
    @Column(name = "IDRefacciones")
    public int getIdRefacciones() {
        return idRefacciones;
    }

    public void setIdRefacciones(int idRefacciones) {
        this.idRefacciones = idRefacciones;
    }

    @Basic
    @Column(name = "cantidad")
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServicioRefaccionEntity that = (ServicioRefaccionEntity) o;

        if (idServicioRefaccion != that.idServicioRefaccion) return false;
        if (idServicios != that.idServicios) return false;
        if (idRefacciones != that.idRefacciones) return false;
        if (cantidad != that.cantidad) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idServicioRefaccion;
        result = 31 * result + idServicios;
        result = 31 * result + idRefacciones;
        result = 31 * result + cantidad;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "IDServicios", referencedColumnName = "IDServicio", nullable = false)
    public ServicioEntity getServicioByIdServicios() {
        return servicioByIdServicios;
    }

    public void setServicioByIdServicios(ServicioEntity servicioByIdServicios) {
        this.servicioByIdServicios = servicioByIdServicios;
    }

    @ManyToOne
    @JoinColumn(name = "IDRefacciones", referencedColumnName = "IDRefaccion", nullable = false)
    public RefaccionEntity getRefaccionByIdRefacciones() {
        return refaccionByIdRefacciones;
    }

    public void setRefaccionByIdRefacciones(RefaccionEntity refaccionByIdRefacciones) {
        this.refaccionByIdRefacciones = refaccionByIdRefacciones;
    }
}
