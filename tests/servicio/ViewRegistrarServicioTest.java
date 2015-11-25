package servicio;

import entidades.ServicioEntity;
import entityControlers.ConexionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;

/**
 * Created by ValdemarRamos on 21/11/2015.
 */
public class ViewRegistrarServicioTest {

    public ViewRegistrarServicioTest(){
        ConexionBD.conectar();
    }

    @Test
    public void testVerificarDatosRegistro() throws Exception {
        //clase mocki
        ViewRegistrarServicio mock = Mockito.mock(ViewRegistrarServicio.class);
        doCallRealMethod().when(mock).verificarDatosRegistro(anyString(), anyString(), anyString(),
                any(ObservableList.class));

        //data
        List<ServicioEntity> servicioEntityList = ControladorServicio.getServicios();
        ObservableList<ServicioEntity> dataServicio = FXCollections.observableArrayList();
        dataServicio.addAll(servicioEntityList);

        String descripcion="muy caro";
        String costo="20";
        String idEmpleados="2";
        Assert.assertEquals(false,mock.verificarDatosRegistro(descripcion,costo,idEmpleados,dataServicio));
    }
    @Test
    public void testVerificarDatosIncompletos() throws Exception{

        ViewRegistrarServicio mock = Mockito.mock(ViewRegistrarServicio.class);
        doCallRealMethod().when(mock).verificarDatosRegistro(anyString(),anyString(),anyString(),any(ObservableList.class));

        //data
        List<ServicioEntity> servicioEntityList = ControladorServicio.getServicios();
        ObservableList<ServicioEntity> dataServicio =FXCollections.observableArrayList();
        dataServicio.addAll(servicioEntityList);

        String descripcion="";
        String costo="";
        String idEmpleados="2";
        Assert.assertEquals(false,mock.verificarDatosRegistro(descripcion,costo,idEmpleados,dataServicio));
    }
    @Test
    public void testVerificarDatosDuplicados() throws Exception{
        ViewRegistrarServicio mock = Mockito.mock(ViewRegistrarServicio.class);
        doCallRealMethod().when(mock).verificarDatosRegistro(anyString(),anyString(),anyString(),any(ObservableList.class));

        //data
        List<ServicioEntity> servicioEntityList = ControladorServicio.getServicios();
        ObservableList<ServicioEntity> dataServicio = FXCollections.observableArrayList();
        dataServicio.addAll(servicioEntityList);

        String descripcion="Cambio de Aceite Aceite negro";
        String costo="20";
        String idEmpleados="1";
        Assert.assertEquals(true,mock.verificarDatosRegistro(descripcion,costo,idEmpleados,dataServicio));
    }
}