package viewControlers;

import entidades.VentasEntity;
import entityControlers.ControladorVentas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by osocron on 29/10/15.
 */
public class ViewConsultarVentas implements Initializable {

    public TableView<VentasEntity> tablaVentas;
    public MenuItem menuItemClose;
    public TableColumn idVentaTableColumn;
    public TableColumn fechaTablecolumn;
    public TableColumn clienteTableColumn;
    public Button buttonEliminar;
    public Button buttonModificar;
    public Button buttonGuardar;


    private ObservableList<VentasEntity> dataVenta = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        java.util.List<VentasEntity> listaVentas = ControladorVentas.getVentas();
        dataVenta.addAll(listaVentas);
    }

    public void eliminarVentaEvent(){

    }

    public void modificarVentaEvent(){

    }

    public void guardarVentaEvent() {

    }

    public void cerrarVentanaEvent() {
        Stage stage = (Stage) tablaVentas.getScene().getWindow();
        stage.close();
    }

    public void cancelarActionEvent(){

    }

}
