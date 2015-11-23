package venta;

import cliente.ViewRegistrarCliente;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ValdemarRamos on 22/11/2015.
 */
public class ViewRegistrarVentaTest {

    @Test
    public void testVerificarDatosVentaCorrecta() throws Exception {
        ViewRegistrarVenta viewRegistrarVenta = new ViewRegistrarVenta();
        String idVenta="3";
        String idCliente="AFFG123411";
        Assert.assertEquals(true,viewRegistrarVenta.verificarDatosVenta(idVenta,idCliente));
    }
    @Test
    public void testVerificarDatosVentaIDclienteIncorrecta() throws Exception{
        ViewRegistrarVenta viewRegistrarVenta = new ViewRegistrarVenta();
        String idVenta="1";
        String idCliente="3";
        Assert.assertEquals(true,viewRegistrarVenta.verificarDatosVenta(idVenta,idCliente));
    }
}