package viewControlers;

import entidades.ClienteEntity;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewRegistrarAutomovil implements Initializable {

    @FXML
    public TextField matriculaTextField,marcaTextField,modeloTextField,colorTextField;
    @FXML
    public ComboBox<ClienteEntity> clienteEntityComboBox;
    @FXML
    public Button registrarButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
