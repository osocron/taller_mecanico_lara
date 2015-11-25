package refaccion;

import entidades.RefaccionEntity;
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
 * Created by ValdemarRamos on 20/11/2015.
 */
public class ViewRegistrarRefaccionTest {
    public ViewRegistrarRefaccionTest(){
        ConexionBD.conectar();
    }

    @Test
    public void testVerificarDatosRegistroRefaccionCorrectos() throws Exception {
        ViewRegistrarRefaccion mock = Mockito.mock(ViewRegistrarRefaccion.class);
        doCallRealMethod().when(mock).verificarDatosRegistro(anyString(),anyString(),anyString(),any(ObservableList.class));

        //data
        List<RefaccionEntity> refaccionEntityList = ControladorRefaccion.getRefacciones();
        ObservableList<RefaccionEntity> dataRefaccion = FXCollections.observableArrayList();
        dataRefaccion.addAll(refaccionEntityList);

        String descripcion = "barato";
        String precio ="10";
        String cantidad ="2";
        Assert.assertEquals(true,mock.verificarDatosRegistro(descripcion,precio,cantidad,dataRefaccion));
    }
    @Test
    public void testVerificarDatosRegistrosRefaccionesIncorrectos() throws Exception{
        ViewRegistrarRefaccion mock = Mockito.mock(ViewRegistrarRefaccion.class);
        doCallRealMethod().when(mock).verificarDatosRegistro(anyString(),anyString(),anyString(),any(ObservableList.class));

        //data
        List<RefaccionEntity> refaccionEntityList = ControladorRefaccion.getRefacciones();
        ObservableList<RefaccionEntity> dataRefaccion = FXCollections.observableArrayList();
        dataRefaccion.addAll(refaccionEntityList);

        String descripcion = "barato";
        String precio ="qqqw";
        String cantidad ="ewwww";
        Assert.assertEquals(false,mock.verificarDatosRegistro(descripcion,precio,cantidad,dataRefaccion));
    }
}