package viewControlers;

import entidades.VentaRefaccionEntity;
import entidades.VentaServicioEntity;
import entidades.VentasEntity;
import entityControlers.ControladorVentas;
import entityControlers.ControladorVentasRefaccion;
import entityControlers.ControladorVentasServicio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by ValdemarRamos on 28/10/2015.
 */
public class ViewReporte implements Initializable{
    @FXML
    public Label labelFolio;
    @FXML
    public TextField textfieldReporte;
    @FXML
    public Button buttonBuscar;
    @FXML
    public Button buttonAtras;
    @FXML
    public Button buttonImprimir;
    @FXML
    public ComboBox comboboxReporte;
    @FXML
    public TableView tableviewReporte;

    private ObservableList<VentasEntity> dataVentas = FXCollections.observableArrayList();
    private ObservableList<VentaServicioEntity> dataVentaServicio = FXCollections.observableArrayList();
    private ObservableList<VentaRefaccionEntity> dataVentaRefaccion = FXCollections.observableArrayList();
    private DatePicker fecha;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        java.util.List<VentasEntity> listaVenta = ControladorVentas.getVentas();
        dataVentas.addAll(listaVenta);
        java.util.List<VentaRefaccionEntity>listaVentaRefaccion = FXCollections.observableArrayList();
        dataVentaRefaccion.addAll(listaVentaRefaccion);
        java.util.List<VentaServicioEntity> listaVentaServicio = FXCollections.observableArrayList();
        dataVentaServicio.addAll(listaVentaServicio);
        //comboboxReporte.setItems(fecha);
    }
    public void tablaReporteEvent(){
        tableviewReporte.setEditable(true);
    }
    public void crearReporteEvent(){
        ControladorVentas.getVentas();
        ControladorVentasRefaccion.getVentasRefaccion();
        ControladorVentasServicio.getVentaServicio();
    }
    public void cerrarVentanaEvent(){
        Stage stage = (Stage) textfieldReporte.getScene().getWindow();
        stage.close();
    }
}
