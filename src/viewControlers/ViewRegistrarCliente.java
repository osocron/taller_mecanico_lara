package viewControlers;

import entidades.ClienteEntity;
import entityControlers.ControladorCliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
    public TextField textfieldNombre;
    @FXML
    public TextField textfieldRFC;
    @FXML
    public TextField textfieldDomicilio;
    @FXML
    public TextField textfieldTelefono;
    @FXML
    public Button buttonAceptar;
    @FXML
    public Button buttonCancelar;

    private ObservableList<ClienteEntity> dataCliente = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        java.util.List<ClienteEntity> listaClientes = ControladorCliente.getCliente();
        dataCliente.addAll(listaClientes);
    }

    public void guardarClienteActionEvent() {
        ControladorCliente.guardarCliente(ControladorCliente.crearCliente(textfieldRFC.getText(),
                textfieldNombre.getText(), textfieldDomicilio.getText(),
                textfieldTelefono.getText()));
        Alert alert = getWarningAlert("Exitoso","Atencion","Cliente gregistrado exitosamente!");
        alert.showAndWait();
        textfieldRFC.setText("");
        textfieldNombre.setText("");
        textfieldDomicilio.setText("");
        textfieldTelefono.setText("");
    }

    private Alert getWarningAlert(String title, String headerText, String contentText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        return alert;
    }

}
