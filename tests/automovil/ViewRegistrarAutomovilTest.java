package automovil;

import entityControlers.ConexionBD;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ValdemarRamos on 19/11/2015.
 */
public class ViewRegistrarAutomovilTest {

    public ViewRegistrarAutomovilTest(){
        ConexionBD.conectar();
    }

    @Test
    public void testVeirificarDatosDeRegistroMatriculaIncorrecta() throws Exception {
        ViewRegistrarAutomovil viewRegistrarAutomovil = new ViewRegistrarAutomovil();
        String matricula = "11111";
        String modelo = "Mustang";
        String marca = "Ford";
        String color = "Azul";
        Assert.assertEquals(false,viewRegistrarAutomovil.veirificarDatosDeRegistro(matricula,marca,modelo,color));
    }

    @Test
    public void testVeirificarDatosDeRegistroConDatosCorrectos() throws Exception {
        ViewRegistrarAutomovil viewRegistrarAutomovil = new ViewRegistrarAutomovil();
        String matricula = "FCV1278";
        String modelo = "Mustang";
        String marca = "Ford";
        String color = "Azul";
        Assert.assertEquals(true,viewRegistrarAutomovil.veirificarDatosDeRegistro(matricula,marca,modelo,color));
    }
}