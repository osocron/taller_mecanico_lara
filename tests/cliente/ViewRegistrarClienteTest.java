package cliente;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ValdemarRamos on 20/11/2015.
 */
public class ViewRegistrarClienteTest {

    @Test
    public void testValidarDatosRegistroDatosCorrectos() throws Exception {
        ViewRegistrarCliente viewRegistrarCliente = new ViewRegistrarCliente();
        String rfc="agarh12e21";
        String nombre="valdemar";
        String domicilio="av. orizaba";
        String telefono="1234567890";
        Assert.assertEquals(false,viewRegistrarCliente.validarDatosRegistro(rfc,nombre,domicilio,telefono));
    }
    @Test
    public void testValidarDatosRFCDuplicados() throws Exception{
        ViewRegistrarCliente viewRegistrarCliente = new ViewRegistrarCliente();
        String rfc="agarh12e21";
        String nombre="ulises";
        String domicilio="calle pipila";
        String telefono="0987654321";
        Assert.assertEquals(false,viewRegistrarCliente.validarDatosRegistro(rfc,nombre,domicilio,telefono));
    }
}