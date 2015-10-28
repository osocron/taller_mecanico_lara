package vista;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "servicioautomovil")
public class ServicioAutomovil implements Serializable{
    @Id
    @Column(name = "IDServicios")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idservicios;
    private Integer matricula;

    public Integer getIdservicios(){
        return idservicios;
    }
    public void setIdservicios(Integer idservicios){
        this.idservicios=idservicios;
    }
    public Integer getMatricula(){
        return matricula;
    }
    public void setMatricula(Integer matricula){
        this.matricula=matricula;
    }
    public String toString(){
        StringBuffer buffer = new StringBuffer();
        buffer.append("IDservicio:" + idservicios + ";");
        return buffer.toString();
    }
}
