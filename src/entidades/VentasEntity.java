package entidades;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by osocron on 25/11/15.
 */
@Entity
@Table(name = "ventas", schema = "", catalog = "taller_mecanico_lara")
public class VentasEntity {
    private int idVenta;
    private Date fecha;
    private String idClientes;
    private Collection<VentaRefaccionEntity> ventarefaccionsByIdVenta;
    private ClienteEntity clienteByIdClientes;
    private Collection<VentaServicioEntity> ventaserviciosByIdVenta;

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
    public String getIdClientes() {
        return idClientes;
    }

    public void setIdClientes(String idClientes) {
        this.idClientes = idClientes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VentasEntity that = (VentasEntity) o;

        if (idVenta != that.idVenta) return false;
        if (fecha != null ? !fecha.equals(that.fecha) : that.fecha != null) return false;
        if (idClientes != null ? !idClientes.equals(that.idClientes) : that.idClientes != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idVenta;
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        result = 31 * result + (idClientes != null ? idClientes.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "ventasByIdVentas")
    public Collection<VentaRefaccionEntity> getVentarefaccionsByIdVenta() {
        return ventarefaccionsByIdVenta;
    }

    public void setVentarefaccionsByIdVenta(Collection<VentaRefaccionEntity> ventarefaccionsByIdVenta) {
        this.ventarefaccionsByIdVenta = ventarefaccionsByIdVenta;
    }

    @ManyToOne
    @JoinColumn(name = "IDClientes", referencedColumnName = "IDCliente", nullable = false)
    public ClienteEntity getClienteByIdClientes() {
        return clienteByIdClientes;
    }

    public void setClienteByIdClientes(ClienteEntity clienteByIdClientes) {
        this.clienteByIdClientes = clienteByIdClientes;
    }

    @OneToMany(mappedBy = "ventasByIdVentas")
    public Collection<VentaServicioEntity> getVentaserviciosByIdVenta() {
        return ventaserviciosByIdVenta;
    }

    public void setVentaserviciosByIdVenta(Collection<VentaServicioEntity> ventaserviciosByIdVenta) {
        this.ventaserviciosByIdVenta = ventaserviciosByIdVenta;
    }

    @Override
    public String toString(){
        return idClientes;
    }

}
