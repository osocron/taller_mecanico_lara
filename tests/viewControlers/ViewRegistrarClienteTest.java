package viewControlers;

import cliente.ViewRegistrarCliente;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by osocron on 18/11/15.
 */
public class ViewRegistrarClienteTest {

    @Test
    public void testValidarDatosRegistroConDatosCorrectos() throws Exception {
        ViewRegistrarCliente viewRegistrarCliente = new ViewRegistrarCliente();
        String rfc = "DFCV100495D2F";
        String nombre = "Abel Alejandro Hernandez Lastra";
        String domicilio = "Calle Niños Heroes #34";
        String telefono = "2234225523";
        Assert.assertEquals(true,viewRegistrarCliente.validarDatosRegistro(rfc,nombre,domicilio,telefono));
    }

    @Test
    public void testValidarDatosRegistroConRFCIncorrecta() throws Exception {
        ViewRegistrarCliente viewRegistrarCliente = new ViewRegistrarCliente();
        String rfc = "DFCV1005D2F";
        String nombre = "Abel Alejandro Hernandez Lastra";
        String domicilio = "Calle Niños Heroes #34";
        String telefono = "2234225523";
        Assert.assertEquals(false,viewRegistrarCliente.validarDatosRegistro(rfc,nombre,domicilio,telefono));
    }

    @Test
    public void testValidarDatosRegistroConNombreFaltante() throws Exception {
        ViewRegistrarCliente viewRegistrarCliente = new ViewRegistrarCliente();
        String rfc = "DFCV100495D2F";
        String nombre = "";
        String domicilio = "Calle Niños Heroes #34";
        String telefono = "2234225523";
        Assert.assertEquals(false,viewRegistrarCliente.validarDatosRegistro(rfc,nombre,domicilio,telefono));
    }

    @Test
    public void testValidarDatosRegistroConTelefonoIncorrecto() throws Exception {
        ViewRegistrarCliente viewRegistrarCliente = new ViewRegistrarCliente();
        String rfc = "DFCV100495D2F";
        String nombre = "Abel Alejandro Hernandez Lastra";
        String domicilio = "Calle Niños Heroes #34";
        String telefono = "22342";
        Assert.assertEquals(false,viewRegistrarCliente.validarDatosRegistro(rfc,nombre,domicilio,telefono));
    }

}