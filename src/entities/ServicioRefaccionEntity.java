package entities;

import javax.persistence.*;

/**
 * Created by osocron on 4/11/15.
 */
@Entity
@Table(name = "ServicioRefaccion", schema = "", catalog = "taller_mecanico_lara")
public class ServicioRefaccionEntity {
    private int idServicioRefaccion;
    private int idServicios;
    private int idRefacciones;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServicioRefaccionEntity that = (ServicioRefaccionEntity) o;

        if (idServicioRefaccion != that.idServicioRefaccion) return false;
        if (idServicios != that.idServicios) return false;
        if (idRefacciones != that.idRefacciones) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idServicioRefaccion;
        result = 31 * result + idServicios;
        result = 31 * result + idRefacciones;
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
