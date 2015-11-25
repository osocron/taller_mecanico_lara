package empleado;

import entidades.EmpleadoEntity;
import entityControlers.ConexionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import servicio.ViewRegistrarServicio;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;


public class ViewRegistrarEmpleadoTest {

    public ViewRegistrarEmpleadoTest(){
        ConexionBD.conectar();
    }


    @Test
    public void testValidarDatosRegistroEmpleado() throws Exception {
        ViewRegistrarEmpleado mock = Mockito.mock(ViewRegistrarEmpleado.class);
        doNothing().when(mock).getWarningAlert(anyString(), anyString(), anyString());
        doCallRealMethod().when(mock).validarDatosRegistro(anyString(),anyString(),anyString());

        //data
        List<EmpleadoEntity> empleadoEntityList = ControladorEmpleado.getEmpleados();
        ObservableList<EmpleadoEntity> dataEmpleados = FXCollections.observableArrayList();
        dataEmpleados.addAll(empleadoEntityList);

        String id="102";
        String nombre="jose";
        String puesto="hojalatero";
        Assert.assertEquals(true,mock.validarDatosRegistro(id,nombre,puesto));
    }
    @Test
    public void testValidarDatosRegistroEmpleadoIncorrecto() throws Exception{
        ViewRegistrarEmpleado mock = Mockito.mock(ViewRegistrarEmpleado.class);
        doNothing().when(mock).getWarningAlert(anyString(), anyString(), anyString());
        doCallRealMethod().when(mock).validarDatosRegistro(anyString(),anyString(),anyString());

        //data
        List<EmpleadoEntity> empleadoEntityList = ControladorEmpleado.getEmpleados();
        ObservableList<EmpleadoEntity> dataEmpleados = FXCollections.observableArrayList();
        dataEmpleados.addAll(empleadoEntityList);

        String id="102";
        String nombre="";//falta un campo para la prueba de error
        String puesto="mecanico";
        Assert.assertEquals(false,mock.validarDatosRegistro(id,nombre,puesto));
    }
}