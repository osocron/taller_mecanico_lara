package viewControlers;

import entities.VentasEntity;
import entityControlers.ControladorVentas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
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
    @FXML
    public TableColumn idVenta,fecha,cliente;


    private ObservableList<VentasEntity> dataVenta = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        java.util.List<VentasEntity> listaVentas = ControladorVentas.getVentas();
        dataVenta.addAll(listaVentas);
    }
    public void eliminarVentaEvent(){
        ControladorVentas.eliminarVenta(busquedaTextField.hashCode());
    }
    public void buscarVentaEvent(){

    }
    public void modificarVentaEvent(){
        //ControladorVentas.modificarVenta(idVenta.hashCode(),fecha.format,cliente.getText());
        idVenta.hashCode();
        cliente.setText("");
        fecha.setText("");
    }
    public void guardarVentaEvent(){
        //ControladorVentas.guardarVenta(ControladorVentas.crearVenta(idVenta.hashCode(),fecha.getUserData(),cliente.getText()));
        idVenta.setText("");
        cliente.setText("");
        fecha.setText("");
    }
}
