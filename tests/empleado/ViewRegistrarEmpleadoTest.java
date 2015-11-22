package empleado;

import entidades.EmpleadoEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by ValdemarRamos on 20/11/2015.
 */
public class ViewRegistrarEmpleadoTest {

    private ObservableList<EmpleadoEntity> dataEmpleado = FXCollections.observableArrayList();

    @Test
    public void testValidarDatosRegistroEmpleado() throws Exception {
        ViewRegistrarEmpleado viewRegistrarEmpleado = new ViewRegistrarEmpleado();
        String id="102";
        String nombre="jose";
        String puesto="hojalatero";
        Assert.assertEquals(true,viewRegistrarEmpleado.validarDatosRegistro(id,nombre,puesto));
    }
    @Test
    public void testValidarDatosRegistroEmpleadoIncorrecto() throws Exception{
        ViewRegistrarEmpleado viewRegistrarEmpleado = new ViewRegistrarEmpleado();
        String id="102";
        String nombre="luis";
        String puesto="mecanico";
        Assert.assertEquals(false,viewRegistrarEmpleado.validarDatosRegistro(id,nombre,puesto));
    }
}