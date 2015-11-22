package viewControlers;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import entidades.UsuarioEntity;
import javafx.scene.control.Alert;
import usuario.ControladorUsuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by osocron on 24/10/15.
 */
public class ViewLogin implements Initializable {

    @FXML
    public JFXTextField usuarioTextField;
    @FXML
    public JFXPasswordField passwordTextField;


    private ObservableList<UsuarioEntity> data = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<UsuarioEntity> listaUsuarios = ControladorUsuario.getUsuarios();
        data.addAll(listaUsuarios);
    }

    public void aceptarOnActionEvent(){
        if (validarUsuario(usuarioTextField.getText(),passwordTextField.getText(),this.data)){
            abrirInicio();
            Stage stage = (Stage) usuarioTextField.getScene().getWindow();
            stage.close();
        }else{
            Alert alert = getWarningAlert("Alerta", "Incorrecto", "Datos incorrectos");
            alert.showAndWait();
        }
    }

    private Alert getWarningAlert(String title, String headerText, String contentText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        return alert;
    }

    private void abrirInicio() {
        ViewOpener viewOpener = new ViewOpener();
        viewOpener.openView("vista/Inicio.fxml", "Bienvenido al Sistema");
    }

    public boolean validarUsuario(String nombreUsuario, String contrasena, ObservableList<UsuarioEntity> data){
        final boolean[] res = {false};
        data.forEach(usuarioEntity -> {
            if (nombreUsuario.equals(usuarioEntity.getNombre()) && contrasena.equals(usuarioEntity.getContrasena()))
                res[0] = true;
        });
        return res[0];
    }
}
