package entidades;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by osocron on 25/11/15.
 */
@Entity
@Table(name = "automoviles", schema = "", catalog = "taller_mecanico_lara")
public class AutomovilesEntity {
    private String matricula;
    private String marca;
    private String modelo;
    private String color;
    private String idClientes;
    private ClienteEntity clienteByIdClientes;
    private Collection<ServicioAutomovilEntity> servicioautomovilsByMatricula;

    @Id
    @Column(name = "Matricula")
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Basic
    @Column(name = "Marca")
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Basic
    @Column(name = "Modelo")
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Basic
    @Column(name = "Color")
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

        AutomovilesEntity that = (AutomovilesEntity) o;

        if (matricula != null ? !matricula.equals(that.matricula) : that.matricula != null) return false;
        if (marca != null ? !marca.equals(that.marca) : that.marca != null) return false;
        if (modelo != null ? !modelo.equals(that.modelo) : that.modelo != null) return false;
        if (color != null ? !color.equals(that.color) : that.color != null) return false;
        if (idClientes != null ? !idClientes.equals(that.idClientes) : that.idClientes != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = matricula != null ? matricula.hashCode() : 0;
        result = 31 * result + (marca != null ? marca.hashCode() : 0);
        result = 31 * result + (modelo != null ? modelo.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (idClientes != null ? idClientes.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "IDClientes", referencedColumnName = "IDCliente", nullable = false)
    public ClienteEntity getClienteByIdClientes() {
        return clienteByIdClientes;
    }

    public void setClienteByIdClientes(ClienteEntity clienteByIdClientes) {
        this.clienteByIdClientes = clienteByIdClientes;
    }

    @OneToMany(mappedBy = "automovilesByMatricula")
    public Collection<ServicioAutomovilEntity> getServicioautomovilsByMatricula() {
        return servicioautomovilsByMatricula;
    }

    public void setServicioautomovilsByMatricula(Collection<ServicioAutomovilEntity> servicioautomovilsByMatricula) {
        this.servicioautomovilsByMatricula = servicioautomovilsByMatricula;
    }

    @Override
    public String toString() {
        return marca+" "+modelo+" "+color;
    }
}
