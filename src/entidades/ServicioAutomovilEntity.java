package entidades;

import javax.persistence.*;

/**
 * Created by osocron on 26/10/15.
 */
@Entity
@Table(name = "ServicioAutomovil", schema = "", catalog = "taller_mecanico_lara")
public class ServicioAutomovilEntity {
    private int idServicioAutomovil;
    private int idServicios;
    private int matriculas;
    private ServicioEntity servicioByIdServicios;
    private AutomovilesEntity automovilesByMatriculas;

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

        ServicioAutomovilEntity that = (ServicioAutomovilEntity) o;

        if (idServicioAutomovil != that.idServicioAutomovil) return false;
        if (idServicios != that.idServicios) return false;
        if (matriculas != that.matriculas) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idServicioAutomovil;
        result = 31 * result + idServicios;
        result = 31 * result + matriculas;
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
    @JoinColumn(name = "Matriculas", referencedColumnName = "Matricula", nullable = false)
    public AutomovilesEntity getAutomovilesByMatriculas() {
        return automovilesByMatriculas;
    }

    public void setAutomovilesByMatriculas(AutomovilesEntity automovilesByMatriculas) {
        this.automovilesByMatriculas = automovilesByMatriculas;
    }
}
