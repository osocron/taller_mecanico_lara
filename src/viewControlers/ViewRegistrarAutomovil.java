package viewControlers;

import entidades.AutomovilesEntity;
import entidades.ClienteEntity;
import entityControlers.ControladorAutomovil;
import entityControlers.ControladorCliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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

    private ObservableList<ClienteEntity> dataCliente = FXCollections.observableArrayList();
    private ObservableList<AutomovilesEntity> dataAtuomoviles = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        java.util.List<AutomovilesEntity> listaAutomoviles = ControladorAutomovil.getAutomoviles();
        dataAtuomoviles.addAll(listaAutomoviles);
    }
    public void guardarAutomovilEvent(){
        /*ControladorAutomovil.guardarAutomovil(ControladorAutomovil.crearUsuario(matriculaTextField.getText(),marcaTextField.getText(),
                modeloTextField.getText(),colorTextField.getText()));*/
    }


    public void registrarAutomovilActionEvent() {
        ControladorAutomovil.guardarAutomovil(ControladorAutomovil.crearAutomovil(matriculaTextField.getText(),
                marcaTextField.getText(),modeloTextField.getText(), colorTextField.getText(),
                clienteEntityComboBox.getSelectionModel().getSelectedItem().getIdCliente()));
        Alert alert = getWarningAlert("Exitoso","Atencion","Automovil gregistrado exitosamente!");
        alert.showAndWait();
        matriculaTextField.setText("");
        marcaTextField.setText("");
        modeloTextField.setText("");
        colorTextField.setText("");
    }

    private Alert getWarningAlert(String title, String headerText, String contentText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        return alert;
    }
}
