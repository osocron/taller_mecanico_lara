package viewControlers;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by osocron on 18/11/15.
 */
public class ViewRegistrarEmpleadoTest {

    @Test
    public void testValidarDatosRegistro() throws Exception {
        ViewRegistrarEmpleado viewRegistrarEmpleado = new ViewRegistrarEmpleado();
        String id = "34";
        String nombre = "Roberto Baez Mora";
        String puesto = "Gerente";
        Assert.assertEquals(true,viewRegistrarEmpleado.validarDatosRegistro(id,nombre,puesto));
    }

    @Test
    public void testValidarDatosRegistroConDatosFaltantes() throws Exception {
        ViewRegistrarEmpleado viewRegistrarEmpleado = new ViewRegistrarEmpleado();
        String id = "34";
        String nombre = "";
        String puesto = "";
        Assert.assertEquals(false,viewRegistrarEmpleado.validarDatosRegistro(id,nombre,puesto));
    }

}