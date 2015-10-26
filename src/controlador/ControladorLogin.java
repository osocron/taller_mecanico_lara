package controlador;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by osocron on 24/10/15.
 */
public class ControladorLogin extends Application {

    @FXML
    private TextField usuarioTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Button aceptarButton;
    @FXML
    private RadioButton mantenerSesionRadioButton;

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    private void validarUsuarioContraseña(){

    }

}
