package controlador;

import entidades.RefaccionEntity;
import entidades.ServicioEntity;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        java.util.List<ServicioEntity> listaServicio = ControladorServicio.getServicios();
        dataServicio.addAll(listaServicio);
        java.util.List<RefaccionEntity> listaRefaccion = ControladorRefaccion.getRefacciones();
        dataRefaccion.addAll(listaRefaccion);
    }
}
