package sample;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Servicio")
public class Servicio implements Serializable{
    @Id
    @Column(name = "IDServicio")
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer idservicio;
    private String descripcion;
    private float costo;
    private Integer idempleados;

    public Integer getIdservicio(){
        return idservicio;
    }
    public void setIdservicio(Integer idservicio){
        this.idservicio=idservicio;
    }
    public String getDescripcion(){
        return descripcion;
    }
    public void setDescripcion(String descripcion){
        this.descripcion=descripcion;
    }
    public float getCosto(){
        return costo;
    }
    public void setCosto(float costo){
        this.costo=costo;
    }
    public Integer getIdempleados(){
        return idempleados;
    }
    public void setIdempleados(Integer idempleados){
        this.idempleados=idempleados;
    }
    public String toString(){
        StringBuffer buffer = new StringBuffer();
        buffer.append("Descripción:"+ descripcion + ";");
        buffer.append("Costo" + costo + ";");
        buffer.append("Idempleados"+ idempleados+ ";");
        return buffer.toString();
    }
}
