package entities;

import javax.persistence.*;

/**
 * Created by osocron on 4/11/15.
 */
@Entity
@Table(name = "ServicioAutomovil", schema = "", catalog = "taller_mecanico_lara")
public class ServicioAutomovilEntity {
    private int idServicioAutomovil;
    private int idServicios;
    private String matricula;
    private AutomovilesEntity automovilesByMatricula;
    private ServicioEntity servicioByIdServicios;

    @Id
    @Column(name = "idServicioAutomovil")
    public int getIdServicioAutomovil() {
        return idServicioAutomovil;
    }

    public void setIdServicioAutomovil(int idServicioAutomovil) {
        this.idServicioAutomovil = idServicioAutomovil;
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
    @Column(name = "Matricula")
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServicioAutomovilEntity that = (ServicioAutomovilEntity) o;

        if (idServicioAutomovil != that.idServicioAutomovil) return false;
        if (idServicios != that.idServicios) return false;
        if (matricula != null ? !matricula.equals(that.matricula) : that.matricula != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idServicioAutomovil;
        result = 31 * result + idServicios;
        result = 31 * result + (matricula != null ? matricula.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "Matricula", referencedColumnName = "Matricula", nullable = false)
    public AutomovilesEntity getAutomovilesByMatricula() {
        return automovilesByMatricula;
    }

    public void setAutomovilesByMatricula(AutomovilesEntity automovilesByMatricula) {
        this.automovilesByMatricula = automovilesByMatricula;
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
