package sample;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "serviciorefaccion")
public class ServicioRefaccion implements Serializable{
    @Id
    @Column(name = "IDServicios")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idservicios;
    private Integer idrefacciones;

    public Integer getIdservicios(){
        return idservicios;
    }
    public void setIdservicios(Integer idservicios){
        this.idservicios=idservicios;
    }
    public Integer getIdrefacciones(){
        return idrefacciones;
    }
    public void setIdrefacciones(Integer idrefacciones){
        this.idrefacciones=idrefacciones;
    }
    public String toString(){
        StringBuffer buffer = new StringBuffer();
        buffer.append("IDrefacciones:"+ idrefacciones + ";");
        return buffer.toString();
    }
}
