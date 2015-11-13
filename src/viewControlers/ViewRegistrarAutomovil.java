package viewControlers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entidades.AutomovilesEntity;
import entidades.ClienteEntity;
import entityControlers.ControladorAutomovil;
import entityControlers.ControladorCliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ViewRegistrarAutomovil implements Initializable {

    public JFXTextField matriculaTextField,marcaTextField,modeloTextField,colorTextField;
    public ComboBox<ClienteEntity> clienteEntityComboBox;
    public JFXButton registrarButton;

    private ObservableList<ClienteEntity> dataCliente = FXCollections.observableArrayList();
    private ObservableList<AutomovilesEntity> dataAtuomoviles = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        java.util.List<AutomovilesEntity> listaAutomoviles = ControladorAutomovil.getAutomoviles();
        dataAtuomoviles.addAll(listaAutomoviles);
        List<ClienteEntity> clienteEntityList = ControladorCliente.getCliente();
        dataCliente.addAll(clienteEntityList);
        clienteEntityComboBox.setItems(dataCliente);
    }

    public void registrarAutomovilActionEvent() {
        boolean isPlaca = InputValidator.isPlaca(matriculaTextField.getText());
        if ((matriculaTextField.getLength() != 0) && (marcaTextField.getLength() != 0) && (modeloTextField.getLength() != 0)
                && (colorTextField.getLength() != 0) && isPlaca) {
            ControladorAutomovil.guardarAutomovil(ControladorAutomovil.crearAutomovil(matriculaTextField.getText(),
                    marcaTextField.getText(), modeloTextField.getText(), colorTextField.getText(),
                    clienteEntityComboBox.getSelectionModel().getSelectedItem().getIdCliente()));
            Alert alert = getWarningAlert("Exitoso", "Atencion", "Automovil gregistrado exitosamente!");
            alert.showAndWait();
            matriculaTextField.setText("");
            marcaTextField.setText("");
            modeloTextField.setText("");
            colorTextField.setText("");
        }else {
            Alert alert = getWarningAlert("Cuidado", "Atencion", "Favor de verificar los datos.");
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

    public void cerrarVentanaEvent() {
        Stage stage = (Stage) matriculaTextField.getScene().getWindow();
        stage.close();
    }

    public void cancelarActionEvent(){
        clienteEntityComboBox.getSelectionModel().clearSelection();
        matriculaTextField.setText("");
        marcaTextField.setText("");
        modeloTextField.setText("");
        colorTextField.setText("");
    }

}
