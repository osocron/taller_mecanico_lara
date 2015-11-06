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
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.lang.String;
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
    public ComboBox<ClienteEntity> comboBoxCliente;
    public DatePicker fechaDatePicker;
    public ListView servicioListView;
    public Button eliminarServicioButton;
    public Button agregarServicioButton;
    public TableView refaccionTableView;
    public TableColumn refaccionTableColumn;
    public TableColumn cantidadTableColumn;
    public Button eliminarRefaccionButton;
    public Button agregarRefaccionActionEvent;

    SimpleDateFormat pattern = new SimpleDateFormat("dd/MM/yyyy");

    private ObservableList<VentasEntity> data = FXCollections.observableArrayList();
    private ObservableList<ClienteEntity> dataVenta = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        java.util.List<VentasEntity> listaVentas = ControladorVentas.getVentas();
        data.addAll(listaVentas);
        comboBoxCliente.setItems(dataVenta);
    }
    public void crearVentaEvent(){

        LocalDate local = fechaDatePicker.getValue();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,local.getDayOfMonth());
        calendar.set(Calendar.MONTH,local.getMonthValue()-1);
        calendar.set(Calendar.YEAR, local.getYear());

        pattern.format((calendar.getTime()));
        java.util.Date simpleDate = calendar.getTime();
        java.sql.Date sqlDate = new java.sql.Date(simpleDate.getTime());

        ControladorVentas.guardarVenta(ControladorVentas.crearVenta(Integer.parseInt(textfieldIDventa.getText()),sqlDate,comboBoxCliente.getSelectionModel().getSelectedItem().getIdCliente()
                        ));
        Alert alert = getWarningAlert("Exitoso","Atencion","Ventas registrado exitosamente!");
        alert.showAndWait();
        textfieldIDventa.setText("");
        textfieldDescripcion.setText("");
        textfieldCantidad.setText("");
    }
    private Alert getWarningAlert(String title, String headerText, String contentText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        return alert;
    }

    public void cerrarVentanaClose(ActionEvent actionEvent) {
        Stage stage = (Stage) labelDescripcion.getScene().getWindow();
        stage.close();
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
