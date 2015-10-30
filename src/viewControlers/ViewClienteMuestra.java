package viewControlers;

import entidades.ClienteEntity;
import entityControlers.ControladorCliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by ValdemarRamos on 28/10/2015.
 */
public class ViewClienteMuestra implements Initializable{
    @FXML
    private BorderPane bordePane;
    @FXML
    public Label labelIDautomovil;
    @FXML
    public Label labelNombre;
    @FXML
    public Label labelDomicilio;
    @FXML
    public Label labelTelefono;
    @FXML
    public Label labelIDcliente;
    @FXML
    public TextField textfieldIDautomovil;
    @FXML
    public TextField textfieldNombre;
    @FXML
    public TextField textfieldDomicilio;
    @FXML
    public TextField textfieldTelefono;
    @FXML
    public ComboBox comboboxIDcliente;
    @FXML
    public Button buttonConsultar;
    @FXML
    public Button buttonAtras;
    @FXML
    private VBox vBox;

    private ObservableList<ClienteEntity> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        java.util.List<ClienteEntity> listaClientes = ControladorCliente.getCliente();
        data.addAll(listaClientes);
    }
}
