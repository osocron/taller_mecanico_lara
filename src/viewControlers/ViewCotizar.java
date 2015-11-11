package viewControlers;

import entidades.RefaccionEntity;
import entidades.ServicioEntity;
import entityControlers.ControladorRefaccion;
import entityControlers.ControladorServicio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by ValdemarRamos on 28/10/2015.
 */
public class ViewCotizar implements Initializable {
    @FXML
    public Label labelCliente;
    @FXML
    public Label labelRefaccion;
    @FXML
    public Label labelAutomovil;
    @FXML
    public Label labelServicio;
    @FXML
    public Label labelCosto;
    @FXML
    public Label labelFecha;
    @FXML
    public TextField textfieldCliente;
    @FXML
    public TextField textfieldAutomovil;
    @FXML
    public TextField textfieldCosto;
    @FXML
    public ComboBox comboboxRefaccion;
    @FXML
    public ComboBox comboboxServicio;
    @FXML
    public DatePicker datepickerFecha;
    @FXML
    public TableView tableviewCotizar;
    public TableColumn servicioColumn;
    public TableColumn refaccionColumn;
    public TableColumn totalColumn;

    private ObservableList<ServicioEntity> dataServicio = FXCollections.observableArrayList();
    private ObservableList<RefaccionEntity> dataRefaccion = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        obtenerDatosdeBD();
        comboboxServicio.setItems(dataServicio);
        comboboxRefaccion.setItems(dataRefaccion);
    }
    public void obtenerDatosdeBD(){
        java.util.List<ServicioEntity> listaServicio = ControladorServicio.getServicios();
        dataServicio.addAll(listaServicio);
        List<RefaccionEntity> listaRefaccion = ControladorRefaccion.getRefacciones();
        dataRefaccion.addAll(listaRefaccion);
    }
    public void prepararTabla(){
        tableviewCotizar.setEditable(true);
        servicioColumn.setCellFactory(new PropertyValueFactory<>("servicio"));
        refaccionColumn.setCellFactory(new PropertyValueFactory<>("refaccion"));
        totalColumn.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>() {
            @Override
            public String toString(Integer object) {
                return String.valueOf(object);
            }

            @Override
            public Integer fromString(String string) {
                return Integer.parseInt(string);
            }
        }));
    }
    public void crearCotizacionEvent(){
        servicioColumn.getColumns().add(dataServicio);
        refaccionColumn.getColumns().add(dataRefaccion);
    }

    public void cerrarVentanaEvent(ActionEvent actionEvent) {
        Stage stage = (Stage) textfieldAutomovil.getScene().getWindow();
        stage.close();
    }
}
