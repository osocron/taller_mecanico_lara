package viewControlers;

import entidades.VentasEntity;
import entityControlers.ControladorVentas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by osocron on 29/10/15.
 */
public class ViewConsultarVentas implements Initializable {

    @FXML
    public TextField busquedaTextField;
    @FXML
    public TableView<VentasEntity> tablaVentas;
    @FXML
    public Button buscarButton;


    private ObservableList<VentasEntity> dataVenta = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        java.util.List<VentasEntity> listaVentas = ControladorVentas.getVentas();
        dataVenta.addAll(listaVentas);
    }
    public void eliminarVentaEvent(){
        ControladorVentas.eliminarVenta(Integer.parseInt(busquedaTextField.getText()));
    }
    public void buscarVentaEvent(){

    }
    public void modificarVentaEvent(){

    }

    public void guardarVentaEvent(ActionEvent actionEvent) {

    }
}
