package servicio;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ValdemarRamos on 21/11/2015.
 */
public class ViewRegistrarServicioTest {

    @Test
    public void testVerificarDatosRegistro() throws Exception {
        ViewRegistrarServicio viewRegistrarServicio = new ViewRegistrarServicio();
        String descripcion="muy caro";
        String costo="20";
        String idEmpleados="2";
        Assert.assertEquals(false,viewRegistrarServicio.verificarDatosRegistro(descripcion,costo,idEmpleados));
    }
    @Test
    public void testVerificarDatosIncompletos() throws Exception{
        ViewRegistrarServicio viewRegistrarServicio = new ViewRegistrarServicio();
        String descripcion="";
        String costo="";
        String idEmpleados="2";
        Assert.assertEquals(false,viewRegistrarServicio.verificarDatosRegistro(descripcion,costo,idEmpleados));
    }
    @Test
    public void testVerificarDatosDuplicados() throws Exception{
        ViewRegistrarServicio viewRegistrarServicio = new ViewRegistrarServicio();
        String descripcion="Cambio de Aceite Aceite negro";
        String costo="20";
        String idEmpleados="1";
        Assert.assertEquals(true,viewRegistrarServicio.verificarDatosRegistro(descripcion,costo,idEmpleados));
    }
}