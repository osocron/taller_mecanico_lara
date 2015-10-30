package viewControlers;

import entidades.UsuarioEntity;
import entityControlers.ControladorUsuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by osocron on 24/10/15.
 */
public class ViewLogin implements Initializable {

    @FXML
    private TextField usuarioTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private RadioButton mantenerSesionRadioButton;

    private ObservableList<UsuarioEntity> data = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<UsuarioEntity> listaUsuarios = ControladorUsuario.getUsuarios();
        data.addAll(listaUsuarios);
    }

    public void aceptarOnActionEvent(){
        if (validarUsuario(usuarioTextField.getText(),passwordTextField.getText())){
            abrirInicio();
        }
    }

    private void abrirInicio() {
        ViewOpener viewOpener = new ViewOpener();
        viewOpener.openView("vista/Inicio.fxml", "Bienvenido al Sistema");
    }

    private boolean validarUsuario(String nombreUsuario, String contrasena){
        final boolean[] res = {false};
        data.forEach(usuarioEntity -> {
            if (nombreUsuario.equals(usuarioEntity.getNombre()) && contrasena.equals(usuarioEntity.getContrasena()))
                res[0] = true;
        });
        return res[0];
    }
}
