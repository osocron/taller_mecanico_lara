package viewControlers;

import entidades.ServicioEntity;
import entityControlers.ControladorServicio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by ValdemarRamos on 28/10/2015.
 */
public class ViewConsultarServicio implements Initializable {

    public TableView<ServicioEntity> tableviewServicio;
    public TableColumn<ServicioEntity,String> idServicio;
    public TableColumn<ServicioEntity,String> descripcion;
    public TableColumn<ServicioEntity,BigDecimal> costo;
    public TableColumn<ServicioEntity, String> idEmpleado;

    private ObservableList<ServicioEntity> dataServicio = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        java.util.List<ServicioEntity> listaServicio = ControladorServicio.getServicios();
        dataServicio.addAll(listaServicio);

        prepararTableView();

    }

    private void prepararTableView() {
        tableviewServicio.setEditable(true);
        idServicio.setCellValueFactory(new PropertyValueFactory<>("idServicio"));
        descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        descripcion.setCellFactory(TextFieldTableCell.forTableColumn());
        descripcion.setOnEditCommit(event -> ControladorServicio.modificarDescripcion(
                event.getTableView().getSelectionModel().getSelectedItem().getIdServicio(),
                event.getNewValue()
        ));
        costo.setCellValueFactory(new PropertyValueFactory<>("costo"));
        costo.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<BigDecimal>() {
            @Override
            public String toString(BigDecimal object) {
                return String.valueOf(object);
            }

            @Override
            public BigDecimal fromString(String string) {
                return new BigDecimal(string);
            }
        }));
        costo.setOnEditCommit(event -> ControladorServicio.modificarCosto(
                event.getTableView().getSelectionModel().getSelectedItem().getIdServicio(),
                event.getNewValue()
        ));
        idEmpleado.setCellValueFactory(new PropertyValueFactory<>("idEmpleados"));
        tableviewServicio.setItems(dataServicio);
    }

    public void eliminarServicioEvent(){
        ServicioEntity servicioEntity = tableviewServicio.getSelectionModel().getSelectedItem();
        dataServicio.remove(servicioEntity);
        tableviewServicio.setItems(dataServicio);
        ControladorServicio.eliminarServicio(servicioEntity.getIdServicio());
    }


    public void cerrarVentanaEvent() {
        Stage stage = (Stage) tableviewServicio.getScene().getWindow();
        stage.close();
    }
}
