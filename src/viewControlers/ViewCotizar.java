package viewControlers;

import entidades.ClienteEntity;
import entidades.RefaccionEntity;
import entidades.ServicioEntity;
import entityControlers.ControladorAutomovil;
import entityControlers.ControladorCliente;
import entityControlers.ControladorRefaccion;
import entityControlers.ControladorServicio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
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

    private ObservableList<ServicioEntity> dataServicio = FXCollections.observableArrayList();
    private ObservableList<RefaccionEntity> dataRefaccion = FXCollections.observableArrayList();
    private ObservableList<ClienteEntity> dataCliente = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        java.util.List<ServicioEntity> listaServicio = ControladorServicio.getServicios();
        dataServicio.addAll(listaServicio);
        java.util.List<RefaccionEntity> listaRefaccion = ControladorRefaccion.getRefacciones();
        dataRefaccion.addAll(listaRefaccion);
    }
    public void guardarCotizacionEvent() {
        /*ControladorCliente.guardarCliente(ControladorCliente.crearCliente(textfieldCliente.getText()));
        ControladorRefaccion.guardarRefaccion(ControladorRefaccion.crearRefaccion(comboboxRefaccion.getOnAction()));
        ControladorServicio.guardarServicio(ControladorServicio.crearServicio(comboboxServicio.getOnAction()));
        ControladorAutomovil.guardarAutomovil(ControladorAutomovil.crearAutomovil(textfieldAutomovil.getText()));
        textfieldCliente.setText("");
        textfieldAutomovil.setText("");
        comboboxRefaccion.setOnAction(comboboxRefaccion);
        comboboxServicio.setOnAction(comboboxServicio);*/
    }
}
