package entidades;

import javax.persistence.*;

/**
 * Created by osocron on 14/10/15.
 */
@Entity
@Table(name = "VentaRefaccion", schema = "", catalog = "taller_mecanico_lara")
public class VentaRefaccionEntity {
    private int idVentaRefaccion;
    private int idVentas;
    private int idRefacciones;
    private VentasEntity ventasByIdVentas;
    private RefaccionEntity refaccionByIdRefacciones;

    @Id
    @Column(name = "idVentaRefaccion", nullable = false, insertable = true, updatable = true)
    public int getIdVentaRefaccion() {
        return idVentaRefaccion;
    }

    public void setIdVentaRefaccion(int idVentaRefaccion) {
        this.idVentaRefaccion = idVentaRefaccion;
    }

    @Basic
    @Column(name = "IDVentas", nullable = false, insertable = true, updatable = true)
    public int getIdVentas() {
        return idVentas;
    }

    public void setIdVentas(int idVentas) {
        this.idVentas = idVentas;
    }

    @Basic
    @Column(name = "IDRefacciones", nullable = false, insertable = true, updatable = true)
    public int getIdRefacciones() {
        return idRefacciones;
    }

    public void setIdRefacciones(int idRefacciones) {
        this.idRefacciones = idRefacciones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VentaRefaccionEntity that = (VentaRefaccionEntity) o;

        if (idVentaRefaccion != that.idVentaRefaccion) return false;
        if (idVentas != that.idVentas) return false;
        if (idRefacciones != that.idRefacciones) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idVentaRefaccion;
        result = 31 * result + idVentas;
        result = 31 * result + idRefacciones;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "IDVentas", referencedColumnName = "IDVenta", nullable = false)
    public VentasEntity getVentasByIdVentas() {
        return ventasByIdVentas;
    }

    public void setVentasByIdVentas(VentasEntity ventasByIdVentas) {
        this.ventasByIdVentas = ventasByIdVentas;
    }

    @ManyToOne
    @JoinColumn(name = "IDRefacciones", referencedColumnName = "IDRefaccion", nullable = false)
    public RefaccionEntity getRefaccionByIdRefacciones() {
        return refaccionByIdRefacciones;
    }

    public void setRefaccionByIdRefacciones(RefaccionEntity refaccionByIdRefacciones) {
        this.refaccionByIdRefacciones = refaccionByIdRefacciones;
    }
}
