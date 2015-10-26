package entidades;

import javax.persistence.*;

/**
 * Created by osocron on 26/10/15.
 */
@Entity
@Table(name = "ClienteAutomovil", schema = "", catalog = "taller_mecanico_lara")
public class ClienteAutomovilEntity {
    private int idClienteAutomovil;
    private int idClientes;
    private int matriculas;
    private ClienteEntity clienteByIdClientes;
    private AutomovilesEntity automovilesByMatriculas;

    @Id
    @Column(name = "idClienteAutomovil")
    public int getIdClienteAutomovil() {
        return idClienteAutomovil;
    }

    public void setIdClienteAutomovil(int idClienteAutomovil) {
        this.idClienteAutomovil = idClienteAutomovil;
    }

    @Basic
    @Column(name = "IDClientes")
    public int getIdClientes() {
        return idClientes;
    }

    public void setIdClientes(int idClientes) {
        this.idClientes = idClientes;
    }

    @Basic
    @Column(name = "Matriculas")
    public int getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(int matriculas) {
        this.matriculas = matriculas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClienteAutomovilEntity that = (ClienteAutomovilEntity) o;

        if (idClienteAutomovil != that.idClienteAutomovil) return false;
        if (idClientes != that.idClientes) return false;
        if (matriculas != that.matriculas) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idClienteAutomovil;
        result = 31 * result + idClientes;
        result = 31 * result + matriculas;
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

    @ManyToOne
    @JoinColumn(name = "Matriculas", referencedColumnName = "Matricula", nullable = false)
    public AutomovilesEntity getAutomovilesByMatriculas() {
        return automovilesByMatriculas;
    }

    public void setAutomovilesByMatriculas(AutomovilesEntity automovilesByMatriculas) {
        this.automovilesByMatriculas = automovilesByMatriculas;
    }
}
