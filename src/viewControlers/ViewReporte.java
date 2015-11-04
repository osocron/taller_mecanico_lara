package viewControlers;

import entities.VentaRefaccionEntity;
import entities.VentaServicioEntity;
import entities.VentasEntity;
import entityControlers.ControladorVentas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        java.util.List<VentasEntity> listaVenta = ControladorVentas.getVentas();
        dataVentas.addAll(listaVenta);
    }
}
