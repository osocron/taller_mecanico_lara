package viewControlers;

import entidades.UsuarioEntity;
import entityControlers.ConexionBD;
import entityControlers.ControladorUsuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Assert;
import org.junit.Test;
import java.util.List;

/**
 * Created by osocron on 18/11/15.
 */
public class ViewLoginTest {

    public ViewLoginTest(){
        ConexionBD.conectar();
    }

    @Test
    public void testValidarLoginConDatosCorrectos() throws Exception {
        ViewLogin viewLogin = new ViewLogin();
        List<UsuarioEntity> usuarioEntityList = ControladorUsuario.getUsuarios();
        ObservableList<UsuarioEntity> usuarioEntityObservableList = FXCollections.observableArrayList();
        usuarioEntityObservableList.addAll(usuarioEntityList);
        String user = "taller";
        String passwd = "lara";
        Assert.assertEquals(true,viewLogin.validarUsuario(user,passwd,usuarioEntityObservableList));
    }

    @Test
    public void testValidarLoginConDatosIncorrectos() throws Exception {
        ViewLogin viewLogin = new ViewLogin();
        List<UsuarioEntity> usuarioEntityList = ControladorUsuario.getUsuarios();
        ObservableList<UsuarioEntity> usuarioEntityObservableList = FXCollections.observableArrayList();
        usuarioEntityObservableList.addAll(usuarioEntityList);
        String user = "taller";
        String passwd = "mecanico";
        Assert.assertEquals(false,viewLogin.validarUsuario(user,passwd,usuarioEntityObservableList));
    }


}