package usuario;

import entidades.UsuarioEntity;
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

/**
 * Created by ValdemarRamos on 22/11/2015.
 */
public class ControladorUsuarioTest {
    public ControladorUsuarioTest(){
        ConexionBD.conectar();
    }

    @Test
    public void testVerificarDatosCorrectos() throws Exception {
        ControladorUsuario mock = Mockito.mock(ControladorUsuario.class);
        doCallRealMethod().when(mock).verificarDatos(anyString(), anyString(),
                any(ObservableList.class));

        //data
        List<UsuarioEntity> usuarioEntityList = ControladorUsuario.getUsuarios();
        ObservableList<UsuarioEntity> dataUsuario = FXCollections.observableArrayList();
        dataUsuario.addAll(usuarioEntityList);

        String nombre="valdemar";
        String idEmpleado="3";
        Assert.assertEquals(true,mock.verificarDatos(nombre,idEmpleado,dataUsuario));
    }
    @Test
    public void testVerificarDatosIncorrectos() throws Exception{
        ControladorUsuario mock = Mockito.mock(ControladorUsuario.class);
        doCallRealMethod().when(mock).verificarDatos(anyString(), anyString(),
                any(ObservableList.class));

        //data
        //data
        List<UsuarioEntity> usuarioEntityList = ControladorUsuario.getUsuarios();
        ObservableList<UsuarioEntity> dataUsuario = FXCollections.observableArrayList();
        dataUsuario.addAll(usuarioEntityList);

        String nombre = "juan carlos";
        String idEmpleado ="";
        Assert.assertEquals(false,mock.verificarDatos(nombre,idEmpleado,dataUsuario));
    }
}