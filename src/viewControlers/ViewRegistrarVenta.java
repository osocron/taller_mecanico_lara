package viewControlers;

import entidades.ClienteEntity;
import entidades.VentasEntity;
import entityControlers.ControladorVentas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by ValdemarRamos on 28/10/2015.
 */
public class ViewRegistrarVenta implements Initializable {
    @FXML
    public Label labelIDventa;
    @FXML
    public Label labelPrecio;
    @FXML
    public Label labelCantidad;
    @FXML
    public Label labelDescripcion;
    @FXML
    public Label labelTotal;
    @FXML
    public TextField textfieldIDventa;
    @FXML
    public TextField textfieldPrecio;
    @FXML
    public TextField textfieldCantidad;
    @FXML
    public TextField textfieldDescripcion;
    @FXML
    public TextField textfieldTotal;
    @FXML
    public Button buttonAceptar;
    @FXML
    public Button buttonCancelar;
    @FXML
    public TableView tableviewVenta;
    public MenuItem menuItemClose;
    public ComboBox comboBoxCliente;
    public DatePicker fechaDatePicker;
    public ListView servicioListView;
    public Button eliminarServicioButton;
    public Button agregarServicioButton;
    public TableView refaccionTableView;
    public TableColumn refaccionTableColumn;
    public TableColumn cantidadTableColumn;
    public Button eliminarRefaccionButton;
    public Button agregarRefaccionActionEvent;

    private ObservableList<VentasEntity> data = FXCollections.observableArrayList();
    private ObservableList<ClienteEntity> dataCliente = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        java.util.List<VentasEntity> listaVentas = ControladorVentas.getVentas();
        data.addAll(listaVentas);
    }
    public void crearVentaEvent(){
        textfieldIDventa.setText("");
        textfieldDescripcion.setText("");
        textfieldCantidad.setText("");
    }

    public void cerrarVentanaClose(ActionEvent actionEvent) {

    }

    public void eliminarServicioActionEvent(ActionEvent actionEvent) {

    }

    public void agregarServicioActionEvent(ActionEvent actionEvent) {

    }

    public void eliminarRefaccionActionEvent(ActionEvent actionEvent) {

    }

    public void agregarRefaccionActionEvent(ActionEvent actionEvent) {

    }

    public void cancelarActionEvent(ActionEvent actionEvent) {

    }
}
