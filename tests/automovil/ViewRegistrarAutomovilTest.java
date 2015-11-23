package automovil;

import entidades.AutomovilesEntity;
import entityControlers.ConexionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by ValdemarRamos on 19/11/2015.
 */
public class ViewRegistrarAutomovilTest {

    public ViewRegistrarAutomovilTest(){
        ConexionBD.conectar();
    }

    @Test
    public void testVeirificarDatosDeRegistroMatriculaIncorrecta() throws Exception {
        //Mock
        ViewRegistrarAutomovil mock = Mockito.mock(ViewRegistrarAutomovil.class);
        doNothing().when(mock).getWarningAlert(anyString(), anyString(), anyString());
        doCallRealMethod().when(mock).veirificarDatosDeRegistro(anyString(), anyString(), anyString(), anyString(),
                any(ObservableList.class));
        //Data
        List<AutomovilesEntity> automovilesEntityList = ControladorAutomovil.getAutomoviles();
        ObservableList<AutomovilesEntity> dataAutos = FXCollections.observableArrayList();
        dataAutos.addAll(automovilesEntityList);
        //Test
        String matricula = "11111";
        String modelo = "Mustang";
        String marca = "Ford";
        String color = "Azul";
        Assert.assertEquals(false, mock.veirificarDatosDeRegistro(matricula, marca, modelo, color,dataAutos));
    }

    @Test
    public void testVeirificarDatosDeRegistroConDatosCorrectos() throws Exception {
        ViewRegistrarAutomovil mock = Mockito.mock(ViewRegistrarAutomovil.class);
        doNothing().when(mock).getWarningAlert(anyString(),anyString(),anyString());
        doCallRealMethod().when(mock).veirificarDatosDeRegistro(anyString(), anyString(), anyString(), anyString(),
                any(ObservableList.class));
        //Data
        List<AutomovilesEntity> automovilesEntityList = ControladorAutomovil.getAutomoviles();
        ObservableList<AutomovilesEntity> dataAutos = FXCollections.observableArrayList();
        dataAutos.addAll(automovilesEntityList);
        //Test
        String matricula = "FDD1278";
        String modelo = "Mustang";
        String marca = "Ford";
        String color = "Azul";
        Assert.assertEquals(true, mock.veirificarDatosDeRegistro(matricula, marca, modelo, color,dataAutos));
    }
}