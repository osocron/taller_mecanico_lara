package viewControlers;

import entidades.ServicioEntity;
import entityControlers.ControladorServicio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by ValdemarRamos on 28/10/2015.
 */
public class ViewRegistrarServicio implements Initializable {
    @FXML
    public Label labelIDservicio;
    @FXML
    public Label labelServicio;
    @FXML
    public Label labelDescripcion;
    @FXML
    public Label labelIDempleado;
    @FXML
    public Label labelPrecio;
    @FXML
    public Label labelFecha;
    @FXML
    public TextField textfieldServicio;
    @FXML
    public TextField textfieldDescripcion;
    @FXML
    public ComboBox comboboxServicio;
    @FXML
    public TextField textfieldIDempleado;
    @FXML
    public TextField textfieldPrecio;
    @FXML
    public DatePicker datepickerFecha;
    @FXML
    public Button buttonAceptar;
    @FXML
    public Button buttonCancelar;

    private ObservableList<ServicioEntity> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        java.util.List<ServicioEntity> listaServicio = ControladorServicio.getServicios();
        data.addAll(listaServicio);
    }
}
