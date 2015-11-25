package entidades;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by osocron on 25/11/15.
 */
@Entity
@Table(name = "cliente", schema = "", catalog = "taller_mecanico_lara")
public class ClienteEntity {
    private String idCliente;
    private String nombre;
    private String direccion;
    private String telefono;
    private Collection<AutomovilesEntity> automovilesByIdCliente;
    private Collection<VentasEntity> ventasesByIdCliente;

    @Id
    @Column(name = "IDCliente")
    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    @Basic
    @Column(name = "Nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "Direccion")
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Basic
    @Column(name = "Telefono")
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClienteEntity that = (ClienteEntity) o;

        if (idCliente != null ? !idCliente.equals(that.idCliente) : that.idCliente != null) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (direccion != null ? !direccion.equals(that.direccion) : that.direccion != null) return false;
        if (telefono != null ? !telefono.equals(that.telefono) : that.telefono != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCliente != null ? idCliente.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (direccion != null ? direccion.hashCode() : 0);
        result = 31 * result + (telefono != null ? telefono.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "clienteByIdClientes")
    public Collection<AutomovilesEntity> getAutomovilesByIdCliente() {
        return automovilesByIdCliente;
    }

    public void setAutomovilesByIdCliente(Collection<AutomovilesEntity> automovilesByIdCliente) {
        this.automovilesByIdCliente = automovilesByIdCliente;
    }

    @OneToMany(mappedBy = "clienteByIdClientes")
    public Collection<VentasEntity> getVentasesByIdCliente() {
        return ventasesByIdCliente;
    }

    public void setVentasesByIdCliente(Collection<VentasEntity> ventasesByIdCliente) {
        this.ventasesByIdCliente = ventasesByIdCliente;
    }
}
