package cliente;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entidades.ClienteEntity;
import cliente.ControladorCliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import viewControlers.InputValidator;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by ValdemarRamos on 28/10/2015.
 */
public class ViewRegistrarCliente implements Initializable {
    @FXML
    public Label labelNombre;
    @FXML
    public Label labelRFC;
    @FXML
    public Label labelDomicilio;
    @FXML
    public Label labelTelefono;
    @FXML
    public JFXTextField textfieldNombre;
    @FXML
    public JFXTextField textfieldRFC;
    @FXML
    public JFXTextField textfieldDomicilio;
    @FXML
    private JFXTextField textfieldTelefono;
    @FXML
    public JFXButton buttonAceptar;
    @FXML
    public JFXButton buttonCancelar;

    private ObservableList<ClienteEntity> dataCliente = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        java.util.List<ClienteEntity> listaClientes = ControladorCliente.getCliente();
        dataCliente.addAll(listaClientes);
    }

    public void guardarClienteActionEvent() {
        if (validarDatosRegistro(textfieldRFC.getText(),textfieldNombre.getText(),textfieldDomicilio.getText(),
                textfieldTelefono.getText())) {
            ControladorCliente.guardarCliente(ControladorCliente.crearCliente(textfieldRFC.getText(),
                    textfieldNombre.getText(), textfieldDomicilio.getText(),
                    textfieldTelefono.getText()));
            Alert alert = getWarningAlert("Exitoso", "Atencion", "Cliente registrado exitosamente!");
            alert.showAndWait();
            textfieldRFC.setText("");
            textfieldNombre.setText("");
            textfieldDomicilio.setText("");
            textfieldTelefono.setText("");
        }
    }

    public boolean validarDatosRegistro(String rfc, String nombre, String domicilio, String telefono){
        boolean telefonoEsNumerico = InputValidator.textIsNumericOnly(telefono);
        boolean isRFC = InputValidator.isRFC(rfc);
        if ((rfc.length() != 0) && (nombre.length() != 0) &&
                (domicilio.length() != 0) && (telefono.length() != 0) &&
                telefonoEsNumerico && isRFC && (telefono.length() == 10)) {
            final boolean[] isRepetido = {false};
            dataCliente.forEach(clienteEntity -> {
                if (clienteEntity.getIdCliente().equals(rfc)) {
                    isRepetido[0] = true;
                }
            });
            if (!isRepetido[0]) {
                return true;
            }else {
                Alert alert = getWarningAlert("Cuidado", "Atencion", "El RFC ya existe en la base de datos!");
                alert.showAndWait();
                return false;
            }
        }else {
            Alert alert = getWarningAlert("Cuidado", "¡Atencion!", "Agunos datos no son correctos\n o están incompletos.");
            alert.showAndWait();
            return false;
        }
    }

    public void cerrarVentanaEvent() {
        Stage stage = (Stage) labelDomicilio.getScene().getWindow();
        stage.close();
    }

    public void cancelarActionEvent() {
        cerrarVentanaEvent();
    }

    private Alert getWarningAlert(String title, String headerText, String contentText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        return alert;
    }

}
