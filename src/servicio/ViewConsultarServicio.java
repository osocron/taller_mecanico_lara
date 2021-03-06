package servicio;

import empleado.ControladorEmpleado;
import entidades.EmpleadoEntity;
import entidades.ServicioEntity;
import servicio.ControladorServicio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by ValdemarRamos on 28/10/2015.
 */
public class ViewConsultarServicio implements Initializable {

    public TableView<ServicioEntity> tableviewServicio;
    public TableColumn<ServicioEntity,String> idServicio;
    public TableColumn<ServicioEntity,String> descripcion;
    public TableColumn<ServicioEntity,BigDecimal> costo;
    public TableColumn<ServicioEntity, Integer> idEmpleado;

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
        idEmpleado.setCellFactory(column -> new TableCell<ServicioEntity, Integer>(){
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    EmpleadoEntity empleadoEntity = ControladorEmpleado.getEmpleadoPorID(item);
                    setText(empleadoEntity.getNombre());
                }
            }
        });
        tableviewServicio.setItems(dataServicio);
    }

    public void eliminarServicioEvent(){
        if (tableviewServicio.getSelectionModel().getSelectedItem() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Cuidado");
            alert.setHeaderText("Atención!");
            alert.setContentText("¿Seguro que deseas eliminar el elemento?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                ServicioEntity servicioEntity = tableviewServicio.getSelectionModel().getSelectedItem();
                dataServicio.remove(servicioEntity);
                tableviewServicio.setItems(dataServicio);
                ControladorServicio.eliminarServicio(servicioEntity.getIdServicio());
            }
        }else {
            Alert alert = getWarningAlert("Cuidado", "Atencion", "Favor de seleccionar un elemento!");
            alert.showAndWait();
        }
    }

    private Alert getWarningAlert(String title, String headerText, String contentText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        return alert;
    }


    public void cerrarVentanaEvent() {
        Stage stage = (Stage) tableviewServicio.getScene().getWindow();
        stage.close();
    }
}
