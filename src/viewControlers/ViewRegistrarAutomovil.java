package viewControlers;

import entidades.ClienteEntity;
import entityControlers.ControladorAutomovil;
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
    public ComboBox<ClienteEntity> clienteEntityComboBox;
    public Button registrarButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void crearAutomovilEvent(){
        /*ControladorAutomovil.crearAutomovil(ControladorAutomovil.crearAutomovil(matriculaTextField.getAnchor(),
                marcaTextField.getText(),modeloTextField.getText(),colorTextField.getText(),clienteEntityComboBox.getId()));*/
        marcaTextField.setText("");
        matriculaTextField.setText("");
        modeloTextField.setText("");
        colorTextField.setText("");
        //clienteEntityComboBox.setOnAction();
    }
}
