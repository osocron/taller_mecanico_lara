package viewControlers;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by osocron on 18/11/15.
 */
public class ViewRegistrarRefaccionTest {

    @Test
    public void testVerificarDatosRegistroCorrectos() throws Exception {
        ViewRegistrarRefaccion viewRegistrarRefaccion = new ViewRegistrarRefaccion();
        String descripcion = "Balata marca Good-Year 12cm";
        String precio = "72.30";
        String cantidad = "22";
        Assert.assertEquals(true, viewRegistrarRefaccion.verificarDatosRegistro(descripcion,precio,cantidad));
    }

    @Test
    public void testVerificarDatosRegistroConDatosFaltantes() throws Exception {
        ViewRegistrarRefaccion viewRegistrarRefaccion = new ViewRegistrarRefaccion();
        String descripcion = "";
        String precio = "72.30";
        String cantidad = "22";
        Assert.assertEquals(false, viewRegistrarRefaccion.verificarDatosRegistro(descripcion,precio,cantidad));
    }

    @Test
    public void testVerificarDatosRegistroPrecioNoNumerico() throws Exception {
        ViewRegistrarRefaccion viewRegistrarRefaccion = new ViewRegistrarRefaccion();
        String descripcion = "Balata marca Good-Year 12cm";
        String precio = "Hola";
        String cantidad = "22";
        Assert.assertEquals(false, viewRegistrarRefaccion.verificarDatosRegistro(descripcion,precio,cantidad));
    }

    @Test
    public void testVerificarDatosRegistroCantidadNoNumerico() throws Exception {
        ViewRegistrarRefaccion viewRegistrarRefaccion = new ViewRegistrarRefaccion();
        String descripcion = "Balata marca Good-Year 12cm";
        String precio = "72.30";
        String cantidad = "Mundo";
        Assert.assertEquals(false, viewRegistrarRefaccion.verificarDatosRegistro(descripcion,precio,cantidad));
    }

}