package viewControlers;

import entidades.ServicioEntity;
import entityControlers.ControladorServicio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by osocron on 6/11/15.
 */
public class ViewSeleccionarServicio implements Initializable{

    public ListView<ServicioEntity> servicioListView;
    public Button cancelarButton;
    public Button seleccionarServicioButton;

    private ViewRegistrarVenta parent;
    private ObservableList<ServicioEntity> dataServicios = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<ServicioEntity> servicioEntityList = ControladorServicio.getServicios();
        dataServicios.addAll(servicioEntityList);
        servicioListView.setItems(dataServicios);
    }

    public void cancelarActionEvent() {
        Stage stage = (Stage)cancelarButton.getScene().getWindow();
        stage.close();
    }

    public void seleccionarServicioActionEvent() {
        ServicioEntity servicioEntity = servicioListView.getSelectionModel().getSelectedItem();
        parent.setSelectedServicio(servicioEntity);
        cancelarActionEvent();
    }

    public void setParent(ViewRegistrarVenta parent) {
        this.parent = parent;
    }

}
