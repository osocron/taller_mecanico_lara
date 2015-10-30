package viewControlers;

import entidades.ServicioEntity;
import entityControlers.ControladorServicio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.awt.*;
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

    private ObservableList<ServicioEntity> dataServicio = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        java.util.List<ServicioEntity> listaServicio = ControladorServicio.getServicios();
        dataServicio.addAll(listaServicio);
    }
}
