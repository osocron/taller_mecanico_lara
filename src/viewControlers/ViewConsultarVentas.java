package viewControlers;

import entidades.VentasEntity;
import entityControlers.ControladorVentas;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    public TableColumn idVenta;
    @FXML
    public TableColumn cliente;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void guardarVentaActionEvent() {
        //ControladorVentas.guardarVenta(ControladorVentas.guardarVenta(idVenta.getTableView(),cliente.getTableView()));
        idVenta.setText("");
        cliente.setText("");
    }
    public void eliminarVentaActionEvent(){
        //ControladorVentas.eliminarVenta(ControladorVentas.eliminarVenta(idVenta.getTableView()));
        idVenta.setText("");
    }
    public void modificarVentaActionEvent(){
        //ControladorVentas.modificarVenta(ControladorVentas.modificarVenta(idVenta.getTableView(),cliente.getText()));
        idVenta.setText("");
        cliente.setText("");
    }
}
