package refaccion;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ValdemarRamos on 20/11/2015.
 */
public class ViewRegistrarRefaccionTest {

    @Test
    public void testVerificarDatosRegistroRefaccionCorrectos() throws Exception {
        ViewRegistrarRefaccion viewRegistrarRefaccion = new ViewRegistrarRefaccion();
        String descripcion = "barato";
        String precio ="10";
        String cantidad ="2";
        Assert.assertEquals(true,viewRegistrarRefaccion.verificarDatosRegistro(descripcion,precio,cantidad));
    }
    @Test
    public void testVerificarDatosRegistrosRefaccionesIncorrectos() throws Exception{
        ViewRegistrarRefaccion viewRegistrarRefaccion = new ViewRegistrarRefaccion();
        String descripcion = "barato";
        String precio ="qqqw";
        String cantidad ="ewwww";
        Assert.assertEquals(false,viewRegistrarRefaccion.verificarDatosRegistro(descripcion,precio,cantidad));
    }
}