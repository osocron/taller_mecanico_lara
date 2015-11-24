package cliente;

import entidades.ClienteEntity;
import entityControlers.ConexionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import javax.swing.text.View;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;

/**
 * Created by ValdemarRamos on 20/11/2015.
 */
public class ViewRegistrarClienteTest {

    public ViewRegistrarClienteTest(){
        ConexionBD.conectar();
    }

    @Test
    public void testValidarDatosRegistroConRFCIncorrecto() throws Exception {
        //Mock
        ViewRegistrarCliente mock = Mockito.mock(ViewRegistrarCliente.class);
        doNothing().when(mock).getWarningAlert(anyString(), anyString(), anyString());
        doCallRealMethod().when(mock).validarDatosRegistro(anyString(), anyString(), anyString(), anyString(),
                any(ObservableList.class));
        //Data
        List<ClienteEntity> clienteEntityList = ControladorCliente.getCliente();
        ObservableList<ClienteEntity> dataCliente = FXCollections.observableArrayList();
        dataCliente.addAll(clienteEntityList);
        //Test
        String rfc="agarh12e21";
        String nombre="valdemar";
        String domicilio="av. orizaba";
        String telefono="1234567890";
        Assert.assertEquals(false, mock.validarDatosRegistro(rfc, nombre, domicilio, telefono, dataCliente));
    }

    @Test
    public void testValidarDatosRegistroConDatosCorrectos() throws Exception {
        //Mock
        ViewRegistrarCliente mock = Mockito.mock(ViewRegistrarCliente.class);
        doNothing().when(mock).getWarningAlert(anyString(), anyString(), anyString());
        doCallRealMethod().when(mock).validarDatosRegistro(anyString(), anyString(), anyString(), anyString(),
                any(ObservableList.class));
        //Data
        List<ClienteEntity> clienteEntityList = ControladorCliente.getCliente();
        ObservableList<ClienteEntity> dataCliente = FXCollections.observableArrayList();
        dataCliente.addAll(clienteEntityList);
        //Test
        String rfc="ADMV190627FG1";
        String nombre="valdemar";
        String domicilio="av. orizaba";
        String telefono="1234567890";
        Assert.assertEquals(true,mock.validarDatosRegistro(rfc,nombre,domicilio,telefono,dataCliente));
    }

    @Test
    public void testValidarDatosRFCDuplicados() throws Exception{
        //Mock
        ViewRegistrarCliente mock = Mockito.mock(ViewRegistrarCliente.class);
        doNothing().when(mock).getWarningAlert(anyString(), anyString(), anyString());
        doCallRealMethod().when(mock).validarDatosRegistro(anyString(), anyString(), anyString(), anyString(),
                any(ObservableList.class));
        //Data
        List<ClienteEntity> clienteEntityList = ControladorCliente.getCliente();
        ObservableList<ClienteEntity> dataCliente = FXCollections.observableArrayList();
        dataCliente.addAll(clienteEntityList);
        //Test
        String rfc="agarh12e21";
        String nombre="ulises";
        String domicilio="calle pipila";
        String telefono="0987654321";
        Assert.assertEquals(false,mock.validarDatosRegistro(rfc,nombre,domicilio,telefono,dataCliente));
    }
}