package entidades;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by osocron on 26/10/15.
 */
@Entity
@Table(name = "Ventas", schema = "", catalog = "taller_mecanico_lara")
public class VentasEntity {
    private int idVenta;
    private Date fecha;
    private int idClientes;
    private Collection<VentaRefaccionEntity> ventaRefaccionsByIdVenta;
    private Collection<VentaServicioEntity> ventaServiciosByIdVenta;
    private ClienteEntity clienteByIdClientes;

    @Id
    @Column(name = "IDVenta")
    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    @Basic
    @Column(name = "Fecha")
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Basic
    @Column(name = "IDClientes")
    public int getIdClientes() {
        return idClientes;
    }

    public void setIdClientes(int idClientes) {
        this.idClientes = idClientes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VentasEntity that = (VentasEntity) o;

        if (idVenta != that.idVenta) return false;
        if (idClientes != that.idClientes) return false;
        if (fecha != null ? !fecha.equals(that.fecha) : that.fecha != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idVenta;
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        result = 31 * result + idClientes;
        return result;
    }

    @OneToMany(mappedBy = "ventasByIdVentas")
    public Collection<VentaRefaccionEntity> getVentaRefaccionsByIdVenta() {
        return ventaRefaccionsByIdVenta;
    }

    public void setVentaRefaccionsByIdVenta(Collection<VentaRefaccionEntity> ventaRefaccionsByIdVenta) {
        this.ventaRefaccionsByIdVenta = ventaRefaccionsByIdVenta;
    }

    @OneToMany(mappedBy = "ventasByIdVentas")
    public Collection<VentaServicioEntity> getVentaServiciosByIdVenta() {
        return ventaServiciosByIdVenta;
    }

    public void setVentaServiciosByIdVenta(Collection<VentaServicioEntity> ventaServiciosByIdVenta) {
        this.ventaServiciosByIdVenta = ventaServiciosByIdVenta;
    }

    @ManyToOne
    @JoinColumn(name = "IDClientes", referencedColumnName = "IDCliente", nullable = false)
    public ClienteEntity getClienteByIdClientes() {
        return clienteByIdClientes;
    }

    public void setClienteByIdClientes(ClienteEntity clienteByIdClientes) {
        this.clienteByIdClientes = clienteByIdClientes;
    }
}
