package usuario;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ValdemarRamos on 22/11/2015.
 */
public class ControladorUsuarioTest {

    @Test
    public void testVerificarDatosCorrectos() throws Exception {
        ControladorUsuario controladorUsuario = new ControladorUsuario();
        String nombre="valdemar";
        String idEmpleado="3";
        Assert.assertEquals(true,controladorUsuario.verificarDatos(nombre,idEmpleado));
    }
    @Test
    public void testVerificarDatosIncorrectos() throws Exception{
        ControladorUsuario controladorUsuario = new ControladorUsuario();
        String nombre = "juan carlos";
        String idEmpleado ="";
        Assert.assertEquals(false,controladorUsuario.verificarDatos(nombre,idEmpleado));
    }
}