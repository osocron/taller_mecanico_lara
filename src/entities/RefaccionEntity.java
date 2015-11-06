package entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

/**
 * Created by osocron on 4/11/15.
 */
@Entity
@Table(name = "Refaccion", schema = "", catalog = "taller_mecanico_lara")
public class RefaccionEntity {
    private int idRefaccion;
    private String marca;
    private Float precio;
    private int cantidad;
    private Collection<ServicioRefaccionEntity> servicioRefaccionsByIdRefaccion;
    private Collection<VentaRefaccionEntity> ventaRefaccionsByIdRefaccion;

    @Id
    @Column(name = "IDRefaccion")
    public int getIdRefaccion() {
        return idRefaccion;
    }

    public void setIdRefaccion(int idRefaccion) {
        this.idRefaccion = idRefaccion;
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
    @Column(name = "Precio")
    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    @Basic
    @Column(name = "Cantidad")
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RefaccionEntity that = (RefaccionEntity) o;

        if (idRefaccion != that.idRefaccion) return false;
        if (cantidad != that.cantidad) return false;
        if (marca != null ? !marca.equals(that.marca) : that.marca != null) return false;
        if (precio != null ? !precio.equals(that.precio) : that.precio != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRefaccion;
        result = 31 * result + (marca != null ? marca.hashCode() : 0);
        result = 31 * result + (precio != null ? precio.hashCode() : 0);
        result = 31 * result + cantidad;
        return result;
    }

    @OneToMany(mappedBy = "refaccionByIdRefacciones")
    public Collection<ServicioRefaccionEntity> getServicioRefaccionsByIdRefaccion() {
        return servicioRefaccionsByIdRefaccion;
    }

    public void setServicioRefaccionsByIdRefaccion(Collection<ServicioRefaccionEntity> servicioRefaccionsByIdRefaccion) {
        this.servicioRefaccionsByIdRefaccion = servicioRefaccionsByIdRefaccion;
    }

    @OneToMany(mappedBy = "refaccionByIdRefacciones")
    public Collection<VentaRefaccionEntity> getVentaRefaccionsByIdRefaccion() {
        return ventaRefaccionsByIdRefaccion;
    }

    public void setVentaRefaccionsByIdRefaccion(Collection<VentaRefaccionEntity> ventaRefaccionsByIdRefaccion) {
        this.ventaRefaccionsByIdRefaccion = ventaRefaccionsByIdRefaccion;
    }
    @Override
    public String toString(){
        return marca;
    }
}
