package entities;

import javax.persistence.*;

/**
 * Created by osocron on 4/11/15.
 */
@Entity
@Table(name = "VentaServicio", schema = "", catalog = "taller_mecanico_lara")
public class VentaServicioEntity {
    private int idVentaServicio;
    private int idVentas;
    private int idServicios;
    private VentasEntity ventasByIdVentas;
    private ServicioEntity servicioByIdServicios;

    @Id
    @Column(name = "idVentaServicio")
    public int getIdVentaServicio() {
        return idVentaServicio;
    }

    public void setIdVentaServicio(int idVentaServicio) {
        this.idVentaServicio = idVentaServicio;
    }

    @Basic
    @Column(name = "IDVentas")
    public int getIdVentas() {
        return idVentas;
    }

    public void setIdVentas(int idVentas) {
        this.idVentas = idVentas;
    }

    @Basic
    @Column(name = "IDServicios")
    public int getIdServicios() {
        return idServicios;
    }

    public void setIdServicios(int idServicios) {
        this.idServicios = idServicios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VentaServicioEntity that = (VentaServicioEntity) o;

        if (idVentaServicio != that.idVentaServicio) return false;
        if (idVentas != that.idVentas) return false;
        if (idServicios != that.idServicios) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idVentaServicio;
        result = 31 * result + idVentas;
        result = 31 * result + idServicios;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "IDVentas", referencedColumnName = "IDVenta", nullable = false)
    public VentasEntity getVentasByIdVentas() {
        return ventasByIdVentas;
    }

    public void setVentasByIdVentas(VentasEntity ventasByIdVentas) {
        this.ventasByIdVentas = ventasByIdVentas;
    }

    @ManyToOne
    @JoinColumn(name = "IDServicios", referencedColumnName = "IDServicio", nullable = false)
    public ServicioEntity getServicioByIdServicios() {
        return servicioByIdServicios;
    }

    public void setServicioByIdServicios(ServicioEntity servicioByIdServicios) {
        this.servicioByIdServicios = servicioByIdServicios;
    }
}
