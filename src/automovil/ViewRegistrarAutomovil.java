package automovil;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entidades.AutomovilesEntity;
import entidades.ClienteEntity;
import automovil.ControladorAutomovil;
import cliente.ControladorCliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import viewControlers.InputValidator;

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
        prepareData();
    }

    private void prepareData() {
        java.util.List<AutomovilesEntity> listaAutomoviles = ControladorAutomovil.getAutomoviles();
        dataAtuomoviles.addAll(listaAutomoviles);
        List<ClienteEntity> clienteEntityList = ControladorCliente.getCliente();
        dataCliente.addAll(clienteEntityList);
        clienteEntityComboBox.setItems(dataCliente);
    }

    public void registrarAutomovilActionEvent() {
        if (veirificarDatosDeRegistro(matriculaTextField.getText(),marcaTextField.getText(),modeloTextField.getText(),
                colorTextField.getText(),dataAtuomoviles) && (clienteEntityComboBox.getSelectionModel().getSelectedIndex() >= 0)) {
            ControladorAutomovil.guardarAutomovil(ControladorAutomovil.crearAutomovil(matriculaTextField.getText(),
                    marcaTextField.getText(), modeloTextField.getText(), colorTextField.getText(),
                    clienteEntityComboBox.getSelectionModel().getSelectedItem().getIdCliente()));
            getWarningAlert("Exitoso", "Atencion", "Automovil registrado exitosamente!");
            matriculaTextField.setText("");
            marcaTextField.setText("");
            modeloTextField.setText("");
            colorTextField.setText("");
        }
    }

    public boolean veirificarDatosDeRegistro(String matricula, String marca, String modelo, String color,
                                             ObservableList<AutomovilesEntity> dataAtuomoviles){
        boolean isPlaca = InputValidator.isPlaca(matricula);
        if ((matricula.length() != 0) && (marca.length() != 0) && (modelo.length() != 0)
                && (color.length() != 0) && isPlaca) {
            final boolean[] isRepetido = {false};
            dataAtuomoviles.forEach(automovilesEntity -> {
                if (automovilesEntity.getMatricula().equals(matricula)){
                    isRepetido[0] = true;
                }
            });
            if (!isRepetido[0]) {
                return true;
            }else {
                getWarningAlert("Cuidado", "Atencion", "La matricula ya existe en la base de datos!");
                return false;
            }
        }else {
            getWarningAlert("Cuidado", "Atencion", "Favor de verificar los datos.");
            return false;
        }
    }

    public void getWarningAlert(String title, String headerText, String contentText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
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
