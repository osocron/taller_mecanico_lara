package viewControlers;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by osocron on 18/11/15.
 */
public class ViewRegistrarAutomovilTest {

    @Test
    public void testValidarRegistroConDatosCorrectos() throws Exception {
        ViewRegistrarAutomovil viewRegistrarAutomovil = new ViewRegistrarAutomovil();
        String matricula = "SVF1245";
        String marca = "Nissan";
        String modelo = "Tsuru 2012";
        String color = "Azul";
        Assert.assertEquals(true,viewRegistrarAutomovil.veirificarDatosDeRegistro(matricula,marca,modelo,color));
    }

    @Test
    public void testValidarRegistroConMatriculaIncorrecta() throws Exception {
        ViewRegistrarAutomovil viewRegistrarAutomovil = new ViewRegistrarAutomovil();
        String matricula = "XF1$";
        String marca = "Nissan";
        String modelo = "Tsuru 2012";
        String color = "Azul";
        Assert.assertEquals(false,viewRegistrarAutomovil.veirificarDatosDeRegistro(matricula,marca,modelo,color));
    }

    @Test
    public void testValidarRegistroConDatosFaltantes() throws Exception {
        ViewRegistrarAutomovil viewRegistrarAutomovil = new ViewRegistrarAutomovil();
        String matricula = "SVF1245";
        String marca = "Nissan";
        String modelo = "";
        String color = "Azul";
        Assert.assertEquals(false,viewRegistrarAutomovil.veirificarDatosDeRegistro(matricula,marca,modelo,color));
    }

}