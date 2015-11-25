package entidades;

/**
 * Created by osocron on 25/11/15.
 */
public class ReporteEntity {

    private String idVenta;
    private String fecha;
    private String concepto;
    private String cantidad;
    private String costo;

    public ReporteEntity(String idVenta, String fecha, String concepto, String cantidad, String costo) {
        this.idVenta = idVenta;
        this.fecha = fecha;
        this.concepto = concepto;
        this.cantidad = cantidad;
        this.costo = costo;
    }

    public String getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(String idVenta) {
        this.idVenta = idVenta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

}
