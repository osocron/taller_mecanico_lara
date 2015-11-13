package viewControlers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entidades.ClienteEntity;
import entityControlers.ControladorCliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
        boolean telefonoEsNumerico = InputValidator.textIsNumericOnly(textfieldTelefono.getText());
        boolean isRFC = InputValidator.isRFC(textfieldRFC.getText());
        if ((textfieldRFC.getLength() != 0) && (textfieldNombre.getLength() != 0) &&
                (textfieldDomicilio.getLength() != 0) && (textfieldTelefono.getLength() != 0) &&
                telefonoEsNumerico && isRFC && (textfieldTelefono.getLength() == 10)) {
            ControladorCliente.guardarCliente(ControladorCliente.crearCliente(textfieldRFC.getText(),
                    textfieldNombre.getText(), textfieldDomicilio.getText(),
                    textfieldTelefono.getText()));
            Alert alert = getWarningAlert("Exitoso", "Atencion", "Cliente registrado exitosamente!");
            alert.showAndWait();
            textfieldRFC.setText("");
            textfieldNombre.setText("");
            textfieldDomicilio.setText("");
            textfieldTelefono.setText("");
        }else {
            Alert alert = getWarningAlert("Cuidado", "¡Atencion!", "Agunos datos no son correctos\n o están incompletos.");
            alert.showAndWait();
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
