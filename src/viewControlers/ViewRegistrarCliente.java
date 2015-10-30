package viewControlers;

import entidades.ClienteAutomovilEntity;
import entityControlers.ControladorClienteAutomovil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.awt.*;
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
    public Label labelIDautomovil;
    @FXML
    public TextField textfieldNombre;
    @FXML
    public TextField textfieldRFC;
    @FXML
    public TextField textfieldDomicilio;
    @FXML
    public TextField textfieldTelefono;
    @FXML
    public TextField textfieldIDautomovil;
    @FXML
    public Button buttonAceptar;
    @FXML
    public Button buttonCancelar;

    private ObservableList<ClienteAutomovilEntity> dataClienteAuto = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        java.util.List<ClienteAutomovilEntity> listaClienteAuto = ControladorClienteAutomovil.getAutoCliente();
        dataClienteAuto.addAll(listaClienteAuto);
    }
}
