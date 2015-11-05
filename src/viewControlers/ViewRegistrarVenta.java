package viewControlers;

import entities.ClienteEntity;
import entities.VentasEntity;
import entityControlers.ControladorVentas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by ValdemarRamos on 28/10/2015.
 */
public class ViewRegistrarVenta implements Initializable {
    @FXML
    public Label labelIDventa;
    @FXML
    public Label labelPrecio;
    @FXML
    public Label labelCantidad;
    @FXML
    public Label labelDescripcion;
    @FXML
    public Label labelTotal;
    @FXML
    public TextField textfieldIDventa;
    @FXML
    public TextField textfieldPrecio;
    @FXML
    public TextField textfieldCantidad;
    @FXML
    public TextField textfieldDescripcion;
    @FXML
    public TextField textfieldTotal;
    @FXML
    public Button buttonAceptar;
    @FXML
    public Button buttonCancelar;
    @FXML
    public TableView tableviewVenta;

    private ObservableList<VentasEntity> data = FXCollections.observableArrayList();
    private ObservableList<ClienteEntity> dataCliente = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        java.util.List<VentasEntity> listaVentas = ControladorVentas.getVentas();
        data.addAll(listaVentas);
    }
    public void crearVentaEvent(){
       /* ControladorVentas.crearVenta(textfieldIDventa.getText(), textfieldPrecio.getText(),
                textfieldCantidad.getText(), textfieldDescripcion.getText(),textfieldTotal.getText());*/
    }
}
