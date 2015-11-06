package viewControlers;

import entities.ServicioEntity;
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
public class ViewServicioMuestra implements Initializable {
    @FXML
    public TableView tableviewServicio;
    @FXML
    public Label labelServicio;
    @FXML
    public TextField textfieldServicio;
    @FXML
    public Button buttonBuscar;
    @FXML
    public Button buttonCotizar;
    @FXML
    public Button buttonAtras;
    @FXML
    public Button buttonAgregar;
    @FXML
    public TableColumn idServicio;
    @FXML
    public TableColumn servicio;
    @FXML
    public TableColumn descripcion;
    @FXML
    public TableColumn costo;
    @FXML
    public TableColumn idEmpleado;
    @FXML
    public TableColumn total;

    private ObservableList<ServicioEntity> dataServicio = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        java.util.List<ServicioEntity> listaServicio = ControladorServicio.getServicios();
        dataServicio.addAll(listaServicio);
    }
    public void guardarServicioEvent(){
        //ControladorServicio.guardarServicio(ControladorServicio.crearServicio(idServicio.hashCode(),descripcion.getText(),costo.hashCode(),idEmpleado.hashCode()));
        idServicio.setText("");
        servicio.setText("");
        descripcion.setText("");
        costo.setText("");
        idEmpleado.setText("");
        total.setText("");
    }
    public void eliminarServicioEvent(){

    }
    public void modificarServicioEvent(){

    }
}
