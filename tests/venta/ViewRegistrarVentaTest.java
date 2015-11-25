package venta;

import cliente.ControladorCliente;
import cliente.ViewRegistrarCliente;
import entidades.ClienteEntity;
import entidades.VentasEntity;
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
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;

/**
 * Created by ValdemarRamos on 22/11/2015.
 */
public class ViewRegistrarVentaTest {
    public ViewRegistrarVentaTest(){
        ConexionBD.conectar();
    }

    @Test
    public void testVerificarDatosVentaCorrecta() throws Exception {
        ViewRegistrarVenta mock = Mockito.mock(ViewRegistrarVenta.class);
        doNothing().when(mock).getWarningAlert(anyString(),anyString(),anyString());
        doCallRealMethod().when(mock).verificarDatosVenta(anyString(),anyString(),any(ObservableList.class));

        //data
        List<ClienteEntity> clienteEntityList = ControladorCliente.getCliente();
        ObservableList<ClienteEntity>dataCliente = FXCollections.observableArrayList();
        dataCliente.addAll(clienteEntityList);

        String idVenta="3";
        String idCliente="AFFG123411";
        Assert.assertEquals(false, mock.verificarDatosVenta(idVenta, idCliente,dataCliente ));
    }
    @Test
    public void testVerificarDatosVentaIDclienteIncorrecta() throws Exception{
        ViewRegistrarVenta mock = Mockito.mock(ViewRegistrarVenta.class);
        doNothing().when(mock).getWarningAlert(anyString(),anyString(),anyString());
        doCallRealMethod().when(mock).verificarDatosVenta(anyString(),anyString(),any(ObservableList.class));

        //data
        List<ClienteEntity> clienteEntityList = ControladorCliente.getCliente();
        ObservableList<ClienteEntity>dataCliente = FXCollections.observableArrayList();
        dataCliente.addAll(clienteEntityList);

        String idVenta="1";
        String idCliente="3";
        Assert.assertEquals(true,mock.verificarDatosVenta(idVenta,idCliente,dataCliente));
    }
}