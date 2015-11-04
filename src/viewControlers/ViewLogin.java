package viewControlers;

import entities.UsuarioEntity;
import entityControlers.ControladorUsuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by osocron on 24/10/15.
 */
public class ViewLogin implements Initializable {

    @FXML
    public TextField usuarioTextField;
    @FXML
    public PasswordField passwordTextField;
    @FXML
    public RadioButton mantenerSesionRadioButton;

    private ObservableList<UsuarioEntity> data = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<UsuarioEntity> listaUsuarios = ControladorUsuario.getUsuarios();
        data.addAll(listaUsuarios);
    }

    public void aceptarOnActionEvent(){
        if (validarUsuario(usuarioTextField.getText(),passwordTextField.getText())){
            abrirInicio();
            Stage stage = (Stage) usuarioTextField.getScene().getWindow();
            stage.close();
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
